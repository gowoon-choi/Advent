package com.hbd.data.remote.api

import com.hbd.data.model.response.AdventResponse
import com.hbd.data.model.response.common.DailySentence
import retrofit2.Response
import retrofit2.http.GET

interface CommonService {
    @GET("/dailysentence")
    suspend fun getDailySentence(): Response<AdventResponse<DailySentence>>
}