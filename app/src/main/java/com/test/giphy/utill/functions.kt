package com.test.giphy.utill

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import com.test.giphy.App.Companion.BLACK_LIST_KEY
import com.test.giphy.App.Companion.sharedPref
import com.test.giphy.data.model.Data
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

fun getSharedPref() =
    sharedPref?.getStringSet(BLACK_LIST_KEY, null)?.toMutableSet()

fun putSharedPref(data: Data) {
    val setData = getSharedPref()?: mutableSetOf()
    setData.add(data.id)

    sharedPref?.edit()?.putStringSet(BLACK_LIST_KEY, setData)?.apply()
}

private fun Context.isNetworkConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
}