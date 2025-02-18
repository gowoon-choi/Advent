package com.hbd.data.mapper

import com.hbd.data.model.response.user.LoginResponse
import com.hbd.domain.model.User

object UserMapper: MapperToDomain<LoginResponse, User> {
    override fun LoginResponse.toDomain(): User {
        return User(token = accessToken, nickname = nickname, newUser = newUser)
    }
}