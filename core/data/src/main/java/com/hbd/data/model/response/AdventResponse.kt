package com.hbd.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class AdventResponse<T>(
    val status: Status,
    val data: T? = null
)

@Serializable
data class Status(
    val resCode: String,
    val resMessage: String
)