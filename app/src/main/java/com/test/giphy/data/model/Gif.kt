package com.test.giphy.data.model


import com.google.gson.annotations.SerializedName

data class Gif(
    val data: List<Data>,
    val pagination: Pagination,
    val meta: Meta
)