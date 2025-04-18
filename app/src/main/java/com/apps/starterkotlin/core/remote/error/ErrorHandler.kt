package com.apps.starterkotlin.core.remote.error

import com.apps.starterkotlin.core.remote.result.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ErrorHandler {
    companion object {
        suspend fun <T> handle(statusCode: Int, data: T?): ApiResult<T> {
            if (!isInternetAvailable()) {
                return ApiResult.error(
                    AppException.NoInternet("No internet connection")
                )
            }

            return when (statusCode) {
                200, 201, 204 -> ApiResult.success(data as T)
                400 -> ApiResult.error(
                    AppException.BadRequest("Bad request")
                )
                401 -> ApiResult.error(
                    AppException.Unauthorized("Unauthorized access")
                )
                404 -> ApiResult.error(
                    AppException.NotFound("Resource not found")
                )
                500, 502 -> ApiResult.error(
                    AppException.ServerError("Internal server error")
                )
                else -> ApiResult.error(
                    AppException.NetworkError("Error code: $statusCode")
                )
            }
        }

        private suspend fun isInternetAvailable(): Boolean = withContext(Dispatchers.IO) {
            try {
                val runtime = Runtime.getRuntime()
                val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
                val exitValue = ipProcess.waitFor()
                exitValue == 0
            } catch (e: Exception) {
                false
            }
        }
    }
}
