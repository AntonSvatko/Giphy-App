package com.test.giphy.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.giphy.data.model.Data
import com.test.giphy.network.api.GifService
import com.test.giphy.network.const.ApiConstants
import com.test.giphy.ui.base.paging.BasePagingSource
import com.test.giphy.utill.getSharedPref

class GifPagingSource(
    private val photoApiService: GifService,
    private val source: PagingSource<Int, Data>,
    private val online: Boolean,
    private val isOnline: (Boolean) -> Boolean
) : BasePagingSource() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPage = params.key ?: 0
            val response = photoApiService.fetchGifs(offset = nextPage)

            val listItem = blackList(response)

            isOnline(online)
            if (online)
                loadResult(listItem, nextPage)
            else
                source.load(params)
        } catch (e: Exception) {
            isOnline(false)
            source.load(params)
        }
    }
}