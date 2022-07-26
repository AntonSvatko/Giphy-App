package com.test.giphy.ui.base.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.giphy.data.model.Data
import com.test.giphy.data.model.Gif
import com.test.giphy.network.api.GifService
import com.test.giphy.utill.getSharedPref
import retrofit2.Response

abstract class BasePagingSource(
    private val photoApiService: GifService,
    private val response: Gif
) : PagingSource<Int, Data>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val nextPage = params.key ?: 0
        val response = photoApiService.fetchGifs(offset = nextPage)

        val blackList = getSharedPref()
        val listItem = response.data.filter {
            !(blackList?.contains(it.id) ?: false)
        }

        return LoadResult.Page(
            data = listItem,
            prevKey = if (nextPage == 0) null else nextPage - 1,
            nextKey = nextPage + 20
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return null
    }
}