package com.hbd.data.repository

import com.hbd.data.remote.datasource.CommonDatasource
import com.hbd.domain.repository.CommonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.hbd.advent.common.model.Result

class CommonRepositoryImpl @Inject constructor(
    private val commonDatasource: CommonDatasource
): CommonRepository {
    override suspend fun getDailySentence(): Flow<Result<String>> {
        return commonDatasource.getDailySentence().map {
            when(it){
                is Result.Success -> {
                    Result.Success(it.data ?: "")
                }
                is Result.Error -> {
                    Result.Error(it.exception)
                }
            }
        }
    }
}