package com.example.blackcows.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.blackcows.data.model.SearchCategoryDataSource
import com.example.blackcows.data.repository.SearchCategoryRepository
import com.example.blackcows.data.repository.SearchCategoryRepositoryImpl
import com.example.blackcows.data.repository.VideoRepository
import com.example.blackcows.data.repository.YoutubeRepositoryImpl
import com.example.blackcows.network.RetrofitClient
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: VideoRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Search Fragment"
    }
    val text: LiveData<String> = _text

//    fun getSearchSubCategory(searchCategory : SearchCategory) {
//        repository.getSearchSubCategory(searchCategory)
//    }
    fun getVideoThumbanail(){
        viewModelScope.launch {
            Log.d("SearchViewModel_data",repository.getTrendingVideos("").toString())
            repository.getTrendingVideos("하드디스크")
        }
    }
}

class SearchViewModelFactory : ViewModelProvider.Factory {
    private val searchCategoryRepository = SearchCategoryRepositoryImpl(SearchCategoryDataSource)

    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
//        return SearchViewModel(
            searchCategoryRepository
         val repository = YoutubeRepositoryImpl(RetrofitClient.searchVideoRemoteDataSource)
         return SearchViewModel(
             repository
        ) as T
    }
}