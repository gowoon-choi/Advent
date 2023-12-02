package com.hbd.data.remote.datasource

import com.hbd.data.remote.api.CommonService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.hbd.advent.common.model.Result
import com.hbd.data.remote.common.AdventResCode

class CommonDatasource @Inject constructor(
    private val commonService: CommonService
) {
    suspend fun getDailySentence(): Flow<Result<String>> {
        return flow { emit(commonService.getDailySentence()) }.map {
            if(it.isSuccessful){
                it.body()?.let { response ->
                    if(response.status.resCode == AdventResCode.HBD200){
                        Result.Success(response.data)
                    } else {
                        Result.Error(Exception(response.status.resMessage))
                    }
                } ?: Result.Error(Exception())
            } else {
                Result.Error(Exception(it.message()))
            }
        }
    }
}