package com.perfectlypressed.network.servicetype

import com.bugsee.library.Bugsee
import com.perfectlypressed.network.GsonHelper
import com.perfectlypressed.network.providers.AppLocaleProvider
import com.perfectlypressed.network.interceptor.PerfectlyPressedApiHeaderInterceptor
import com.perfectlypressed.network.providers.OKHttpClientProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Provider


class DefaultPerfectlyPressedRetrofitProvider @Inject constructor(
    private val appLocaleProvider: AppLocaleProvider,
    private val okHttpClientProvider: OKHttpClientProvider,
    private val tawkeelApiHeaderInterceptor: PerfectlyPressedApiHeaderInterceptor
) : PerfectlyPressedRetrofitProvider {

    override fun get(): Retrofit {
        val okHttpBuilder = Bugsee.addNetworkLoggingToOkHttpBuilder(okHttpClientProvider.get())

        return Retrofit.Builder()
            .baseUrl("""${serviceType.baseURL}${appLocaleProvider.getLocaleWithCountryCode()}/""")
            .client(
                okHttpBuilder
                    .addInterceptor(tawkeelApiHeaderInterceptor)
                    .build()
            ).addConverterFactory(GsonConverterFactory.create(GsonHelper.gsonIdentity)).build()
    }
}
