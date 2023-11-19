package com.hbd.data.remote.datasource

import com.hbd.data.model.response.LoginResponse
import com.hbd.data.remote.api.LoginService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.hbd.advent.common.model.Result
import com.hbd.data.model.request.LoginRequest
import timber.log.Timber
import java.lang.Exception

class UserDatasource @Inject constructor(
    private val loginService: LoginService
) {
    suspend fun login(token: String): Flow<Result<LoginResponse>> {
        return flow { emit(loginService.login(LoginRequest(token))) }.map {
            if(it.isSuccessful){
                it.body()?.let { body ->
                    Result.Success(body.data)
                } ?: Result.Error(Exception())
            } else {
                Timber.d(it.errorBody().toString())
                Timber.d(it.body()?.status.toString())
                Result.Error(Exception(it.message()))
            }
        }
    }
}