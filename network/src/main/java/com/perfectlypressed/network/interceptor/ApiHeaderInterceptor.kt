package com.perfectlypressed.network.interceptor

import com.perfectlypressed.network.providers.DefaultPerfectlyPressedApiHeadersProvider.Companion.AUTHORIZATION
import com.perfectlypressed.network.providers.DefaultPerfectlyPressedApiHeadersProvider.Companion.DEVICE_ID
import com.perfectlypressed.network.providers.PerfectlyPressedApiHeadersProvider
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

interface PerfectlyPressedApiHeaderInterceptor : Interceptor

class DefaultPerfectlyPressedApiHeaderInterceptor @Inject constructor(
    private val perfectlyPressedApiHeadersProvider: PerfectlyPressedApiHeadersProvider,
) : PerfectlyPressedApiHeaderInterceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().apply {
                perfectlyPressedApiHeadersProvider.get().forEach { entry ->
                    if (entry.key != DEVICE_ID || !chain.call().request().url.toString().contains("payfort-token")) {
                        Timber.d("${entry.key} -- ${entry.value}")
                        addHeader(entry.key, entry.value)
                    }
                }
                if (removeToken(chain))
                    removeHeader(AUTHORIZATION)

            }.build()
        )
    }

    private fun removeToken(chain: Interceptor.Chain) =
        chain.call().request().url.toString().contains("refresh-token")
}
