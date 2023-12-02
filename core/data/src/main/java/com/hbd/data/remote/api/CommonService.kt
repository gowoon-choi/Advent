package com.hbd.data.remote.api

import com.hbd.data.model.response.AdventResponse
import retrofit2.Response
import retrofit2.http.GET

interface CommonService {
    @GET("/dailysentence")
    suspend fun getDailySentence(): Response<AdventResponse<String>>
}