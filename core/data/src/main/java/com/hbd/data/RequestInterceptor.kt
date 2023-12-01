package com.hbd.data

import com.hbd.advent.datastore.PreferenceManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor @Inject constructor(
    private val preferenceManager: PreferenceManager
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response  {
        val request = chain.request().newBuilder().apply {
            runBlocking {
                preferenceManager.userToken.first()?.let {
                    header("Authorization", it)
                }
            }
        }.build()
        return chain.proceed(request)
    }
}