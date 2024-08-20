package com.example.blackcows.ui.home

import android.util.Log
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
import kotlinx.coroutines.launch

class HomeViewModel (private val repository : VideoRepository) : ViewModel() {

    private val _categoryVideos = MutableLiveData<List<ListItem.VideoItem>>()
    val categoryVideos : LiveData<List<ListItem.VideoItem>> = _categoryVideos

    fun getCategoryVideos(categoryId : String){
        viewModelScope.launch {
            try {
                val response = repository.getCategoryVideos(categoryId)
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching category videos", e)
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