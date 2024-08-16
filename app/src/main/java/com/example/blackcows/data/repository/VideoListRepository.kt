package com.example.blackcows.data.repository

import com.example.blackcows.data.model.VideoResponse

interface VideoRepository {
    suspend fun getTrendingVideos(region: String): VideoResponse

    // home 카테고리 서치
    suspend fun getCategoryVideos(category : String) : VideoResponse
}