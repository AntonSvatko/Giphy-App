package com.test.giphy.di.module

import android.content.Context
import com.test.giphy.di.qualifier.ApiOkHttpClient
import com.test.giphy.network.interceptor.ApiQueryInterceptor
import com.test.giphy.network.interceptor.ConnectivityInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OkHttpModule {

    @Provides
    @Singleton
    fun provideCache(file: File): Cache = Cache(file, 50 * 1024 * 1024)

    @Provides
    @Singleton
    fun provideFile(@ApplicationContext context: Context): File =
        File(context.cacheDir, "ImagesAppCache")

    @Provides
    @Singleton
    @ApiOkHttpClient
    fun provideApiClient(
        connectivityInterceptor: ConnectivityInterceptor,
        apiQueryInterceptor: ApiQueryInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(connectivityInterceptor)
            .addInterceptor(apiQueryInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideConnectivityInterceptor(@ApplicationContext context: Context): ConnectivityInterceptor =
        ConnectivityInterceptor(context)

    @Provides
    @Singleton
    fun provideApiQueryInterceptor(): ApiQueryInterceptor =
        ApiQueryInterceptor()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE

        return httpLoggingInterceptor
    }
}