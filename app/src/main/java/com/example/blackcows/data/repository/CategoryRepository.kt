package com.example.blackcows.data.repository

import com.example.blackcows.data.model.SubCategory
import com.example.blackcows.ui.search.Category

interface CategoryRepository {
    fun getSubCategory(categoryType : Category) : List<SubCategory>
}