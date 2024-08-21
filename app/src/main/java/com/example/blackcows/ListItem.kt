package com.example.blackcows

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ListItem : Parcelable {
    @Parcelize
    data class HeaderItem(val thumbnail: String) : ListItem()

    @Parcelize
    data class VideoItem(
        var channelTitle: String,
        var title: String,
        var thumbnail: String,
        var description: String,
        var videoId: String
    ) : ListItem()
}