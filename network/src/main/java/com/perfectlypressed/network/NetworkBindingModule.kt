package com.perfectlypressed.network

import com.perfectlypressed.network.providers.AppLocaleProvider
import com.perfectlypressed.network.providers.DefaultAppLocaleProvider
import com.perfectlypressed.network.interceptor.CustomHttpLoggingInterceptor
import com.perfectlypressed.network.interceptor.HttpLoggingInterceptor
import com.perfectlypressed.network.servicetype.DefaultPerfectlyPressedRetrofitProvider
import com.perfectlypressed.network.interceptor.PerfectlyPressedApiHeaderInterceptor
import com.perfectlypressed.network.providers.AuthorizationTokenProvider
import com.perfectlypressed.network.providers.DefaultAuthorizationTokenProvider
import com.perfectlypressed.network.providers.DefaultOKHttpClientProvider
import com.perfectlypressed.network.providers.DefaultPerfectlyPressedApiHeadersProvider
import com.perfectlypressed.network.providers.OKHttpClientProvider
import com.perfectlypressed.network.providers.PerfectlyPressedApiHeadersProvider
import com.perfectlypressed.network.servicetype.PerfectlyPressedRetrofitProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkBindingModule {

    @Binds
    fun bindOKHttpClientProvider(default: DefaultOKHttpClientProvider): OKHttpClientProvider

    @Binds
    fun bindHttpLoggingInterceptor(default: CustomHttpLoggingInterceptor): HttpLoggingInterceptor

    @Binds
    fun bindPerfectlyPressedApiHeaderInterceptor(default: DefaultPerfectlyPressedRetrofitProvider): PerfectlyPressedRetrofitProvider

    @Binds
    fun bindPerfectlyPressedApiHeadersProvider(default: DefaultPerfectlyPressedApiHeadersProvider): PerfectlyPressedApiHeadersProvider

    @Binds
    fun bindAppLocaleProvider(default: DefaultAppLocaleProvider): AppLocaleProvider

    @Binds
    @Singleton
    fun bindAuthorizationTokenProvider(default: DefaultAuthorizationTokenProvider): AuthorizationTokenProvider

    @Binds
    @Singleton
    fun bindNetworkPreferencesManager(default: DefaultNetworkPreferencesManager): NetworkPreferencesManager

    @Binds
    fun bindNetworkUtils(default: DefaultNetworkUtils): NetworkUtils
}
