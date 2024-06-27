package com.perfectlypressed.android.ui.dialog

import android.content.Context
import com.perfectlypressed.android.ui.click_callback.ClickCallback
import javax.inject.Inject

interface DialogProvider {
    fun showGeneralDialog(context: Context, generalDialogUiData: GeneralDialogUiData)
    fun showErrorDialog(
        context: Context,
        message: String,
        description: String? = null,
        clickCallback: ClickCallback<Any>? = null,
        cancelable: Boolean
    )
    fun showSuccessDialog(context: Context, successDialogUiData: SuccessDialogUiData)
    fun showNoInternetDialog(context: Context, noInternetDialogUiData: NoInternetDialogUiData)
}

class DefaultDialogProvider @Inject constructor(
    private val generalDialogProvider: GeneralDialogProvider,
    private val errorDialogProvider: ErrorDialogProvider,
    private val successDialogProvider: SuccessDialogProvider,
    private val noInternetDialogProvider: NoInternetDialogProvider,
) : DialogProvider {

    override fun showGeneralDialog(context: Context, generalDialogUiData: GeneralDialogUiData) {
        generalDialogProvider.show(context, generalDialogUiData)
    }

    override fun showErrorDialog(context: Context, message: String, description: String?, clickCallback: ClickCallback<Any>?, cancelable: Boolean) {
        errorDialogProvider.show(context, message, description, clickCallback = clickCallback, isCancelable = cancelable)
    }

    override fun showSuccessDialog(context: Context, successDialogUiData: SuccessDialogUiData) {
        successDialogProvider.show(context, successDialogUiData)
    }

    override fun showNoInternetDialog(context: Context, noInternetDialogUiData: NoInternetDialogUiData) {
        noInternetDialogProvider.show(context, noInternetDialogUiData)
    }
}
