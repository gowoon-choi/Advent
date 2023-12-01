package com.hbd.domain.model

data class User(
    val token: String,
    val nickname: String,
    val newUser: Boolean
)
