package com.hbd.domain.repository

import com.hbd.advent.common.model.Result
import com.hbd.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserInfo(token: String): Flow<Result<User>>
    suspend fun setUserToken(token: String)
    suspend fun hasUserToken(): Flow<Result<Boolean>>
    suspend fun getUserNickname(): Flow<Result<String?>>
}