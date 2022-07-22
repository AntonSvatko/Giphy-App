package com.test.giphy.util

import kotlinx.coroutines.CoroutineExceptionHandler

inline fun createCoroutineHandler(crossinline onError: (Throwable?) -> Unit) =
    CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }