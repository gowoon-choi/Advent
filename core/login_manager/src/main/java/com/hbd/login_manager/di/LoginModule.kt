package com.hbd.login_manager.di

import android.content.Context
import com.hbd.advent.datastore.PreferenceManager
import com.hbd.login_manager.LoginManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {
    @Provides
    fun provideLoginManager(
        @ApplicationContext applicationContext: Context
    ) : LoginManager {
        return LoginManager(applicationContext)
    }
}