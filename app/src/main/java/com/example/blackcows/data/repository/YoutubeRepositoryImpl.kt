package com.example.blackcows.data.repository

import com.example.blackcows.data.model.VideoResponse
import com.example.blackcows.data.remote.SearchVideoRemoteDataSource
import com.example.blackcows.network.RetrofitClient

class YoutubeRepositoryImpl(private val searchVideoRemoteDataSource: SearchVideoRemoteDataSource) : VideoRepository {
    override suspend fun getTrendingVideos(region: String): VideoResponse {
        return searchVideoRemoteDataSource.getTrendingVideos()
    }
    override suspend fun getSearchVideos(query: String): VideoResponse {
        return searchVideoRemoteDataSource.getSearchVideos(query)
    }
}