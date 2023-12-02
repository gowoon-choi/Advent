package com.hbd.data

import com.hbd.advent.datastore.PreferenceManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
object RetrofitFactory {
    const val BASE_URL = BuildConfig.BASE_URL

    val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    val jsonConverter = json.asConverterFactory("application/json".toMediaType())

    inline fun <reified T> create(preferenceManager: PreferenceManager): T{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(jsonConverter)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .addInterceptor(RequestInterceptor(preferenceManager))
                    .build()
            )
            .build()
            .create(T::class.java)
    }

    inline fun <reified T> createWithoutRequestToken(): T{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(jsonConverter)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
            )
            .build()
            .create(T::class.java)
    }
}