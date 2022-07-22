package com.test.giphy.data.model


import com.google.gson.annotations.SerializedName

data class FixedHeightDownsampled(
    val height: String,
    val size: String,
    val url: String,
    val webp: String,
    @SerializedName("webp_size")
    val webpSize: String,
    val width: String
)