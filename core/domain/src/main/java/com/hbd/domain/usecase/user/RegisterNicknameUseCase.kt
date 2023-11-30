package com.hbd.domain.usecase.user

import com.hbd.advent.common.model.Result
import com.hbd.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterNicknameUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(nickname: String): Flow<Result<Nothing?>>{
        return userRepository.setUserNickname(nickname)
    }
}