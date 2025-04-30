package com.apps.histopia.core.remote.api

interface BaseApiService {
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val HEADER_ACCEPT = "Accept"
        const val HEADER_CONTENT_TYPE = "Content-Type"

        const val DEFAULT_CONTENT_TYPE = "application/json"
    }
}
