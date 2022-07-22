package com.test.giphy.network.api

import com.test.giphy.data.model.Gif
import com.test.giphy.network.const.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface GifService {

    @GET("/v1/gifs/trending")
    suspend fun fetchPhotos(
        @Query("api_key") apiKey: String = ApiConstants.API_KEY,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
//        @Query("extras") extras: String = PHOTO_EXTRAS
    ): Gif

//    @GET("/services/rest/")
//    suspend fun fetchSizes(
//        @Query("photo_id") id: String,
//        @Query("method") method: String = PHOTO_SIZES_METHOD
//    ): ResponseSize


    companion object {
        private const val PHOTOS_LIST_METHOD = "flickr.interestingness.getList"

        private const val PHOTO_EXTRAS = "url_s"
    }
}