package com.hbd.data.remote.api

import com.hbd.data.model.request.user.NicknameRequest
import com.hbd.data.model.response.AdventResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT

interface UserService {
    @PUT("/user/nickname")
    suspend fun registerNickname(@Body nicknameRequest: NicknameRequest): Response<AdventResponse<Nothing>>

}