package com.perfectlypressed.android.extentions

import android.animation.Animator
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.LayoutRes
import androidx.annotation.Px
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.forEach
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.perfectlypressed.R
import kotlinx.coroutines.delay
import timber.log.Timber



fun View.show() = run {
    visibility = View.VISIBLE
    this
}

fun View.invisible() = run {
    visibility = View.INVISIBLE
    this
}

fun View.hide() = run {
    visibility = View.GONE
    this
}

@BindingAdapter("set_bottom_menu")
fun BottomNavigationView.setMenu(currentMenu: Int) {
    menu.clear()
    inflateMenu(currentMenu)
}

@BindingAdapter("remove_bottom_menu_item")
fun BottomNavigationView.removeBottomMenuItem(item: Int) {
    menu.removeItem(item)
}

@BindingAdapter(value = ["view_visibility"])
fun View.viewVisibility(show: Boolean) = run {
    if (show) show() else hide()
}

@BindingAdapter("set_text_if_not_blank")
fun AppCompatEditText.setTextIfNotBlank(enteredText: String?) {
    isEnabled = true
    enteredText?.let {
        if (it.isNotEmpty()) {
            setText(it)
            isEnabled = false
        }
    }
}
@BindingAdapter("single_on_click")
fun View.singleOnClick(listener: View.OnClickListener) {
    clickToAction {
        listener.onClick(this)
    }
}

@BindingAdapter(value = ["inverse_view_visibility"])
fun View.inverseViewVisibility(hide: Boolean) = run {
    viewVisibility(hide.inverse)
}

@BindingAdapter(value = ["disableField"])
fun View.disableField(disable: Boolean) {
    if (disable) {
        isEnabled = false
        alpha = 0.5f
    } else {
        isEnabled = true
        alpha = 1f
    }
}



@BindingAdapter(value = ["invisible_view_visibility"])
fun View.invisibleViewVisibility(show: Boolean) = run {
    if (show) show() else invisible()
}


fun View.visibility(show: Boolean, invisible: Boolean = false) = run {
    if (show) show() else if (invisible) invisible() else hide()
}

inline fun <T : View> viewVisibility(
    view: T?,
    show: Boolean,
    invisible: Boolean = false,
    crossinline showedCallBack: T.() -> Unit = {},
    crossinline hideCallBack: T.() -> Unit = {}
) =
    view?.run {
        if (show) show() else if (invisible) invisible() else hide()
        if (show) showedCallBack(this) else hideCallBack(this)
    }

fun View.drawable(@DrawableRes drawableRes: Int): Drawable? =
    AppCompatResources.getDrawable(context, drawableRes)

fun View.dimension(@DimenRes dimenRes: Int) = resources.getDimension(dimenRes)

fun View.dimensionInteger(@DimenRes dimenRes: Int) = dimension(dimenRes).toInt()

fun View.string(@StringRes stringRes: Int) = resources.getString(stringRes)

fun View.colorStateList(@ColorRes color: Int): ColorStateList =
    AppCompatResources.getColorStateList(context, color)

fun AppCompatImageView.setTint(@ColorRes colorRes: Int) {
    ImageViewCompat.setImageTintList(
        this,
        ColorStateList.valueOf(ContextCompat.getColor(context, colorRes))
    )
}

fun View.colorStateList(hexColor: String?): ColorStateList {
    return try {
        ColorStateList.valueOf(Color.parseColor(hexColor.default))
    } catch (e: Exception) {
        colorStateList(R.color.app_blue)
    }
}

fun View.translate(
    value: Float = 800f,
    duration: Long = 2000,
    flipOnEnd: Boolean = false,
    onEnd: () -> Unit
) {
    animate().translationX(value).setListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator) {
            scaleX = if (flipOnEnd && scaleX == 1f) {
                -1f
            } else {
                1f
            }
            onEnd()
        }

        override fun onAnimationEnd(animation: Animator) {}
        override fun onAnimationCancel(animation: Animator) {}
        override fun onAnimationRepeat(animation: Animator) {}
    }).setDuration(duration).start()
}

fun View.color(@ColorRes color: Int) = resources.getColor(color)

fun View.font(@FontRes font: Int) = ResourcesCompat.getFont(context, font)

fun ViewGroup.inflate(@LayoutRes resourceId: Int): View = View.inflate(context, resourceId, this)

val View.inflater
    get() = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

fun <T : ViewDataBinding> ViewGroup.getBinding(@LayoutRes resourceId: Int): T =
    DataBindingUtil.inflate(inflater, resourceId, this, true)

fun View.getResourceValueId(a: TypedArray, styleableId: Int) = TypedValue().also {
    a.getValue(styleableId, it)
}.resourceId

fun View.getResourceValue(a: TypedArray, styleableId: Int) = TypedValue().apply {
    a.getValue(styleableId, this)
}

fun View.checkIfResourceIsColor(resourceType: Int) =
    resourceType.greaterThanEqualsTo(TypedValue.TYPE_FIRST_COLOR_INT) and resourceType.lessThanEqualsTo(
        TypedValue.TYPE_LAST_COLOR_INT
    )

fun View.isValidResourceId(id: Int) =
    isValidDrawableRes(id) or isValidColorRes(id) or
            isValidDimens(id) or isValidString(id) or
            id.greaterThan(0)

fun View.isValidFont(id: Int) = try {
    font(id)
    true
} catch (e: Exception) {
    false
}

fun View.isValidString(id: Int) = try {
    string(id)
    true
} catch (e: Exception) {
    false
}

fun View.isValidDimens(id: Int) = try {
    dimension(id)
    true
} catch (e: Exception) {
    false
}

fun View.isValidDrawableRes(id: Int) = try {
    drawable(id)
    true
} catch (e: Exception) {
    false
}

