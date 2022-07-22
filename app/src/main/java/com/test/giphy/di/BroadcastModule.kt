package com.test.giphy.di

import android.content.Context
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object BroadcastModule {

    @ActivityRetainedScoped
    @Provides
    fun provideLocalManager(
        @ApplicationContext context: Context
    ) = LocalBroadcastManager.getInstance(context)

}