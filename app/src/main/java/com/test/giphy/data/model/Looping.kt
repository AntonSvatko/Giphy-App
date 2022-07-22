package com.test.giphy.data.model


import com.google.gson.annotations.SerializedName

data class Looping(
    val mp4: String,
    @SerializedName("mp4_size")
    val mp4Size: String
)