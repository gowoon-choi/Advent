package com.hbd.data.model.request.user

import kotlinx.serialization.Serializable

@Serializable
data class NicknameRequest(
    val nickname: String
)
