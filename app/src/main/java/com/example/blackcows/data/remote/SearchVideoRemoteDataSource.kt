package com.example.blackcows.data.remote

import com.example.blackcows.data.model.YouTubeSearchResponse
import com.example.blackcows.data.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_MAX_RESULT = 20
private const val API_REGION = "KR"
private const val API_KEY = "AIzaSyCGmiXhF9ZFQrtFUslYwGDxWoitXL085Dw"//BuildConfig.YOUTUBE_API_KEY
//화민 : AIzaSyCf0LT94hMzBUrsNmHLADw_6GPW0Q39l2Y
//문기 : AIzaSyA26WhxZpd4IkYzUy784K98mPqPqHzMkiM
//지민 : AIzaSyA6rDu-BRuKFCsdrjLIr_XjWHjjMb6Qq34
//혜령 : AIzaSyCGmiXhF9ZFQrtFUslYwGDxWoitXL085Dw

interface SearchVideoRemoteDataSource {
    @GET("videos")
    suspend fun getTrendingVideos(
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("maxResults") maxResults: Int = API_MAX_RESULT,
        @Query("regionCode") regionCode: String = API_REGION,
        @Query("key") apiKey: String = API_KEY,
        @Query("q") query: String = ""
    ): VideoResponse

    @GET("search")
    suspend fun getSearchVideos(
        @Query("q") query: String,
        @Query("pageToken") pageToken: String?,
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("maxResults") maxResults: Int = API_MAX_RESULT,
        @Query("regionCode") regionCode: String = "KR",
        @Query("key") apiKey: String = API_KEY
    ): YouTubeSearchResponse


}
