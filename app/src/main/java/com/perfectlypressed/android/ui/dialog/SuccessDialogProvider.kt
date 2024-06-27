package com.perfectlypressed.android.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.perfectlypressed.R
import com.perfectlypressed.android.extentions.hide
import com.perfectlypressed.android.extentions.setTextOrHideIfEmpty
import com.perfectlypressed.android.ui.click_callback.ClickCallback
import com.perfectlypressed.databinding.DialogSuccessBinding
import com.tawkeel.base.data.DismissListener
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.inject.Inject


@Parcelize
data class SuccessDialogUiData(
    val title: String,
    val description: String,
    val buttonText: String,
    val callback: @RawValue ClickCallback<Any>? = null
) : Parcelable

interface SuccessDialogProvider {
    fun show(
        context: Context,
        data: SuccessDialogUiData
    )
}

class DefaultSuccessDialogProvider @Inject constructor(

) : SuccessDialogProvider {

    override fun show(context: Context, data: SuccessDialogUiData) {
        val binding = DialogSuccessBinding.inflate(LayoutInflater.from(context))
        val alertDialog = AlertDialog.Builder(context)
            .setView(binding.root)
            .setCancelable(false)
            .create()

//        binding.errorImage.setImageResource(R.drawable.ic_success_img)
      //  if (isFemale() == Gender.FEMALE.slug) binding.errorImage.setImageResource(R.drawable.female_success_character) else binding.errorImage.setImageResource(R.drawable.success_character)
        alertDialog.window?.setBackgroundDrawableResource(R.drawable.dialog_background)
        //val width = (context.resources.displayMetrics.widthPixels * 0.70)
        alertDialog?.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        binding.dismissListener = object : DismissListener {
            override fun onDismiss(value: Any?) {
                value?.let {
                    data.callback?.onClick(value)
                    alertDialog.dismiss()
                }
            }
        }
        binding.apply {
            if(data.title.isNullOrEmpty()){
                titleText.hide()
            }
            titleText.text = data.title
            descriptionText.setTextOrHideIfEmpty(data.description)
            doneButton.text = data.buttonText
        }
        alertDialog.show()
    }
}
