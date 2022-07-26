package com.test.giphy.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiQueryInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder()
            .build()


        val request = Request.Builder().url(url).build()
        return chain.proceed(request)
    }

}