package com.perfectlypressed.android.utils

import android.graphics.Color
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.ClickableSpan


abstract class TouchableSpan(
    private val textColor: Int,
    private val pressedTextColor: Int,
    private val isUnderline: Boolean = false,
    private val typeFace:Typeface
) : ClickableSpan() {

    private var isPressed: Boolean = false
   // private val typeFace = Typeface.create(ResourcesCompat.getFont(context, R.font.ibm_plex_sans_medium), Typeface.NORMAL)


    override fun updateDrawState(paint: TextPaint) {
        super.updateDrawState(paint)
        paint.run {
            color = if (isPressed) pressedTextColor else textColor
            isUnderlineText = isUnderline
            setTypeface(typeFace)
            bgColor = Color.TRANSPARENT
        }
    }

    fun setPressed(pressed: Boolean) {
        isPressed = pressed
    }
}
