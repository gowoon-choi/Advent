package com.hbd.data.model.response.user

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val userId: Long,
    val nickname: String,
    val accessToken: String,
    val accessTokenExpiresIn: String,
    val newUser: Boolean
)
