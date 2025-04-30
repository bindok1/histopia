package com.apps.histopia.core.remote.result

import com.apps.histopia.core.remote.error.AppException

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val exception: AppException) : ApiResult<Nothing>()

    companion object {
        fun <T> success(data: T): ApiResult<T> = Success(data)
        fun error(exception: AppException): ApiResult<Nothing> = Error(exception)
    }
}
