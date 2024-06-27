package com.perfectlypressed.android.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.perfectlypressed.BR
import com.perfectlypressed.R
import com.perfectlypressed.android.base.data.UIMessage
import com.perfectlypressed.android.base.domain.BaseViewModel
import com.perfectlypressed.android.base.domain.DialogDismissalHandler
import com.perfectlypressed.android.host.HostViewModel


abstract class BaseBottomSheetDialogFragment<VB : ViewDataBinding> : BottomSheetDialogFragment() {

    protected open val viewModel: BaseViewModel? = null
    abstract val dialogDismissHandler: DialogDismissalHandler

    val hostViewModel: HostViewModel by activityViewModels()

    protected abstract fun getBindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VB

    protected lateinit var binding: VB

    protected fun attachDismissListener() {
        binding.run {
           /* setVariable(
                BR.dismissListener,
                object : DismissListener {
                    override fun onDismiss(value: Any?) {
                        dialogDismissHandler.onDialogDismissed(value)
                        dismiss()
                    }
                }
            )*/
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getBindView(inflater, container).apply {
            binding = this
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
           // setVariable(BR.translationManager, hostViewModel.translationManager)
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.let { viewModel ->
            /*observeLiveData(viewModel.navigationLiveData) { navigator ->
                when (navigator) {
                    is FragmentNavigator.Push -> navigate(navigator.navDirections)
                    is FragmentNavigator.BottomNavItem -> hostViewModel.postBottomNavMenuItemId(navigator.menuItemId)
                    is FragmentNavigator.ViaIntent -> startActivity(navigator.intent)
                    FragmentNavigator.Finish -> requireActivity().finish()
                    FragmentNavigator.Pop -> navigateBack()
                    else -> {}
                }
            }
            observeLiveData(viewModel.uiMessageLiveData, ::handleUiMessage)
            observeLiveData(viewModel.loadingLiveData) { canShow ->
                hostViewModel.setLoadingFromHost(canShow)
            }*/
        }
    }

    private fun handleUiMessage(uiMessage: UIMessage) {
       /* when (uiMessage) {
            is UIMessage.ToastMessage -> showToast(uiMessage.message)
            is UIMessage.DialogMessage -> hostViewModel.showDialog(requireContext(), uiMessage.dialogMessageType, uiMessage.clickCallback)
        }*/
    }

    override fun getTheme(): Int {
        return R.style.BottomSheetDialog
    }
}
