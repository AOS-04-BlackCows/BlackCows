package com.example.blackcows.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.blackcows.ListItem
import com.example.blackcows.data.model.SearchSubCategory
import com.example.blackcows.data.repository.VideoRepository
import com.example.blackcows.data.repository.YoutubeRepositoryImpl
import com.example.blackcows.network.RetrofitClient

class DetailViewModel(private val repository : VideoRepository) : ViewModel() {

    private val _detailVideos = MutableLiveData<ListItem.VideoItem>()
    val detailVideos: LiveData<ListItem.VideoItem> = _detailVideos
    var position: Int = 0
    var danawaCategory : SearchSubCategory = SearchSubCategory("","0")
    var videoData:ListItem.VideoItem = ListItem.VideoItem("testT", " ", " ", " ", " ")
    fun setDetailVideoData(item: ListItem.VideoItem) {
        _detailVideos.value = item
    }
}

class DetailViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        val repository = YoutubeRepositoryImpl(RetrofitClient.searchVideoRemoteDataSource)
        return DetailViewModel(
            repository
        ) as T
    }
}