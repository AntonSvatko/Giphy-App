package com.test.giphy.utill

import android.app.Activity
import android.graphics.Bitmap
import kotlinx.coroutines.CoroutineExceptionHandler
import java.io.FileOutputStream
import java.io.IOException


inline fun createCoroutineHandler(crossinline onError: (Throwable?) -> Unit) =
    CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }


//fun Activity.saveBitmap(bitmap: Bitmap){
//
//    try {
//        FileOutputStream(filename).use { out ->
//            bmp.compress(Bitmap.CompressFormat.PNG, 100, out) // bmp is your Bitmap instance
//        }
//    } catch (e: IOException) {
//        e.printStackTrace()
//    }
//}