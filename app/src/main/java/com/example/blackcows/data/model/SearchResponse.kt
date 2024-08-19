//package com.example.blackcows.data.model
//
//import com.google.gson.annotations.SerializedName
//
//data class SearchResult(
//    @SerializedName("kind")
//    val kind: String,
//    @SerializedName("etag")
//    val etag: String,
//    @SerializedName("id")
//    val id: VideoId,
//    @SerializedName("snippet")
//    val snippet: Snippet
//)
//
//data class VideoId(
//    @SerializedName("kind")
//    val kind: String,
//    @SerializedName("videoId")
//    val videoId: String? = null, // nullable로 설정
//    @SerializedName("channelId")
//    val channelId: String? = null, // nullable로 설정
//    @SerializedName("playlistId")
//    val playlistId: String? = null // nullable로 설정
//)
//
//data class Snippet(
//    @SerializedName("publishedAt")
//    val publishedAt: String,
//    @SerializedName("channelId")
//    val channelId: String,
//    @SerializedName("title")
//    val title: String,
//    @SerializedName("description")
//    val description: String,
//    @SerializedName("thumbnails")
//    val thumbnails: Thumbnails,
//    @SerializedName("channelTitle")
//    val channelTitle: String,
//    @SerializedName("liveBroadcastContent")
//    val liveBroadcastContent: String
//)
//
//data class Thumbnail(
//    @SerializedName("url")
//    val url: String,
//    @SerializedName("width")
//    val width: Int,
//    @SerializedName("height")
//    val height: Int
//)