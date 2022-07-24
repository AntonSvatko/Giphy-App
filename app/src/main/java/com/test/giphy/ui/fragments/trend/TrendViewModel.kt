package com.test.giphy.ui.fragments.trend

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.test.giphy.data.model.Data
import com.test.giphy.data.repository.DataRepository
import com.test.giphy.network.result.ResultOf
import com.test.giphy.ui.adapter.GifAdapter
import com.test.giphy.ui.adapter.ViewPagerAdapter
import com.test.giphy.ui.base.viewmodel.CoroutineViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class TrendViewModel @Inject constructor(
    private val repository: DataRepository
) : CoroutineViewModel() {

    var adapter: GifAdapter? = null
    var listCreated: Flow<PagingData<Data>> = flowOf()

    fun getPhotos() =
        repository.fetchGifs(viewModelScope).flowOn(Dispatchers.IO)

}