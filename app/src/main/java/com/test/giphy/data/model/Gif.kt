package com.test.giphy.data.model


data class Gif(
    val data: List<Data>,
    val pagination: Pagination,
    val meta: Meta
)