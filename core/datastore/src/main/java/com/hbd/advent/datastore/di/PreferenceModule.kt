package com.hbd.advent.datastore.di

import android.content.Context
import com.hbd.advent.datastore.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {
    @Provides
    fun providePreferenceManager(
        @ApplicationContext applicationContext: Context
    ): PreferenceManager {
        return PreferenceManager(applicationContext)
    }
}