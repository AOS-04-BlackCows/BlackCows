package com.example.blackcows.data.repository

import com.example.blackcows.data.model.YouTubeSearchResponse
import com.example.blackcows.data.model.VideoResponse
import com.example.blackcows.data.remote.SearchVideoRemoteDataSource

class YoutubeRepositoryImpl(private val searchVideoRemoteDataSource: SearchVideoRemoteDataSource) : VideoRepository {
    override suspend fun getTrendingVideos(region: String): VideoResponse {
        return searchVideoRemoteDataSource.getTrendingVideos()
    }
    
    override suspend fun getSearchVideos(query: String): YouTubeSearchResponse {
        return searchVideoRemoteDataSource.getSearchVideos(query)
    }

    // home 카테고리 서치
    override suspend fun getCategoryVideos(categoryId : String): VideoResponse {
        //query = categoryId로 되어있던 거 수정
        return searchVideoRemoteDataSource.getTrendingVideos(categoryId)
    }
}