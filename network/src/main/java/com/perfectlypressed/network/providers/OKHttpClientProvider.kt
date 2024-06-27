package com.perfectlypressed.network.providers

import com.perfectlypressed.network.interceptor.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

interface OKHttpClientProvider : Provider<OkHttpClient.Builder>

class DefaultOKHttpClientProvider @Inject constructor(
    private val customHttpLoggingInterceptor: HttpLoggingInterceptor,
) : OKHttpClientProvider {

    private companion object {
        const val TIMEOUT = 60
    }

    override fun get(): OkHttpClient.Builder {
        val loggingInterceptor = okhttp3.logging.HttpLoggingInterceptor()
        loggingInterceptor.level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .callTimeout(TIMEOUT.toLong(),TimeUnit.SECONDS)
            .addNetworkInterceptor(loggingInterceptor)
    }
}
