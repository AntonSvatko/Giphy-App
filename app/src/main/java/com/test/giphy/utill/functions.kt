package com.test.giphy.utill

import kotlinx.coroutines.CoroutineExceptionHandler


inline fun createCoroutineHandler(crossinline onError: (Throwable?) -> Unit) =
    CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

//fun bindFailedToast(progressBar: ProgressBar, throwable: Throwable?) {
//    throwable?.let {
//        val context = progressBar.context
//        val resources = context.resources
//
//        val text = when (it) {
//            is NoConnectivityException -> resources.getString(R.string.failed_connection)
//            else -> resources.getString(R.string.failed_to_load)
//        }
//
//        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
//    }
//}
