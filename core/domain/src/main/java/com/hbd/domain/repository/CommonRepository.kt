package com.hbd.domain.repository

import kotlinx.coroutines.flow.Flow
import com.hbd.advent.common.model.Result

interface CommonRepository {
    suspend fun getDailySentence(): Flow<Result<String>>
}