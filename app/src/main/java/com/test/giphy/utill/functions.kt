package com.test.giphy.utill

import android.app.Activity
import android.graphics.Insets
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import kotlinx.coroutines.CoroutineExceptionHandler


inline fun createCoroutineHandler(crossinline onError: (Throwable?) -> Unit) =
    CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }