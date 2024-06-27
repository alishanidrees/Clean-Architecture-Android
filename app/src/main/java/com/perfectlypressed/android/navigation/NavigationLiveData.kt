package com.tawkeel.navigation

import android.content.Intent
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import com.tawkeel.base.data.ActivityResultContractData
import com.tawkeel.base.domain.SingleLiveEvent
import javax.inject.Inject

class NavigationLiveData @Inject constructor() : SingleLiveEvent<FragmentNavigator>()

sealed class FragmentNavigator {
    data class Push(val navDirections: NavDirections) : FragmentNavigator()
    data class BottomNavItem(@IdRes val menuItemId: Int) : FragmentNavigator()
    data class ViaIntent(val intent: Intent) : FragmentNavigator()
    data class LaunchActivityResultContract<I>(
        val launchResult: ActivityResultContractData.Launch<I>
    ) : FragmentNavigator()

    object Pop : FragmentNavigator()
    object Finish : FragmentNavigator()
    object SupportService : FragmentNavigator()
}