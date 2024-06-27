package com.perfectlypressed.android.splash.domain

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.perfectlypressed.android.base.domain.BaseViewModel
import com.perfectlypressed.android.base.domain.ScreenAnalytics
import com.perfectlypressed.android.extentions.EMPTY_STRING
import com.perfectlypressed.android.ui.header.HeaderConfig
import com.perfectlypressed.android.ui.header.HeaderRightButtonType
import com.tawkeel.base.domain.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

    ) : BaseViewModel(), ScreenAnalytics {

    val onAccountTypeSelection: LiveData<Unit>
        get() = _onAccountTypeSelection
    private val _onAccountTypeSelection: SingleLiveEvent<Unit> by lazy {
        SingleLiveEvent()
    }
    val notificationsRequestPermissions: LiveData<Boolean>
        get() = _notificationsRequestPermissions
    private val _notificationsRequestPermissions: SingleLiveEvent<Boolean> by lazy {
        SingleLiveEvent()
    }
    private val canShowOffersTab: LiveData<Boolean>
        get() = showOffersTab
    val showOffersTab: MutableLiveData<Boolean> by lazy {
        MutableLiveData()
    }




    fun onInit() {
        setSplashLoaderStopped(true)
    }



    override fun trackScreenAnalyticsData() {

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
        return super.getHeaderConfig(
            background,
            title,
            rightButtonType,
            showBackButton = false,
            showFreshDeskIcon = false,
            chatUserTitle = EMPTY_STRING,
            chatUserUrl = EMPTY_STRING
        )
    }


}
