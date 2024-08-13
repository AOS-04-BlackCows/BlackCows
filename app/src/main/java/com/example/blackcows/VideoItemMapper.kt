package com.example.blackcows

import com.example.blackcows.data.model.Item

fun List<Item>.toVideoItem(): List<ListItem.VideoItem> {
    return this.map {
        ListItem.VideoItem(
            channelTitle = it.snippet?.channelTitle ?: "",
            title = it.snippet?.title ?: "",
            thumbnail = it.snippet?.thumbnails?.high?.url ?: "",
            description = it.snippet?.description ?: ""
        )
    }
}