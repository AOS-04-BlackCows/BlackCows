package com.example.blackcows.data.repository

import com.example.blackcows.data.model.YouTubeSearchResponse
import com.example.blackcows.data.model.VideoResponse
import com.example.blackcows.data.remote.SearchVideoRemoteDataSource

class YoutubeRepositoryImpl(
    private val searchVideoRemoteDataSource: SearchVideoRemoteDataSource
) : VideoRepository {

    override suspend fun getTrendingVideos(region: String): VideoResponse {
        return searchVideoRemoteDataSource.getTrendingVideos()
    }

    override suspend fun getSearchVideos(query: String, pageToken: String?): YouTubeSearchResponse {
        return searchVideoRemoteDataSource.getSearchVideos(query, pageToken)
    }

}