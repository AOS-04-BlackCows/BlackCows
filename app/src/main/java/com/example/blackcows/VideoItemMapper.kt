package com.example.blackcows

import com.example.blackcows.ListItem.*
import com.example.blackcows.data.model.Item
import com.example.blackcows.data.model.SearchSnippet
import com.example.blackcows.data.model.Snippet
import com.example.blackcows.data.model.YouTubeItem

fun List<Item>.toVideoItem(): List<VideoItem> {
    return this.map {
        VideoItem(
            channelTitle = it.snippet?.get(0)?.channelTitle ?: "",
            title = it.snippet?.get(0)?.title ?: "",
            thumbnail = it.snippet?.get(0)?.thumbnails?.high?.url ?: "",
            description = it.snippet?.get(0)?.description ?: "",
            videoId = it.id ?: ""
        )
    }
}

fun List<YouTubeItem>.toSearchVideoItem(): List<VideoItem> {
    return this.map {
        VideoItem(
            channelTitle = it.snippet?.channelTitle ?: "",
            title = it.snippet?.title ?: "",
            thumbnail = it.snippet?.thumbnails?.high?.url ?: "",
            description = it.snippet?.description ?: "",
            videoId = it.id?.videoId ?: ""
        )
    }
}