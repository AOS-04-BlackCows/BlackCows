package com.example.blackcows.data.repository

import com.example.blackcows.data.model.SearchSubCategory
import com.example.blackcows.ui.home.HomeCategoryDataClass
import com.example.blackcows.ui.search.SearchCategory

interface SearchCategoryRepository {
    fun getSearchSubCategory(searchCategoryType : SearchCategory) : List<SearchSubCategory>
}

