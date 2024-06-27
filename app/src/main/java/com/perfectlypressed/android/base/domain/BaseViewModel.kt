package com.perfectlypressed.android.base.domain

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perfectlypressed.android.ui.click_callback.ClickCallback
import com.perfectlypressed.android.ui.header.BackButtonConfig
import com.perfectlypressed.android.ui.header.ChatImageTitleConfig
import com.perfectlypressed.android.ui.header.HeaderConfig
import com.perfectlypressed.android.ui.header.HeaderRightButtonConfig
import com.perfectlypressed.android.ui.header.HeaderRightButtonType
import com.perfectlypressed.android.ui.header.TitleConfig
import com.perfectlypressed.android.base.data.UIMessage
import com.perfectlypressed.android.base.data.ViewState
import com.perfectlypressed.android.base.data.unZipViewState
import com.tawkeel.base.domain.SingleLiveEvent
import com.tawkeel.navigation.FragmentNavigator
import com.tawkeel.navigation.NavigationLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CustomScreenHeader {

    val navigationLiveData: LiveData<FragmentNavigator>
        get() = _navigationLiveData
    private val _navigationLiveData: NavigationLiveData by lazy {
        NavigationLiveData()
    }

    val isChatButtonTap: LiveData<Boolean>
        get() = _isChatButtonTap
    val _isChatButtonTap: SingleLiveEvent<Boolean> by lazy {
        SingleLiveEvent()
    }

    val isSplash: LiveData<Boolean>
        get() = _isSplash
    private val _isSplash: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    val uiMessageLiveData: LiveData<UIMessage>
        get() = _uiMessageLiveData
    private val _uiMessageLiveData: SingleLiveEvent<UIMessage> by lazy {
        SingleLiveEvent()
    }

    val isSeeker: LiveData<Boolean>
        get() = _isSeeker
    val _isSeeker: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData
    private val _loadingLiveData: SingleLiveEvent<Boolean> by lazy {
        SingleLiveEvent()
    }

    protected open fun getBackButtonConfig(showBackButton: Boolean): BackButtonConfig =
        BackButtonConfig(
            canShowBackButton = showBackButton,
            clickCallback = object : ClickCallback<Unit> {
                override fun onClick(type: Unit) {
                   // navigateBack()
                }
            }
        )

    private fun getTitleConfig(title: String): TitleConfig = TitleConfig.DEFAULT.copy(title = title)
    private fun getChatTitleConfig(title: String, imageUrl: String): ChatImageTitleConfig =
        ChatImageTitleConfig.DEFAULT.copy(titleName = title, imageUrl = imageUrl)

   /* protected fun navigate(navDirections: NavDirections) {
        _navigationLiveData.value = FragmentNavigator.Push(navDirections)
    }


    protected fun navigate(intent: Intent) {
        _navigationLiveData.value = FragmentNavigator.ViaIntent(intent)
    }

    protected fun <I> navigateFromActivityContract(activityResultContractData: ActivityResultContractData.Launch<I>) {
        _navigationLiveData.value =
            FragmentNavigator.LaunchActivityResultContract(activityResultContractData)
    }

    fun navigateBack() {
        _navigationLiveData.value = FragmentNavigator.Pop
    }

    protected fun finishActivity() {
        _navigationLiveData.value = FragmentNavigator.Finish
    }

    protected fun navigateToBottomNavItem(@IdRes idRes: Int) {
        _navigationLiveData.value = FragmentNavigator.BottomNavItem(idRes)
    }
*/
    protected open fun launch(
        dispatcher: CoroutineContext = Dispatchers.Main,
        block: suspend () -> Unit
    ) {
        viewModelScope.launch(dispatcher) {
            block()
        }
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
        return HeaderConfig.DEFAULT.copy(
            backButtonConfig = getBackButtonConfig(showBackButton),
            titleConfig = getTitleConfig(title),
            headerRightButtonConfig = getHeaderRightButtonConfig(rightButtonType),
            itemBackground = background,
            chatTitleConfig = getChatTitleConfig(chatUserTitle, chatUserUrl)
        )
    }

    private fun getHeaderRightButtonConfig(headerRightButtonType: HeaderRightButtonType): HeaderRightButtonConfig =
        HeaderRightButtonConfig(
            clickCallback = object : ClickCallback<HeaderRightButtonType> {
                override fun onClick(type: HeaderRightButtonType) {
                   /* when (type) {
                        is HeaderRightButtonType.Notification ->
                            navigate(LoginFragmentDirections.globalActionNotificationsListing())
                        HeaderRightButtonType.Home -> if (isSeeker.value.default) navigate(
                            LoginFragmentDirections.globalActionSeekerHome()
                        ) else navigate(LoginFragmentDirections.globalActionPerformerHome())

                        else -> {
                            //
                        }
                    }*/
                }
            },
            headerRightButtonType = headerRightButtonType,
        )

   /* protected open fun getBackButtonConfig(showBackButton: Boolean): BackButtonConfig =
        BackButtonConfig(
            canShowBackButton = showBackButton,
            clickCallback = object : ClickCallback<Unit> {
                override fun onClick(type: Unit) {
                    navigateBack()
                }
            }
        )

    private fun getTitleConfig(title: String): TitleConfig = TitleConfig.DEFAULT.copy(title = title)
    private fun getChatTitleConfig(title: String, imageUrl: String): ChatImageTitleConfig =
        ChatImageTitleConfig.DEFAULT.copy(titleName = title, imageUrl = imageUrl)

    private fun getHeaderRightButtonConfig(headerRightButtonType: HeaderRightButtonType): HeaderRightButtonConfig =
        HeaderRightButtonConfig(
            clickCallback = object : ClickCallback<HeaderRightButtonType> {
                override fun onClick(type: HeaderRightButtonType) {
                    when (type) {
                        is HeaderRightButtonType.Notification -> navigate(LoginFragmentDirections.globalActionNotificationsListing())
                        HeaderRightButtonType.Home -> if (isSeeker.value.default) navigate(
                            LoginFragmentDirections.globalActionSeekerHome()
                        ) else navigate(LoginFragmentDirections.globalActionPerformerHome())

                        else -> {
                            //
                        }
                    }
                }
            },
            headerRightButtonType = headerRightButtonType,
        )


    protected open fun getFreshDeskIconConfig(showFreshDeskIconConfig: Boolean): FreshDeskIconConfig =
        FreshDeskIconConfig(
            canShowFreshDeskIcon = showFreshDeskIconConfig,
            iconRes = R.drawable.ic_support_clear,
            clickCallback = object : ClickCallback<Unit> {
                override fun onClick(type: Unit) {
                    startSupportService()
                }
            }
        )

    protected fun startSupportService() {
        _isChatButtonTap.value = true
//        _navigationLiveData.value = FragmentNavigator.SupportService
    }

    protected fun navigateWithDelay(
        navDirections: NavDirections,
        delay: Long = Constants.SCREEN_TRANSITIONS_DELAY
    ) {
        launch {
            delay(delay)
            navigate(navDirections)
        }
    }

    protected fun navigateWithDelay(@IdRes bottomNavId: Int) {
        launch {
            delay(Constants.SCREEN_TRANSITIONS_DELAY)
            navigateToBottomNavItem(bottomNavId)
        }
    }*/

    protected fun setLoading(loading: Boolean) {
        _loadingLiveData.value = loading
    }

    protected fun setSplashLoaderStopped(loading: Boolean) {
        _isSplash.value = loading
    }

    protected fun setUiMessage(uiMessage: UIMessage) {
        _uiMessageLiveData.value = uiMessage
    }

    protected fun <T> unZipViewState(
        flow: Flow<ViewState<T>>,
        error: UIMessage.() -> Unit = {},
        success: T.() -> Unit
    ) {
        launch {
            flow.unZipViewState(
                loading = {
                    if (isSplash.value!!) {
                        setLoading(false)
                    } else {
                        setLoading(this)
                    }
                },
                error = {
                    error.invoke(this)
                    setLoading(false)
                    setUiMessage(this)
                },
                success = {
                    setLoading(false)
                    success(this)
                }
            )
        }
    }

    /*protected fun onAppError(appError: AppError) {
        when (appError) {
            AppError.PerformerNotFound,
            AppError.AccountError.UserNotFound,
            AppError.AccountError.UnAuthorizedUser -> {
                navigate(SplashFragmentDirections.login())
            }

            else -> return
        }
    }

    protected fun showGeneralDialogUiData(generalDialogUiData: GeneralDialogUiData) {
        setUiMessage(
            UIMessage.DialogMessage(
                DialogMessageType.GeneralDialog(generalDialogUiData)
            )
        )
    }*/
}
