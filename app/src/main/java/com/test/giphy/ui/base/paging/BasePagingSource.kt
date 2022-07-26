package com.test.giphy.ui.base.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.giphy.data.model.Data
import com.test.giphy.data.model.Gif
import com.test.giphy.network.const.ApiConstants
import com.test.giphy.utill.getSharedPref

abstract class BasePagingSource : PagingSource<Int, Data>() {
    fun blackList(response: Gif): List<Data> {
        val blackList = getSharedPref()
        return response.data.filter {
            !(blackList?.contains(it.id) ?: false)
        }
    }

    fun loadResult(listItem: List<Data>, nextPage: Int) =
        LoadResult.Page(
            data = listItem,
            prevKey = if (nextPage == 0) null else nextPage - 1,
            nextKey = nextPage + ApiConstants.API_PAGE_SIZE
        )

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return null
    }
}