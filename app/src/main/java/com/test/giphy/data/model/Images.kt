package com.test.giphy.data.model


import com.google.gson.annotations.SerializedName

data class Images(
    val original: Original,
    @SerializedName("original_mp4")
    val previewGif: PreviewGif,
)