package com.test.giphy.network.paging

import com.test.giphy.data.model.Data
import com.test.giphy.network.api.GifService
import com.test.giphy.network.exception.EmptyResultException
import com.test.giphy.ui.base.paging.BasePagingSource

class GifPagingSourceSearch(
    private val photoApiService: GifService,
    private val text: String
) : BasePagingSource() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPage = params.key ?: 0
            val response = photoApiService.fetchSearchGifs(offset = nextPage, text = text)

            val listItem = blackList(response)

            if (listItem.isEmpty() && nextPage > 0)
                LoadResult.Error(EmptyResultException())
            else
                loadResult(listItem, nextPage)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}