package com.example.blackcows.data.repository

import com.example.blackcows.data.model.VideoResponse
import com.example.blackcows.network.RetrofitClient

class YoutubeRepositoryImpl : VideoRepository {
    override suspend fun getTrendingVideos(region: String): VideoResponse {
        return RetrofitClient.youtubeAPI.getTrendingVideos(regionCode = region)
    }
}