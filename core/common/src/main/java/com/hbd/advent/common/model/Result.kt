package com.hbd.advent.common.model

import java.lang.Exception

sealed class Result<out T> {
    class Success<T>(val data: T): Result<T>()
    class Error(val exception: Exception): Result<Nothing>()
}