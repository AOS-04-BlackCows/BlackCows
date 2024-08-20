package com.example.blackcows

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ListItem : Parcelable {
    @Parcelize
    data class HeaderItem(val thumbnail: String) : ListItem()

    @Parcelize
    data class VideoItem(
        val channelTitle: String,
        val title: String,
        val thumbnail: String,
        val description: String,
        val videoId: String
    ) : ListItem()
}