package com.perfectlypressed.android.extentions

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.CountDownTimer
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.method.MovementMethod
import android.text.method.ScrollingMovementMethod
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.FontRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.perfectlypressed.R

import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit


@BindingAdapter("set_text_or_hide_if_empty", "make_invisible_if_empty", requireAll = false)
fun TextView?.setTextOrHideIfEmpty(text: String? = "", invisible: Boolean = false) {
    this?.text = text
    viewVisibility(this, invisible = invisible, show = text?.isNotEmpty().default)
}

fun TextView.applyColorSizeAndFont(
    @FontRes fontPath: Int,
    @ColorRes color: Int,
    @DimenRes size: Int
) {
    applyFont(fontPath)
    applyTextColor(color)
    setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(size))
}

@BindingAdapter(value = ["apply_text_size"])
fun TextView.applyTextSize(@DimenRes dimenRes: Int) {
    setTextSize(TypedValue.COMPLEX_UNIT_PX, dimension(dimenRes))
}

@BindingAdapter(value = ["apply_text_color"])
fun TextView.applyTextColor(@ColorRes colorRes: Int) {
    setTextColor(ContextCompat.getColor(context, colorRes))
}

fun TextView.applyFont(@FontRes fontPath: Int) {
    typeface = ResourcesCompat.getFont(context, fontPath)
}

var TextView.drawableStart: Drawable?
    get() = compoundDrawablesRelative[0]
    set(value) = setDrawables(leftDrawable = value)

var TextView.drawableEnd: Drawable?
    get() = compoundDrawablesRelative[2]
    set(value) = setDrawables(rightDrawable = value)

fun TextView.setDrawables(
    leftDrawable: Drawable? = null,
    topDrawable: Drawable? = null,
    rightDrawable: Drawable? = null,
    bottomDrawable: Drawable? = null,
) =
    setCompoundDrawablesRelativeWithIntrinsicBounds(
        leftDrawable,
        topDrawable,
        rightDrawable,
        bottomDrawable
    )

@BindingAdapter("add_underline")
fun TextView.addUnderline(text:String) {
    this.paintFlags = this.paintFlags or Paint.UNDERLINE_TEXT_FLAG
   // paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

fun TextView.addStrikeThrough() {
    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}

fun TextView.transformToHtml(text: String?) {
    setText(HtmlCompat.fromHtml(text.toString(), HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_DIV))
}

fun TextView.makeClickableHtmlText(
    spannableString: SpannableString,
) {
    text = spannableString
    movementMethod = LinkMovementMethod.getInstance()
}

@BindingAdapter(value = ["enable_scrolling"])
fun AppCompatTextView.enableScrolling(enableScrolling: Boolean = true) {
    movementMethod = if (enableScrolling) ScrollingMovementMethod() else null
}

@BindingAdapter("set_movement_method")
fun TextView.attachMovementMethod(
    method: MovementMethod
) {
    movementMethod = method
}



@FunctionalInterface
interface TimePickerSelection {
    fun onPickTime(hour: Int, minute: Int)
}

@BindingAdapter("set_translation_text", "fallback_text", requireAll = true)
fun TextView.setTranslationText(
    translationText: String?,
    fallbackText: String?
) {
    text = translationText.ifEmpty(fallbackText.default)
}

