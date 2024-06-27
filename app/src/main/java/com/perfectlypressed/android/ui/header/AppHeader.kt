package com.perfectlypressed.android.ui.header

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.perfectlypressed.R
import com.perfectlypressed.android.extentions.getBinding
import com.perfectlypressed.databinding.AppHeaderBinding


class AppHeader @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleRes: Int = 0) :
    ConstraintLayout(context, attributeSet, defStyleRes) {

    private val binding: AppHeaderBinding = getBinding(R.layout.app_header)

    fun setUiData(config: HeaderConfig) {
        binding.run {
            data = config
            executePendingBindings()
        }
    }
}
