package com.example.blackcows.data.model

import com.google.gson.annotations.SerializedName

data class YouTubeSearchResponse(
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("nextPageToken")
    val nextPageToken: String?,
    @SerializedName("items")
    val items: List<YouTubeItem>?
)
data class YouTubeItem(
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("id")
    val id: YouTubeId?,
    @SerializedName("snippet")
    val snippet: SearchSnippet?
)

data class SearchSnippet(
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("channelId")
    val channelId: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("thumbnails")
    val thumbnails: Thumbnails?,
    @SerializedName("channelTitle")
    val channelTitle: String?,
    @SerializedName("liveBroadcastContent")
    val liveBroadcastContent: String?
)

data class YouTubeId(
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("videoId")
    val videoId: String?,
    @SerializedName("channelId")
    val channelId: String?,
    @SerializedName("playlistId")
    val playlistId: String?
)