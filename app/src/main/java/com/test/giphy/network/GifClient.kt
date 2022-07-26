package com.test.giphy.network

import com.test.giphy.data.model.Data
import com.test.giphy.network.api.GifService
import com.test.giphy.network.const.ApiConstants
import com.test.giphy.network.exception.GifException
import com.test.giphy.network.result.ResultOf
import javax.inject.Inject

class GifClient @Inject constructor(
    val gifService: GifService
) {

    suspend fun fetchGif(): ResultOf<List<Data>> {
        val data = gifService.fetchGifs()
        return if (data.meta.msg == ApiConstants.API_OK)
            ResultOf.Success(data.data) else errorResponse(data.meta.msg)
    }


    private fun errorResponse(error: String?) = ResultOf.Failure(GifException(error))

}