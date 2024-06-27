package com.tawkeel.base.data

import androidx.lifecycle.LiveData
import com.tawkeel.navigation.FragmentNavigator
import com.tawkeel.navigation.NavigationLiveData
import com.perfectlypressed.android.ui.header.HeaderConfig

data class FragmentConfig(
    val headerConfig: HeaderConfig,
    val showFooter: Boolean,
    val fragNavContainerTopMargin: Int,
    val navigationLiveData: LiveData<FragmentNavigator>
) {
    companion object {
        internal val DEFAULT =
            FragmentConfig(
                showFooter = false,
                headerConfig = HeaderConfig.DEFAULT,
                navigationLiveData = NavigationLiveData(),
                fragNavContainerTopMargin = 0
            )
    }
}