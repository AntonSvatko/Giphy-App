package com.test.giphy.data.model


import com.google.gson.annotations.SerializedName

data class Data(
    val id: String,
    val images: Images,
    val title: String,
    val url: String,
)