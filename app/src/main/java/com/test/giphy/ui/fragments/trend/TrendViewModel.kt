package com.test.giphy.ui.fragments.trend

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.test.giphy.data.model.Data
import com.test.giphy.data.repository.DataRepository
import com.test.giphy.network.result.ResultOf
import com.test.giphy.ui.base.viewmodel.CoroutineViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class TrendViewModel @Inject constructor(
    private val repository: DataRepository
) : CoroutineViewModel() {

    init {
        getPhotos2()
        Log.d("repo1",  "21")
    }

    fun getPhotos() =
        repository.fetchGifs(viewModelScope).flowOn(Dispatchers.IO)

    fun getPhotos2(){
        launchSafely {
           Log.d("repo1",  "21"+ (repository.photosClient.fetchGif() as ResultOf.Success).data.forEach {
               Log.d("repo1",  "21"+ it.title)
           })
        }
    }
}