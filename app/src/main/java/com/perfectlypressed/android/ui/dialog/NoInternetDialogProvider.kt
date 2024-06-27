package com.perfectlypressed.android.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import com.perfectlypressed.R
import com.perfectlypressed.android.extentions.default
import com.perfectlypressed.android.ui.click_callback.NoInternetClickHandler
import com.perfectlypressed.databinding.DialogNoInternetBinding
import com.tawkeel.base.data.DismissListener
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.inject.Inject
import javax.inject.Singleton

@Parcelize
data class NoInternetDialogUiData(
    val buttonText: String? = null,
    val callback: @RawValue NoInternetClickHandler = object : NoInternetClickHandler {
        override fun canDismiss(): Boolean = true

        override fun onClick() {
            // no-op
        }
    }
) : Parcelable
@Singleton
interface NoInternetDialogProvider {
    fun show(context: Context, noInternetDialogUiData: NoInternetDialogUiData)
}

class DefaultNoInternetDialogProvider @Inject constructor(

) : NoInternetDialogProvider {
    override fun show(context: Context, noInternetDialogUiData: NoInternetDialogUiData) {
        val binding = DialogNoInternetBinding.inflate(LayoutInflater.from(context))
        val alertDialog = AlertDialog.Builder(context)
            .setView(binding.root)
            .setCancelable(false)
            .create()
        alertDialog.window?.setBackgroundDrawableResource(R.drawable.dialog_background)
      // binding.translationManager = translationManager
      /*  binding.doneButton.text = noInternetDialogUiData.buttonText.default(
           *//* translationManager.getGlobalContent().okay.default(
                stringsResourceManager.getString(R.string.okay)
            )*//*
        )*/
        binding.dismissListener = object : DismissListener {
            override fun onDismiss(value: Any?) {
                value?.let {
                    noInternetDialogUiData.callback.onClick()
                    if (noInternetDialogUiData.callback.canDismiss().default) {
                        alertDialog.dismiss()
                    }
                }
            }
        }
        alertDialog.show()
    }
}
