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

class HomeViewModel (private val repository : VideoRepository) : ViewModel() {

    private val _homeVideos = MutableLiveData<List<ListItem.VideoItem>>()
    val homeVideos: LiveData<List<ListItem.VideoItem>> = _homeVideos

    // 주어진 query를 사용해 비디오를 검색하고 결과를 처리하는 역할
    fun getHomeVideos(query: String) {
        viewModelScope.launch {
            try {
                val items = repository.getSearchVideos(query).items
                val snippets = items?.mapNotNull { it.snippet }
                snippets?.let {
                    _homeVideos.value = it.toSearchVideoItem()
                }
            } catch (e : Exception) {
                _homeVideos.value = emptyList()
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