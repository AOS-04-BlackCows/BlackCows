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
import com.example.blackcows.toSearchVideoItem
import kotlinx.coroutines.launch
import com.example.blackcows.ListItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okio.IOException
import retrofit2.HttpException
import java.util.Collections.addAll

private const val TAG = "SearchViewModel"

class SearchViewModel(private val repository: VideoRepository) : ViewModel() {


    var position: Int = 0
    var nextPageToken: String = ""
    private val _trendingVideos = MutableLiveData<List<ListItem.VideoItem>?>()
    val trendingVideos: LiveData<List<ListItem.VideoItem>?> = _trendingVideos
    private var searchKeyword: String = ""
    fun getSearchVideos(query: String, pageToken: String? = null){
        viewModelScope.launch {
            runCatching {
                val response = repository.getSearchVideos(query, pageToken)
                val items = response.items
                val videos = items?.toSearchVideoItem()
                _trendingVideos.value = videos

                nextPageToken = response.nextPageToken?: ""
                searchKeyword = query
            }.onFailure {
                Log.e(TAG, "getSearchVideos() failed! : ${it.message}")
                handleException(it)
            }
        }
    }

    fun addNextPage (query: String, pageToken: String?) {
        viewModelScope.launch {
            runCatching {
                val response = repository.getSearchVideos(query, pageToken)
                val items = response.items
                val videos = items?.toSearchVideoItem()
                _trendingVideos.value = _trendingVideos.value!!.toMutableList().apply {
                    if (videos != null) {
                        addAll(videos)
                    }
                }

                nextPageToken = response.nextPageToken?: ""
            }.onFailure {
                Log.e(TAG, "getSearchVideos() failed! : ${it.message}")
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