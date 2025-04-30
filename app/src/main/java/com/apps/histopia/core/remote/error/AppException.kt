package com.apps.histopia.core.remote.error


sealed class AppException(val message: String) {
    data class NetworkError(val errorMessage: String) : AppException(errorMessage)
    data class ServerError(val errorMessage: String) : AppException(errorMessage)
    data class NoInternet(val errorMessage: String) : AppException(errorMessage)
    data class NotFound(val errorMessage: String) : AppException(errorMessage)
    data class BadRequest(val errorMessage: String) : AppException(errorMessage)
    data class Unauthorized(val errorMessage: String) : AppException(errorMessage)
}