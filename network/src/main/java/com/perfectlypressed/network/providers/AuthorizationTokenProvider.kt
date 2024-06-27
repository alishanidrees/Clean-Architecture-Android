package com.perfectlypressed.network.providers

import com.perfectlypressed.network.NetworkPreferencesManager
import javax.inject.Inject
import javax.inject.Provider

private const val BEARER_TOKEN_TYPE = "Bearer "

interface AuthorizationTokenProvider : Provider<String>

class DefaultAuthorizationTokenProvider @Inject constructor(
    private val networkPreferencesManager: NetworkPreferencesManager
) : AuthorizationTokenProvider {

    override fun get(): String =
        """$BEARER_TOKEN_TYPE${
            networkPreferencesManager.getAuthToken()
        }"""

}