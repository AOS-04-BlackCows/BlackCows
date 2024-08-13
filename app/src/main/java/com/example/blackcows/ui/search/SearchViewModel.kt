package com.example.blackcows.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.blackcows.data.model.SearchCategoryDataSource
import com.example.blackcows.data.repository.SearchCategoryRepository
import com.example.blackcows.data.repository.SearchCategoryRepositoryImpl

class SearchViewModel(private val searchCategoryRepository: SearchCategoryRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Search Fragment"
    }
    val text: LiveData<String> = _text

    fun getSearchSubCategory(searchCategory : SearchCategory) {
        searchCategoryRepository.getSearchSubCategory(searchCategory)
    }
}

class SearchViewModelFactory : ViewModelProvider.Factory {
    private val searchCategoryRepository = SearchCategoryRepositoryImpl(SearchCategoryDataSource)

    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {

        return SearchViewModel(
            searchCategoryRepository
        ) as T
    }
}