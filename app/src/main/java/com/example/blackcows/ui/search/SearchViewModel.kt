package com.example.blackcows.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.blackcows.data.repository.VideoRepository
import com.example.blackcows.data.repository.YoutubeRepositoryImpl
import com.example.blackcows.network.RetrofitClient
import kotlinx.coroutines.launch
import com.example.blackcows.ListItem
import com.example.blackcows.toVideoItem
import okio.IOException
import retrofit2.HttpException

private const val TAG = "SearchViewModel"

class SearchViewModel(private val repository: VideoRepository) : ViewModel() {

    private val _trendingVideos = MutableLiveData<List<ListItem.VideoItem>?>()
    val trendingVideos: LiveData<List<ListItem.VideoItem>?> = _trendingVideos

    fun fetchTrendingVideos(region: String = "KR"){
        viewModelScope.launch {
            runCatching {
                val videos = repository.getTrendingVideos(region).items?.toVideoItem()
                _trendingVideos.value = videos
            }.onFailure {
                Log.e(TAG, "fetchTrendingVideos() failed! : ${it.message}")
                handleException(it)
            }
        }
    }

    private fun handleException(e: Throwable) {
        when (e) {
            is HttpException -> {
                val errorJsonString = e.response()?.errorBody()?.string()
                Log.e(TAG, "HTTP error: $errorJsonString")
            }

            is IOException -> Log.e(TAG, "Network error: $e")
            else -> Log.e(TAG, "Unexpected error: $e")
        }
    }

    // TODO 1. 검색 결과 출력 테스트를 먼저 진행
    // TODO 1-1. 리싸이클러뷰 xml에 추가
    // TODO 1-2. 리싸이클러뷰 아이템 만들기
    // TODO 1-3. 리싸이클러뷰에 검색어를 출력하는걸 만들기

    // TODO 2. 검색어를 입력하는 칸 제작
    // TODO 3. 검색어를 빠르게 입력해주는 UI 제작
}


class SearchViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
         val repository = YoutubeRepositoryImpl(RetrofitClient.searchVideoRemoteDataSource)
        return SearchViewModel(
             repository
        ) as T
    }
}