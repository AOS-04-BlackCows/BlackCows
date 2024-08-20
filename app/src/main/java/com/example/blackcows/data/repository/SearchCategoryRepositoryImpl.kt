package com.example.blackcows.data.repository

import com.example.blackcows.data.model.SearchCategoryDataSource
import com.example.blackcows.data.model.SearchSubCategory
import com.example.blackcows.ui.search.SearchCategory

class SearchCategoryRepositoryImpl(private val searchCategoryDataSource : SearchCategoryDataSource) : SearchCategoryRepository {
    override fun getSearchSubCategory(searchCategoryType: SearchCategory): List<SearchSubCategory> {
        return searchCategoryDataSource.getSearchSubCategory(searchCategoryType)
    }
}