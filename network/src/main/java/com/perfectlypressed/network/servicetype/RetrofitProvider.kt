package com.perfectlypressed.network.servicetype

import retrofit2.Retrofit
import javax.inject.Provider

interface RetrofitProvider<NetworkServiceType> : Provider<Retrofit> {
    val serviceType: NetworkServiceType
}

interface PerfectlyPressedRetrofitProvider : RetrofitProvider<NetworkServiceType> {
    override val serviceType: NetworkServiceType
        get() = NetworkServiceType.PerfectlyPressed
}

