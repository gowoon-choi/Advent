package com.hbd.data.di

import com.hbd.data.remote.datasource.UserDatasource
import com.hbd.data.repository.CalendarRepositoryImpl
import com.hbd.data.repository.UserRepositoryImpl
import com.hbd.domain.repository.CalendarRepository
import com.hbd.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RespositoryModule {
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindCalendarRepository(
        calendarRepositoryImpl: CalendarRepositoryImpl
    ): CalendarRepository
}