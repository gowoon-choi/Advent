package com.hbd.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val token: String
)