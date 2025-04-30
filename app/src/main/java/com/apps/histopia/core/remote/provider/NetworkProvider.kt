package com.apps.histopia.core.remote.provider

import com.apps.histopia.BuildConfig
import com.apps.histopia.core.remote.interceptor.HeaderInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit



object NetworkProvider {
    private const val TIMEOUT = 30L

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        prettyPrint = true
    }

    private val okHttpClient =
        OkHttpClient.Builder().addInterceptor(HeaderInterceptor()).also { client ->
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(logging)
            }
        }
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()

    fun provideRetrofit(baseUrl: String) : Retrofit{
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder().
        baseUrl(baseUrl).
        client(okHttpClient).
        addConverterFactory(
            json.asConverterFactory(contentType)).
        build()
    }
    fun<T> createService(service: Class<T>, baseUrl: String): T {
        return  provideRetrofit(baseUrl).create(service)
    }

}