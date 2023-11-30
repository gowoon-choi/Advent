package com.hbd.data.remote.api

import com.hbd.data.model.request.user.LoginRequest
import com.hbd.data.model.response.AdventResponse
import com.hbd.data.model.response.user.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/user/login/oauth/KAKAO")
    suspend fun login(@Body loginRequest: LoginRequest): Response<AdventResponse<LoginResponse>>
}