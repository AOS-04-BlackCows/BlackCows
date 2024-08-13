package com.example.blackcows.data.repository

import com.example.blackcows.data.model.VideoResponse

interface VideoRepository {
    suspend fun getTrendingVideos(region: String): VideoResponse
}