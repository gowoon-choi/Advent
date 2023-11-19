package com.hbd.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val userId: Long,
    val accessToken: String,
    val accessTokenExpiresIn: String,
    val newUser: Boolean
)
