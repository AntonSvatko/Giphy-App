package com.test.giphy.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.test.giphy.network.GifClient
import com.test.giphy.network.GifPagingSource
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class DataRepository @Inject constructor(
    val photosClient: GifClient,
//    private val favoritesDao: FavoritesDao
) {

    fun fetchGifs(scope: CoroutineScope) = Pager(PagingConfig(pageSize = 20)) {
        GifPagingSource(photosClient.gifService)
    }.flow.cachedIn(scope)

}