package com.example.blackcows.data.repository

import com.example.blackcows.data.model.SearchCategoryDataSource
import com.example.blackcows.data.model.SubCategory
import com.example.blackcows.ui.search.Category

class CategoryRepositoryImpl(private val searchCategoryDataSource : SearchCategoryDataSource) : CategoryRepository {
    override fun getSubCategory(categoryType : Category): List<SubCategory> {
        return searchCategoryDataSource.getSubCategory(categoryType)
    }
}