fun View.isValidColorRes(id: Int) = try {
    color(id)
    true
} catch (e: Exception) {
    false
}

val View.isVisible
    get() = visibility == View.VISIBLE

val View.isGone
    get() = visibility == View.GONE

val View.isInvisible
    get() = visibility == View.INVISIBLE

inline fun View.isVisible(crossinline showed: View.() -> Unit) = run {
    if (isVisible) showed(this)
}

inline fun View.isGone(crossinline showed: View.() -> Unit) = run {
    if (isGone) showed(this)
}

inline fun View.isInvisible(crossinline showed: View.() -> Unit) {
    if (isInvisible) showed(this)
}

private fun <T : AttributeSet?> View.getStyleAttributes(styleableId: IntArray, t: T): TypedArray =
    context.theme.obtainStyledAttributes(t, styleableId, 0, 0)

fun <T : AttributeSet?> View.getStyleAttributes(
    styleableId: IntArray,
    t: T,
    typedArray: TypedArray.() -> TypedArray
) =
    typedArray(context.theme.obtainStyledAttributes(t, styleableId, 0, 0)).apply {
        recycle()
    }

fun <T : AttributeSet?> ViewGroup.inflateAndGetStyleAttributes(
    @LayoutRes resourceId: Int,
    styleableId: IntArray,
    t: T,
    typedArray: TypedArray.() -> Unit
): View =
    inflate(resourceId).apply {
        typedArray(getStyleAttributes(styleableId, t))
    }

fun <T : AttributeSet?, VB : ViewDataBinding> VB.getStyleAttributesFromBinding(
    styleableId: IntArray,
    t: T,
    typedArray: TypedArray.() -> Unit
): View =
    root.apply {
        typedArray(getStyleAttributes(styleableId, t))
    }


fun View.hideKeyboard() {
    (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        windowToken,
        0
    )
}

fun View.showKeyboard() {
    (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
        this,
        InputMethodManager.SHOW_IMPLICIT
    )
}

fun SwipeRefreshLayout.setupLayout(
    progressColor: Int,
    listener: SwipeRefreshLayout.OnRefreshListener
) {
    setColorSchemeResources(progressColor)
    setOnRefreshListener(listener)
}

suspend fun SwipeRefreshLayout.dismissSpinner(afterInterval: Long) {
    delay(afterInterval)
    isRefreshing = false
}



fun <T : View> View.castTo() = this as T

fun ProgressBar.resetProgress() {
    secondaryProgress = 0
}

fun ProgressBar.setMainProgress(progress: Int) {
    secondaryProgress = progress
}

fun ViewGroup.getFirstChildIfExists(): View? {
    return if (childCount.greaterThan(0)) getChildAt(0) else null
}

fun View.updatePadding(@Px all: Int) {
    updatePadding(all, all, all, all)
}

fun View.disableByApplyingAlpha(enable: Boolean, alphaValue: Float = 0.5f) {
    isEnabled = enable
    if (enable.inverse) {
        alpha = alphaValue
    }
}

fun <V : View> BottomSheetBehavior<V>.toggle(open: Boolean = true) {
    if (open.inverse) collapse() else expand()
}

fun <V : View> BottomSheetBehavior<V>.toggle() {
    if (state == BottomSheetBehavior.STATE_EXPANDED) collapse() else expand()
}

fun <V : View> BottomSheetBehavior<V>.collapse() {
    state = BottomSheetBehavior.STATE_COLLAPSED
}

fun <V : View> BottomSheetBehavior<V>.expand() {
    state = BottomSheetBehavior.STATE_EXPANDED
}

fun <V : View> V.setupBottomSheetBehavior(callback: (bottomSheet: View, newState: Int) -> Unit = { _, _ -> }): BottomSheetBehavior<V> =
    BottomSheetBehavior.from(this).apply {
        addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                //
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                callback(bottomSheet, newState)
            }
        })
    }

fun View.applyMargin(@Px all: Int) {
    updateLayoutParams<ViewGroup.MarginLayoutParams> {
        leftMargin = all
        topMargin = all
        rightMargin = all
        bottomMargin = all
    }
}

fun View.applyMargin(@Px start: Int = 0, @Px top: Int = 0, @Px end: Int = 0, @Px bottom: Int = 0) {
    updateLayoutParams<ViewGroup.MarginLayoutParams> {
        marginStart = start
        topMargin = top
        marginEnd = end
        bottomMargin = bottom
    }
}

fun Animation.startAnimationOnViews(vararg views: View) {
    views.forEach { view ->
        view.startAnimation(this)
    }
}

fun Bitmap.compressAndGetUri(context: Context, compressedUri: (Uri) -> Unit) {
    context.contentResolver.insert(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        ContentValues().apply {
            put(MediaStore.Images.Media.TITLE, "title")
            put(MediaStore.Images.Media.MIME_TYPE, "image/*")
        }
    )?.let { uri ->
        try {
            context.contentResolver.openOutputStream(uri)?.let { stream ->
                compress(Bitmap.CompressFormat.JPEG, 70, stream)
                stream.close()
            }
        } catch (e: Exception) {
            Timber.e(e.localizedMessage)
        }
        compressedUri(uri)
    }
}

fun BottomNavigationView.enable(enable: Boolean = true) {
    isEnabled = enable
    menu.forEach { it.isEnabled = enable }
}

fun View.toggleAppearance(enabled: Boolean = true) {
    isEnabled = enabled
}

fun View.clickToAction(withDelay: Boolean = true, action: () -> Unit = {}) {
    if (withDelay) {
        setOnClickListener(
            SafeClickListener {
                hideKeyboard()
                action()
            }
        )
    } else {
        setOnClickListener {
            hideKeyboard()
            action()
        }
    }
}










