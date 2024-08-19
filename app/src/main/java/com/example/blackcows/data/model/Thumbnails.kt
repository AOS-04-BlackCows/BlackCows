package com.example.blackcows.data.model

import com.google.gson.annotations.SerializedName

data class Thumbnails(
    @SerializedName("default")
    val default: ThumbnailDetails?,
    @SerializedName("high")
    val high: ThumbnailDetails?,
    @SerializedName("maxres")
    val maxres: ThumbnailDetails?,
    @SerializedName("medium")
    val medium: ThumbnailDetails?,
    @SerializedName("standard")
    val standard: ThumbnailDetails?
)

data class ThumbnailDetails(
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: Int?,
    @SerializedName("height")
    val height: Int?
)