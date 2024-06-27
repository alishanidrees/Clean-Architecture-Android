package com.perfectlypressed.network

import com.perfectlypressed.network.callApi

interface BaseRepository {
    suspend fun <T> execute(apiFunction: suspend () -> T): ApiResult<T> =
        callApi { apiFunction.invoke() }

}
