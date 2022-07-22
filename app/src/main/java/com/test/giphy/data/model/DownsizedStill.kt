package com.test.giphy.data.model


import com.google.gson.annotations.SerializedName

data class DownsizedStill(
    val height: String,
    val size: String,
    val url: String,
    val width: String
)