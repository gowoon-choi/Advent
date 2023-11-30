package com.hbd.domain.usecase.user

import com.hbd.advent.common.model.Result
import com.hbd.domain.model.User
import com.hbd.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(token: String): Flow<Result<User>>{
        return userRepository.getUserInfo(token).onEach {
            if (it is Result.Success) {
                it.data?.let { user ->
                    userRepository.setUserToken(user.token)
                }
            }
        }
    }
}