package com.hbd.domain.usecase.common

import com.hbd.domain.repository.CommonRepository
import kotlinx.coroutines.flow.Flow
import com.hbd.advent.common.model.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDailySentenceUseCase @Inject constructor(
    private val commonRepository: CommonRepository
) {
    suspend operator fun invoke(): Flow<Result<String>> {
        return commonRepository.getDailySentence()
    }
}