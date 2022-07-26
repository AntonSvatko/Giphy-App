package com.test.giphy.utill

import com.test.giphy.App.Companion.sharedPref
import com.test.giphy.data.model.Data
import com.test.giphy.utill.Const.BLACK_LIST_KEY
import kotlinx.coroutines.CoroutineExceptionHandler


inline fun createCoroutineHandler(crossinline onError: (Throwable?) -> Unit) =
    CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

fun getSharedPref() =
    sharedPref?.getStringSet(BLACK_LIST_KEY, null)?.toMutableSet()

fun putSharedPref(data: Data) {
    val setData = getSharedPref()?: mutableSetOf()
    setData.add(data.id)

    sharedPref?.edit()?.putStringSet(BLACK_LIST_KEY, setData)?.apply()
}