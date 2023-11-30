package com.hbd.data.di

import com.hbd.advent.datastore.PreferenceManager
import com.hbd.data.RetrofitFactory
import com.hbd.data.remote.api.CalendarService
import com.hbd.data.remote.api.LoginService
import com.hbd.data.remote.api.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideLoginService(): LoginService{
        return RetrofitFactory.createWithoutRequestToken()
    }

    @Singleton
    @Provides
    fun provideUserService(
        preferenceManager: PreferenceManager
    ): UserService{
        return RetrofitFactory.create(preferenceManager)
    }

    @Singleton
    @Provides
    fun provideCalendarService(
        preferenceManager: PreferenceManager
    ): CalendarService{
        return RetrofitFactory.create(preferenceManager)
    }
}