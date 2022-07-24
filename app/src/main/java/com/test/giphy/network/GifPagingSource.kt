package com.test.giphy.network

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.giphy.data.model.Data
import com.test.giphy.network.api.GifService

class GifPagingSource(
    private val photoApiService: GifService,
) : PagingSource<Int, Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPage = params.key ?: 0
            val response = photoApiService.fetchPhotos(offset = nextPage)

            val listItem = response.data

            LoadResult.Page(
                data = listItem,
                prevKey = if (nextPage == 0) null else nextPage - 1,
                nextKey = nextPage + 20
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return null
    }

}