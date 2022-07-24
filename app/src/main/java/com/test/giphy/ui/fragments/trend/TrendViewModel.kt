package com.test.giphy.ui.fragments.trend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.test.giphy.data.model.Data
import com.test.giphy.data.repository.DataRepository
import com.test.giphy.ui.base.viewmodel.CoroutineViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendViewModel @Inject constructor(
    private val repository: DataRepository
) : CoroutineViewModel() {

    var savedAdapter: Boolean = false
    var listCreated: MutableLiveData<PagingData<Data>> = MutableLiveData()

    init {
        launchSafely{
            getPhotos().collectLatest {
                listCreated.postValue(it)
            }
        }
    }

    private fun getPhotos() =
        repository.fetchGifs(viewModelScope).flowOn(Dispatchers.IO)

}