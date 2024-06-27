package com.perfectlypressed.network.servicetype
import com.perfectlypressed.network.BuildConfig

sealed class NetworkServiceType(val baseURL: String) {
    data object PerfectlyPressed : NetworkServiceType(BuildConfig.APP_BASE_URL)
}
