package com.example.blackcows.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.blackcows.ListItem
import com.example.blackcows.data.model.SearchSubCategory
import com.example.blackcows.data.repository.VideoRepository
import com.example.blackcows.data.repository.YoutubeRepositoryImpl
import com.example.blackcows.network.RetrofitClient
import kotlinx.coroutines.launch

class DetailViewModel(private val repository : VideoRepository) : ViewModel() {

    private val _DetailVideos = MutableLiveData<List<ListItem.VideoItem>?>()
    val detailVideos: LiveData<List<ListItem.VideoItem>?> = _DetailVideos
    var position: Int = 0
    lateinit var danawaCategory : SearchSubCategory
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