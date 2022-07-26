package com.test.giphy.network.api

import com.test.giphy.data.model.Gif
import com.test.giphy.network.const.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface GifService {

    @GET("/v1/gifs/trending")
    suspend fun fetchGifs(
        @Query("api_key") apiKey: String = ApiConstants.API_KEY,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = ApiConstants.API_PAGE_SIZE
    ): Gif

    @GET("/v1/gifs/search")
    suspend fun fetchSearchGifs(
        @Query("api_key") apiKey: String = ApiConstants.API_KEY,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = ApiConstants.API_PAGE_SIZE,
        @Query("q") text: String
    ): Gif
}