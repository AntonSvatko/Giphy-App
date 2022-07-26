package com.test.giphy

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        sharedPref = getSharedPreferences("black_list_pref", Context.MODE_PRIVATE)
    }

    companion object {
        var sharedPref: SharedPreferences? = null
    }
}