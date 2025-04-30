package com.apps.histopia.core.remote.response
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
sealed class BaseResponse<T> {
    @Serializable
    data class Success<T>(
        @SerialName("status")
        val status: Boolean = true,

        @SerialName("message")
        val message: String? = null,

        @SerialName("data")
        val data: T? = null
    ) : BaseResponse<T>()

    @Serializable
    data class Error(
        @SerialName("status")
        val status: Boolean = false,

        @SerialName("message")
        val message: String? = null,

        @SerialName("error_code")
        val errorCode: String? = null
    ) : BaseResponse<Nothing>()

    // Helper properties
    val isSuccess get() = this is Success
    val isError get() = this is Error

    // Helper functions
    fun data(): T? = when (this) {
        is Success -> data
        is Error -> null
    }

    fun error(): String? = when (this) {
        is Success -> null
        is Error -> message
    }
}


fun <T> BaseResponse<T>.onSuccess(action: (T?) -> Unit): BaseResponse<T> {
    if (this is BaseResponse.Success) {
        action(data)
    }
    return this
}

fun <T> BaseResponse<T>.onError(action: (String?) -> Unit): BaseResponse<T> {
    if (this is BaseResponse.Error) {
        action(message)
    }
    return this
}