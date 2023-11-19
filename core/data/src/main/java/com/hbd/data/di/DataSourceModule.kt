package com.hbd.data.di

import com.hbd.data.remote.api.LoginService
import com.hbd.data.remote.datasource.UserDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideUserDataSource(
        loginService: LoginService
    ): UserDatasource {
        return UserDatasource(loginService)
    }
}