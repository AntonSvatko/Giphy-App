package com.test.giphy.data.model


import com.google.gson.annotations.SerializedName

data class DownsizedSmall(
    val height: String,
    val mp4: String,
    @SerializedName("mp4_size")
    val mp4Size: String,
    val width: String
)