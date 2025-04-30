package com.apps.histopia.core.remote.interceptor

import com.apps.histopia.core.remote.api.BaseApiService
import okhttp3.Interceptor
import okhttp3.Response


class HeaderInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader(BaseApiService.HEADER_CONTENT_TYPE,
            BaseApiService.DEFAULT_CONTENT_TYPE).addHeader(BaseApiService.HEADER_ACCEPT,
            BaseApiService.DEFAULT_CONTENT_TYPE).build()
        return  chain.proceed(request)
    }
}