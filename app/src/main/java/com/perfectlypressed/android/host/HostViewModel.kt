package com.perfectlypressed.android.host

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Handler
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.MenuRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appsflyer.AppsFlyerLib
import com.perfectlypressed.android.base.domain.BaseViewModel
import com.perfectlypressed.android.ui.header.HeaderConfig
import com.perfectlypressed.android.ui.header.HeaderRightButtonType
import com.tawkeel.base.data.FragmentConfig
import com.tawkeel.base.domain.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class HostViewModel @Inject constructor(
    @ApplicationContext private val context: Context,

) : BaseViewModel() {

    val showBottomNavigation: LiveData<Boolean>
        get() = _showBottomNavigation
    private val _showBottomNavigation: SingleLiveEvent<Boolean> by lazy {
        SingleLiveEvent()
    }
    val restartActivityLiveData: LiveData<Unit>
        get() = _restartActivityLiveData
    private val _restartActivityLiveData: SingleLiveEvent<Unit> by lazy {
        SingleLiveEvent()
    }

    val appHeaderLiveData: LiveData<HeaderConfig>
        get() = _appHeaderLiveData
    private val _appHeaderLiveData: MutableLiveData<HeaderConfig> by lazy {
        MutableLiveData()
    }

    val performerBackgroundImageLiveData: LiveData<Boolean>
        get() = _performerBackgroundLiveData
    private val _performerBackgroundLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    val progressLiveData: LiveData<Int>
        get() = _progressLiveData
    val _progressLiveData: MutableLiveData<Int> by lazy {
        MutableLiveData(0)
    }
    val fragNavContainerTopMargin: LiveData<Int>
        get() = _fragNavContainerTopMargin
    private val _fragNavContainerTopMargin: MutableLiveData<Int> by lazy {
        MutableLiveData()
    }
    private fun postShowBottomNavigation(show: Boolean) {
        _showBottomNavigation.value = show
    }
    private fun postAppHeaderUiData(headerConfig: HeaderConfig) {
        _appHeaderLiveData.value = headerConfig
    }
    private fun postFragNavContainerTopMargin(height: Int) {
        _fragNavContainerTopMargin.value = height
    }

    fun postFragmentConfig(fragmentConfig: FragmentConfig) {
        postShowBottomNavigation(fragmentConfig.showFooter)
        postAppHeaderUiData(fragmentConfig.headerConfig)
        postFragNavContainerTopMargin(fragmentConfig.fragNavContainerTopMargin)
    }
    fun setLoadingFromHost(loading: Boolean) {
        setLoading(loading)
    }
    fun updateCurrentDisplayMode() {
       // lightDarkModeManager.setCurrentMode()
    }


    override fun getHeaderConfig(
        background: Drawable?,
        title: String,
        rightButtonType: HeaderRightButtonType,
        showBackButton: Boolean,
        showFreshDeskIcon: Boolean,
        chatUserTitle: String,
        chatUserUrl: String
    ): HeaderConfig {
        TODO("Not yet implemented")
    }
}
