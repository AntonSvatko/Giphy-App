package com.test.giphy.network

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.giphy.data.model.Data
import com.test.giphy.network.api.GifService
import com.test.giphy.network.exception.EmptyResultException
import com.test.giphy.utill.getSharedPref

class GifPagingSourceSearch(
    private val photoApiService: GifService,
    private val text: String
) : PagingSource<Int, Data>() {

    private var page: Int = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {

        return try {
            val nextPage = params.key ?: 0
            val response = photoApiService.fetchSearchGifs(offset = nextPage, text = text)
            page = nextPage

            val blackList = getSharedPref()
            val listItem = response.data.filter {
                blackList?.contains(it.id) ?: true
            }


            if (listItem.isEmpty() && page > 0)
                LoadResult.Error(EmptyResultException())
            else
                LoadResult.Page(
                    data = listItem,
                    prevKey = if (nextPage == 0) null else nextPage - 1,
                    nextKey = nextPage + 20
                )
        } catch (e: Exception) {
            Log.d("Error1", e.toString())
            LoadResult.Error(e)
        }
    }

    override val keyReuseSupported: Boolean
        get() = true

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return null
    }


}