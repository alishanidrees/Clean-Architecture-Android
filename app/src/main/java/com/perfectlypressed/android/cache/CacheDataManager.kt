package com.perfectlypressed.android.cache

import javax.inject.Inject

interface CacheDataManager {
    fun getPrimaryButtonClicked():Boolean
    fun setPrimaryButtonClicked(isClicked: Boolean)

}

class DefaultCacheDataManager @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager,
) : CacheDataManager {

    override fun getPrimaryButtonClicked(): Boolean {
        return sharedPreferencesManager.getPrimaryClicked()
    }
    override fun setPrimaryButtonClicked(isClicked: Boolean) {
        sharedPreferencesManager.setPrimaryClicked(isClicked)
    }

}


