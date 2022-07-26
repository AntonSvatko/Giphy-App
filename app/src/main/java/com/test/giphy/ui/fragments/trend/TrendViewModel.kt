package com.test.giphy.ui.fragments.trend

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.test.giphy.data.model.Data
import com.test.giphy.data.repository.DataRepository
import com.test.giphy.ui.base.viewmodel.CoroutineViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer
import javax.inject.Inject

@HiltViewModel
class TrendViewModel @Inject constructor(
    private val repository: DataRepository,
) : CoroutineViewModel() {

    var listCreated: MutableLiveData<PagingData<Data>> = MutableLiveData()
    var lastSearch = ""


    init {
        update()
    }

    fun update() {
        launchSafely {
            getGifs().collectLatest {
                listCreated.postValue(it)
            }
        }
    }

    private fun getGifs() =
        repository.getAllGifs(viewModelScope).flowOn(Dispatchers.IO)

    fun insertGif(data: Data) {
        data.isDownloaded = true
        launchSafely {
            repository.insertGif(data)
        }
    }

    fun downloadGif(dir: String, drawable: Drawable?, data: Data) {
        viewModelScope.launch(Dispatchers.IO) {
            val name = data.id + ".gif"
            val gifFile = File(dir, name)
            if (!gifFile.exists()) {
                val byteBuffer = (drawable as GifDrawable).buffer

                val output = FileOutputStream(gifFile)
                val bytes = ByteArray(byteBuffer.capacity())
                (byteBuffer.duplicate().clear() as ByteBuffer).get(bytes)
                output.write(bytes, 0, bytes.size)
                output.close()
            }
        }
        insertGif(data)
    }

    private fun <T> debounce(
        waitMs: Long = 500L,
        scope: CoroutineScope,
        destinationFunction: (T) -> Unit
    ): (T) -> Unit {
        var debounceJob: Job? = null
        return { param: T ->
            debounceJob?.cancel()
            debounceJob = scope.launch {
                delay(waitMs)
                destinationFunction(param)
            }
        }
    }

    val debounceTextChange = debounce<String>(scope = viewModelScope) {
        launchSafely {
            if (lastSearch != it) {
                if (it == "")
                    update()
                else
                    repository.getSearched(viewModelScope, it).collectLatest {
                        listCreated.postValue(it)
                    }
            }
        }
        lastSearch = it
    }
}