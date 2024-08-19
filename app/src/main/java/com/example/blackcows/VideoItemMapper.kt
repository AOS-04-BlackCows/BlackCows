package com.example.blackcows

import com.example.blackcows.ListItem.*
import com.example.blackcows.data.model.Item
import com.example.blackcows.data.model.SearchSnippet
import com.example.blackcows.data.model.Snippet
import com.example.blackcows.data.model.YouTubeItem

//fun List<Item>.toVideoItem(): List<VideoItem> {
//    return this.map {
//        VideoItem(
//            channelTitle = it.snippet?.channelTitle ?: "",
//            title = it.snippet?.title ?: "",
//            thumbnail = it.snippet?.thumbnails?.high?.url ?: "",
//            description = it.snippet?.description ?: ""
//        )
//    }
//}

fun List<SearchSnippet>.toSearchVideoItem(): List<VideoItem> {
    return this.map {
        VideoItem(
            channelTitle = it.channelTitle ?: "",
            title = it.title ?: "",
            thumbnail = it.thumbnails?.high?.url ?: "",
            description = it.description ?: ""
        )
    }
}