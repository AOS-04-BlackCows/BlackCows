package com.example.blackcows.ui.mypage

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

class MypageViewModel(private val repository : VideoRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Mypage Fragment"
    }
    val text: LiveData<String> = _text
    fun getVideoThumbanail(){
        viewModelScope.launch {
            Log.d("MypageViewModel_data",repository.getTrendingVideos("").toString())
            repository.getTrendingVideos("키보드")
        }
    }
}

class MypageViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        val repository = YoutubeRepositoryImpl(RetrofitClient.searchVideoRemoteDataSource)
        return MypageViewModel(
            repository
        ) as T
    }
}