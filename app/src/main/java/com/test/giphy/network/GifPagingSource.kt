package com.test.giphy.network

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.giphy.data.model.Data
import com.test.giphy.network.api.GifService
import com.test.giphy.utill.getSharedPref

class GifPagingSource(
    private val photoApiService: GifService,
    private val source: PagingSource<Int, Data>,
    private val online: Boolean,
    private val isOnline: (Boolean) -> Unit
) : PagingSource<Int, Data>() {

    private var page: Int = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPage = params.key ?: 0
            val response = photoApiService.fetchGifs(offset = nextPage)
            page = nextPage

            val blackList = getSharedPref()
            val listItem = response.data.filter {
                !(blackList?.contains(it.id) ?: false)
            }

            if (online) {
                isOnline(true)
                LoadResult.Page(
                    data = listItem,
                    prevKey = if (nextPage == 0) null else nextPage - 1,
                    nextKey = nextPage + 20
                )
            }
            else {
                isOnline(false)
                source.load(params)
            }
        } catch (e: Exception) {
            Log.d("Error1", e.toString())
            isOnline(false)
            source.load(params)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return null
    }
}