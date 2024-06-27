package com.perfectlypressed.android.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.perfectlypressed.BR
import com.perfectlypressed.android.base.domain.BaseViewModel
import com.perfectlypressed.android.extentions.getAppHeaderHeight
import com.perfectlypressed.android.extentions.navigate
import com.perfectlypressed.android.extentions.navigateBack
import com.perfectlypressed.android.extentions.observeLiveData
import com.perfectlypressed.android.extentions.showToast
import com.perfectlypressed.android.host.HostViewModel
import com.perfectlypressed.android.base.data.UIMessage
import com.tawkeel.base.data.ActivityResultContractData
import com.tawkeel.base.data.FragmentConfig
import com.tawkeel.navigation.FragmentNavigator


abstract class BaseFragment<VB : ViewDataBinding> : Fragment() {


    protected abstract fun getBindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VB

    protected abstract val viewModel: BaseViewModel
    protected lateinit var binding: VB

    protected val hostViewModel: HostViewModel by activityViewModels()
   // protected val deepLinkViewModel: DeepLinkViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getBindView(inflater, container).apply {
            binding = this
            lifecycleOwner = viewLifecycleOwner
          //  setVariable(BR.translationManager, hostViewModel.translationManager)
            setVariable(BR.viewModel, viewModel)
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postHeaderAndFooterConfig()
        observeNavigationLiveData()
        observeLoadingAndUiMessageLiveData()
    }

    private fun observeLoadingAndUiMessageLiveData() {
        observeLiveData(viewModel.uiMessageLiveData, ::handleUiMessage)
        observeLiveData(viewModel.loadingLiveData) { canShow ->
            hostViewModel.setLoadingFromHost(canShow)
        }
    }


    protected fun handleUiMessage(uiMessage: UIMessage) {
        when (uiMessage) {
            is UIMessage.ToastMessage -> showToast(uiMessage.message)
          //  is UIMessage.DialogMessage -> handleDialogMessageType(uiMessage.dialogMessageType, uiMessage.clickCallback)
            else -> {}
        }
    }

  /*  private fun handleDialogMessageType(dialogMessageType: DialogMessageType, clickCallback: ClickCallback<Any>) {
        when(dialogMessageType){
            is DialogMessageType.Error -> {
                if (dialogMessageType.errorCode == ApiErrors.DUAL_LOGIN || dialogMessageType.errorCode == ApiErrors.DEVICE_NOT_REGISTERED || dialogMessageType.errorCode == UN_AUTH_ERROR_CODE ){
                   *//* val authOldFlowEnabled = hostViewModel.logout()
                    if (authOldFlowEnabled) {
                        navigate(HostNavGraphDirections.globalActionAccountType())
                    } else {
                        navigate(HostNavGraphDirections.globalActionPreLogin())
                    }*//*
                    return
                }
                hostViewModel.showDialog(
                    requireContext(),
                    dialogMessageType,
                    if (dialogMessageType.errorCode == ApiErrors.UN_AUTHORIZED_USER_CODE) object : ClickCallback<Any> {
                        override fun onClick(type: Any) {
                            val authOldFlowEnabled = hostViewModel.logout()
                            if (authOldFlowEnabled) {
                                navigate(HostNavGraphDirections.globalActionAccountType())
                            } else {
                                navigate(HostNavGraphDirections.globalActionPreLogin())
                            }
                        }
                    } else clickCallback
                )
            }
            else -> hostViewModel.showDialog(requireContext(), dialogMessageType, clickCallback)
        }
    }*/

    private fun observeNavigationLiveData() {

        observeLiveData(getFragmentConfig().navigationLiveData) { navigator ->
            when (navigator) {
                is FragmentNavigator.Push -> navigate(navigator.navDirections)

                is FragmentNavigator.LaunchActivityResultContract<*> ->
                    launchActivityResultContract(navigator.launchResult)

                is FragmentNavigator.ViaIntent -> startActivity(navigator.intent)

                FragmentNavigator.Finish -> requireActivity().finish()

                FragmentNavigator.Pop -> navigateBack()

              /*  FragmentNavigator.SupportService -> hostViewModel.navigateToFreshDeskSupport()*/
                else -> {}
            }
        }
    }

    private fun postHeaderAndFooterConfig() {
        hostViewModel.postFragmentConfig(getFragmentConfig())
    }

    protected open fun getFragmentConfig(): FragmentConfig =
        FragmentConfig.DEFAULT.copy(
            navigationLiveData = viewModel.navigationLiveData,
            headerConfig = viewModel.getHeaderConfig(),
            showFooter = showFooter(),
            fragNavContainerTopMargin = getFragNavContainerTopMargin()
        )

   /* private fun observeLoadingAndUiMessageLiveData() {
        observeLiveData(viewModel.uiMessageLiveData, ::handleUiMessage)
        observeLiveData(viewModel.loadingLiveData) { canShow ->
            hostViewModel.setLoadingFromHost(canShow)
        }
    }*/

  /*  protected fun handleUiMessage(uiMessage: UIMessage) {
        when (uiMessage) {
            is UIMessage.ToastMessage -> showToast(uiMessage.message)
            is UIMessage.DialogMessage -> handleDialogMessageType(uiMessage.dialogMessageType, uiMessage.clickCallback)
        }
    }
*/
/*
    private fun handleDialogMessageType(dialogMessageType: DialogMessageType, clickCallback: ClickCallback<Any>) {
        when(dialogMessageType){
            is DialogMessageType.Error -> {
                if (dialogMessageType.errorCode == ApiErrors.DUAL_LOGIN || dialogMessageType.errorCode == ApiErrors.DEVICE_NOT_REGISTERED || dialogMessageType.errorCode == UN_AUTH_ERROR_CODE ){
                        val authOldFlowEnabled = hostViewModel.logout()
                        if (authOldFlowEnabled) {
                            navigate(HostNavGraphDirections.globalActionAccountType())
                        } else {
                            navigate(HostNavGraphDirections.globalActionPreLogin())
                        }
                    return
                    }
                hostViewModel.showDialog(
                    requireContext(),
                    dialogMessageType,
                    if (dialogMessageType.errorCode == ApiErrors.UN_AUTHORIZED_USER_CODE) object : ClickCallback<Any> {
                        override fun onClick(type: Any) {
                            val authOldFlowEnabled = hostViewModel.logout()
                            if (authOldFlowEnabled) {
                                navigate(HostNavGraphDirections.globalActionAccountType())
                            } else {
                                navigate(HostNavGraphDirections.globalActionPreLogin())
                            }
                        }
                    } else clickCallback
                )
            }
            else -> hostViewModel.showDialog(requireContext(), dialogMessageType, clickCallback)
        }
    }
*/


