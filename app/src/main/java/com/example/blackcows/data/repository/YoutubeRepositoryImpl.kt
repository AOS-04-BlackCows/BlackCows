package com.example.blackcows.data.repository

import com.example.blackcows.data.model.VideoResponse
import com.example.blackcows.data.remote.SearchVideoRemoteDataSource
import com.example.blackcows.network.RetrofitClient

class YoutubeRepositoryImpl(private val searchVideoRemoteDataSource: SearchVideoRemoteDataSource) : VideoRepository {
    override suspend fun getTrendingVideos(region: String): VideoResponse {
        return searchVideoRemoteDataSource.getTrendingVideos()
    }

    // home 카테고리 서치
    override suspend fun getCategoryVideos(category : String): VideoResponse {
        return searchVideoRemoteDataSource.getTrendingVideos(query = category)
    }
}