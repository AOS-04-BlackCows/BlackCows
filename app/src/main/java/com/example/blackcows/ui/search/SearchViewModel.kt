package com.example.blackcows.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.blackcows.data.model.SearchCategoryDataSource
import com.example.blackcows.data.repository.CategoryRepository
import com.example.blackcows.data.repository.CategoryRepositoryImpl

class SearchViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Search Fragment"
    }
    val text: LiveData<String> = _text

    fun getSubCategory(category : Category) {
        categoryRepository.getSubCategory(category)
    }
}

class SearchViewModelFactory : ViewModelProvider.Factory {
    private val categoryRepository = CategoryRepositoryImpl(SearchCategoryDataSource)

    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {

        return SearchViewModel(
            categoryRepository
        ) as T
    }
}