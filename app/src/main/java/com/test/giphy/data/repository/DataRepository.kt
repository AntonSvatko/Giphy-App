package com.test.giphy.data.repository

import androidx.paging.*
import com.test.giphy.data.database.dao.GifDao
import com.test.giphy.data.model.Data
import com.test.giphy.network.GifClient
import com.test.giphy.network.GifPagingSource
import com.test.giphy.network.GifPagingSourceSearch
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val gifClient: GifClient,
    private val gifDao: GifDao
) {

    suspend fun insertGif(data: Data) = gifDao.insertData(data)

    suspend fun updateFavorite(data: Data) = gifDao.updateData(data)

    suspend fun getFavorite(id: String) = gifDao.getData(id)

    private fun getAllGifsDB() = gifDao.getAllGifsLiveData()

    fun getAllGifs(scope: CoroutineScope) = Pager(PagingConfig(pageSize = 20)) {
        GifPagingSource(
            gifClient.gifService,
            getAllGifsDB()
        )
    }.flow.cachedIn(scope)

    fun getSearched(scope: CoroutineScope, text: String) = Pager(PagingConfig(pageSize = 20)) {
        GifPagingSourceSearch(gifClient.gifService, text)
    }.flow.cachedIn(scope)
}