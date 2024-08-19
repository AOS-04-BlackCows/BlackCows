package com.example.blackcows.data.repository

import com.example.blackcows.data.model.YouTubeSearchResponse
import com.example.blackcows.data.model.VideoResponse

interface VideoRepository {
    suspend fun getTrendingVideos(region: String): VideoResponse
  
    suspend fun getSearchVideos(query: String): YouTubeSearchResponse
    // home 카테고리 서치
    suspend fun getCategoryVideos(category : String) : VideoResponse
}