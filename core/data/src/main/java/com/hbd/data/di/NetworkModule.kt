package com.hbd.data.di

import com.hbd.data.RetrofitFactory
import com.hbd.data.remote.api.LoginService
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
}