/*    private fun postHeaderAndFooterConfig() {
        hostViewModel.postFragmentConfig(getFragmentConfig())
    }

    protected open fun getFragmentConfig(): FragmentConfig =
        FragmentConfig.DEFAULT.copy(
            navigationLiveData = viewModel.navigationLiveData,
            headerConfig = viewModel.getHeaderConfig(),
            showFooter = showFooter(),
            fragNavContainerTopMargin = getFragNavContainerTopMargin()
        )

    private fun observeNavigationLiveData() {
        observeLiveData(viewModel.isChatButtonTap){
            when (hostViewModel.chatNavigation()) {
                ChatNavigation.FRESH_DESK -> {
                    hostViewModel.navigateToFreshDeskSupport()
                }

                ChatNavigation.WHATSAPP -> {
                    hostViewModel.openWhatsapp()
                }
            }
        }
        observeLiveData(getFragmentConfig().navigationLiveData) { navigator ->
            when (navigator) {
                is FragmentNavigator.Push -> navigate(navigator.navDirections)

                is FragmentNavigator.BottomNavItem ->
                    hostViewModel.postBottomNavMenuItemId(navigator.menuItemId)

                is FragmentNavigator.LaunchActivityResultContract<*> ->
                    launchActivityResultContract(navigator.launchResult)

                is FragmentNavigator.ViaIntent -> startActivity(navigator.intent)

                FragmentNavigator.Finish -> requireActivity().finish()

                FragmentNavigator.Pop -> navigateBack()

                FragmentNavigator.SupportService -> hostViewModel.navigateToFreshDeskSupport()
            }
        }
    }*/

    private fun <I> launchActivityResultContract(
        data: ActivityResultContractData.Launch<I>
    ) {
        data.activityResultLauncher.launch(data.input)
    }

    protected open fun showFooter(): Boolean = true

    protected open fun getFragNavContainerTopMargin(): Int = getAppHeaderHeight()
}
