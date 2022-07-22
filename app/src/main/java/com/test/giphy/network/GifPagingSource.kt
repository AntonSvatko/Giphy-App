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
            Log.d(
                "retrofit1.1", response.data.size.toString()
            )
            val listItem = response.data

            Log.d("data11", response.data[0].title)
            Log.d("data11",  response.data[1].title)

            listItem.forEach {
                Log.d("data1", it.title)
            }

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