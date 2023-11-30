package com.hbd.data.model.request.user

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val token: String
)