package com.hbd.data.di

import com.hbd.data.remote.api.CalendarService
import com.hbd.data.remote.api.LoginService
import com.hbd.data.remote.api.UserService
import com.hbd.data.remote.datasource.CalendarDatasource
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
        loginService: LoginService,
        userService: UserService
    ): UserDatasource {
        return UserDatasource(loginService, userService)
    }

    @Singleton
    @Provides
    fun provideCalendarDatasource(
        calendarService: CalendarService
    ): CalendarDatasource {
        return CalendarDatasource(calendarService)
    }
}