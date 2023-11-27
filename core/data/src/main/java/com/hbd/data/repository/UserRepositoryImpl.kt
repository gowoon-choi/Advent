package com.hbd.data.repository

import com.hbd.advent.common.model.Result
import com.hbd.advent.datastore.PreferenceManager
import com.hbd.data.mapper.UserMapper.toDomain
import com.hbd.data.remote.datasource.UserDatasource
import com.hbd.domain.model.User
import com.hbd.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val preferenceManager: PreferenceManager,
    private val userDatasource: UserDatasource
): UserRepository {
    override suspend fun getUserInfo(token: String): Flow<Result<User>> {
        return userDatasource.login(token).map {
            when(it){
                is Result.Success -> {
                    Result.Success(it.data.toDomain())
                }
                is Result.Error -> {
                    Result.Error(it.exception)
                }
            }
        }
    }

    override suspend fun setUserToken(token: String) {
        preferenceManager.setUserToken(token)
    }

    override suspend fun hasUserToken(): Flow<Result<Boolean>> {
        return flow {
            emit(Result.Success(preferenceManager.userToken.firstOrNull().isNullOrEmpty()))
        }
    }

    override suspend fun getUserNickname(): Flow<Result<String?>> {
        return flow {
            emit(Result.Success(preferenceManager.userNickname.firstOrNull()))
        }
    }
}