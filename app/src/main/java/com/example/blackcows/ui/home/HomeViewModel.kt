package com.example.blackcows.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.blackcows.ListItem
import com.example.blackcows.data.repository.VideoRepository
import com.example.blackcows.data.repository.YoutubeRepositoryImpl
import com.example.blackcows.network.RetrofitClient
import com.example.blackcows.toSearchVideoItem
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: VideoRepository) : ViewModel() {

    // 카테고리
    private val _homeVideos = MutableLiveData<List<ListItem.VideoItem>>()
    val homeVideos: LiveData<List<ListItem.VideoItem>> = _homeVideos

    fun getHomeVideos(query: String, pageToken: String?) {
        viewModelScope.launch {
            try {
                val response = repository.getSearchVideos(query, pageToken)
                val items = response.items
                _homeVideos.value = items?.toSearchVideoItem()
            } catch (e : Exception) {
                _homeVideos.value = emptyList()
            }
        }
    }

    // 인기영상
    private val _popularVideos = MutableLiveData<List<ListItem.VideoItem>>()
    val popularVideos : LiveData<List<ListItem.VideoItem>> = _popularVideos

    fun getPopularVideos(query: String, pageToken: String?) {
        viewModelScope.launch {
            try {
                val response = repository.getSearchVideos(query, pageToken)
                val items = response.items
                _popularVideos.value = items?.toSearchVideoItem()
                } catch (e : Exception) {
                    _popularVideos.value = emptyList()
            }
        }
    }
}

class HomeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        val repository = YoutubeRepositoryImpl(RetrofitClient.searchVideoRemoteDataSource)
        return HomeViewModel(
            repository
        ) as T
    }
}