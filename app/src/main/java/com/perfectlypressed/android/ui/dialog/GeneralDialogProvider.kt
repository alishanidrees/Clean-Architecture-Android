package com.perfectlypressed.android.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.DimenRes
import com.perfectlypressed.R
import com.perfectlypressed.android.extentions.EMPTY_STRING
import com.perfectlypressed.android.ui.click_callback.ClickCallback
import com.perfectlypressed.databinding.DialogInfoBinding
import com.tawkeel.base.data.DismissListener
import java.io.Serializable
import javax.inject.Inject

data class GeneralDialogUiData(
    val title: String = EMPTY_STRING,
    val description: String,
    val primaryButtonText: String = EMPTY_STRING,
    val secondaryButtonText: String = EMPTY_STRING,
    @Transient val primaryButtonListener: ClickCallback<Any>? = null,
    @Transient val secondaryButtonListener: ClickCallback<Any>? = null,
    val isCancelable: Boolean = false,
    val iconRes: Int = 0,
    @DimenRes val iconWidth: Int? = null,
    @DimenRes val iconHeight: Int? = null,
    val isAnimatedIcon: Boolean = false
) : Serializable

interface GeneralDialogProvider {
    fun show(context: Context, generalDialogUiData: GeneralDialogUiData)
}

class DefaultGeneralDialogProvider @Inject constructor() : GeneralDialogProvider {
    override fun show(context: Context, generalDialogUiData: GeneralDialogUiData) {
        val binding = DialogInfoBinding.inflate(LayoutInflater.from(context))
        val alertDialog = AlertDialog.Builder(context)
            .setView(binding.root)
            .setCancelable(generalDialogUiData.isCancelable)
            .create()
        alertDialog.window?.setBackgroundDrawableResource(R.drawable.dialog_background)
        binding.dismissListener = object : DismissListener {
            override fun onDismiss(value: Any?) {
                when (value) {
                    true -> generalDialogUiData.primaryButtonListener?.onClick(value)
                    false -> generalDialogUiData.secondaryButtonListener?.onClick(Unit)
                }
                alertDialog.dismiss()
            }
        }
        //binding.uiData = generalDialogUiData
        alertDialog.show()
    }
}
