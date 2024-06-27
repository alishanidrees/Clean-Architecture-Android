@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.perfectlypressed.android.extentions

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavDestination
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.perfectlypressed.R
import com.perfectlypressed.android.ui.header.AppHeader
import com.perfectlypressed.android.ui.header.HeaderConfig
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

@BindingAdapter("set_ui_data")
fun AppHeader.setUiDataFromBinding(config: HeaderConfig?) {
    config?.let { data ->
        setUiData(data)
    }
}

@BindingAdapter("top_margin")
fun FragmentContainerView.setTopMargin(value: Int?) {
    applyMargin(top = value.default)
}
@BindingAdapter("bottom_margin")
fun ConstraintLayout.setBottomMargin(value: Int?) {
    applyMargin(bottom = value.default)
}

@BindingAdapter("top_margin")
fun ConstraintLayout.setTopMargin(value: Int?) {
    applyMargin(top = value.default)
}


@BindingAdapter("drawable")
fun ViewGroup.setDrawableFromBinding(drawable: Drawable?) {
    background = drawable ?: drawable(android.R.color.transparent)
}

@BindingAdapter("set_button_drawable")
fun AppCompatCheckBox.setDrawableFromBinding(drawable: Drawable?) {
    buttonDrawable = drawable
}



@BindingAdapter("apply_horizontal_margin")
fun View.horizontalMargin(dimen: Float?) {
    updateLayoutParams<ViewGroup.MarginLayoutParams> {
        marginStart = dimen?.toInt().default
        marginEnd = dimen?.toInt().default
    }
}


@BindingAdapter("apply_horizontal_margin")
fun ViewGroup.horizontalMargin(dimen: Float?) {
    updateLayoutParams<ViewGroup.MarginLayoutParams> {
        marginStart = dimen?.toInt().default
        marginEnd = dimen?.toInt().default
    }
}

@BindingAdapter("apply_padding")
fun View.applyPadding(dimen: Float?) {
    updatePadding(all = dimen?.toInt().default)
}

@BindingAdapter("apply_horizontal_padding")
fun View.horizontalPadding(dimen: Float?) {
    updatePadding(left = dimen?.toInt().default, right = dimen?.toInt().default)
}

@BindingAdapter("apply_horizontal_padding")
fun ViewGroup.horizontalPadding(dimen: Float?) {
    updatePadding(left = dimen?.toInt().default, right = dimen?.toInt().default)
}

fun <T> convertObjectToJsonString(model: T): String =
    GsonBuilder().create().toJson(model)

fun JSONObject.toMap(): Map<String, *> = keys().asSequence().associateWith {
    when (val value = this[it]) {
        is JSONArray -> {
            val map = (0 until value.length()).associate { it -> Pair(it.toString(), value[it]) }
            JSONObject(map).toMap().values.toList()
        }

        is JSONObject -> value.toMap()
        JSONObject.NULL -> null
        else -> value
    }
}

fun getJson(bundle: Bundle?): String? {
    if (bundle == null) return null
    val jsonObject = JSONObject()
    for (key in bundle.keySet()) {
        try {
            jsonObject.put(key, JSONObject.wrap(bundle.get(key)))
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
    return jsonObject.toString()
}


fun <T> convertObjectToJsonStringForOneSignal(model: T): String =
    removeJsonElements(JSONObject(Gson().toJson(model)).toString())

fun <T> convertModelToJsonString(model: T): String =
    JSONObject(Gson().toJson(model)).toString()

private fun removeJsonElements(jsonObject: String): String {
    val testObject = JSONObject(jsonObject)
    if (testObject.get("role_id") == 1)
        testObject.remove("performer_status")
    testObject.remove("image_id_url")
    testObject.remove("account_title")
    testObject.remove("iban_image_url")
    testObject.remove("profile_image_url")
    testObject.remove("iban_number")
    testObject.remove("beneficiary_address")
    testObject.remove("bank")
    testObject.remove("id_number")
    testObject.remove("bank_name")
    testObject.remove("swift_code")
    testObject.remove("performer_avg_rating")
    return testObject.toString()
}

inline fun <reified T> convertJsonToModel(string: String): T? =
    GsonBuilder()
        .enableComplexMapKeySerialization()
        .setPrettyPrinting()
        .create()
        .fromJson(
            string.replace("^\"|\"$".toRegex(), "").replace("\\n", "")
                .replace("\\", "").trim(),
            T::class.java
        )

inline fun <reified T> convertJsonToRegexModel(string: String): T? =
    GsonBuilder()
        .enableComplexMapKeySerialization()
        .setPrettyPrinting()
        .create()
        .fromJson(
            string.replace("^\"|\"$".toRegex(), "").replace("\\n", "").trim(),
            T::class.java
        )

fun MutableMap<String, Any>?.getPayFortResponseMessage(): String =
    this?.get("response_message").toString()

@BindingAdapter(value = ["on_refresh_call_back"])
fun SwipeRefreshLayout.onRefreshCallback(onRefresh: () -> Unit) {
    setupLayout(R.color.app_brown_green) {
        onRefresh()
        isRefreshing = false
    }
}

/*@BindingAdapter("on_delete_user_reason_change")
fun CustomEditTextField.onnDeleteUserReasonEntered(button: Button?) {
    result = { _, _ ->
        if (getFieldText().trim().count() >= 10) {
            button?.isEnabled = true
            button?.alpha = 1f
        } else {
            this.setError("Please enter at least 10 characters", this)
            button?.isEnabled = false
            button?.alpha = 0.5f
        }
    }
}*/

//@BindingAdapter("gender", "url_image")
//fun ImageView.setDrawableForFemale(gender: String?, url: String?) {
//    if (url.isNullOrEmpty() || url == badImageUrl) {
//        if (gender == Gender.FEMALE.slug)
//            setImageResource(R.drawable.female_placeholder)
//           /* this.background =
//                (ContextCompat.getDrawable(context, R.drawable.female_placeholder))*/
//        else
//            setImageResource(R.drawable.general_male_placeholder)
//           /* this.background =
//                (ContextCompat.getDrawable(context, R.drawable.ihram_mens_placeholder))*/
//    } else {
//        loadImage(url, gender)
//        show()
//    }
//}

/*@BindingAdapter("gender", "url_image_profile")
fun ImageView.setDrawableForFemaleProfile(gender: String?, url: String?) {
    if (url.isNullOrEmpty() || url == badImageUrl) {
        if (gender == Gender.FEMALE.slug)
            setImageResource(R.drawable.female_placeholder)
        else
            setImageResource(R.drawable.general_male_placeholder)
    } else {
        loadImage(url, gender)
        show()
    }
}*/

const val badImageUrl = "https://tawkeelnonprod.blob.core.windows.net/upload-image"

/*@BindingAdapter("on_delete_user_reason_change", "input_edit_text")
fun AppCompatEditText.onnDeleteAccountReasonEntered(
    button: AppCompatButton?,
    editText: AppCompatEditText
) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(str: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(str: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(str: Editable) {
            if (str.toString().trim().count() >= 10) {
                button?.isEnabled = true
                button?.background = ContextCompat.getDrawable(context,R.drawable.common_button_background)
                button?.setTextColor(Color.parseColor("#ffffff"))

            } else {
                button?.background = ContextCompat.getDrawable(context,R.drawable.round_button_disabled)
                button?.isEnabled = false
                button?.setTextColor(Color.parseColor("#6D6D6D"))
            }
        }
    })

}*/

@FunctionalInterface
interface SearchQueryListenerCallback {
    fun onQueryTextChanged(query: String)
}

@BindingAdapter(value = ["on_search_query_call_back"])
fun SearchView.searchCallback(searchQueryListenerCallback: SearchQueryListenerCallback?) {
    setOnQueryTextListener(object :
        android.widget.SearchView.OnQueryTextListener,
        SearchView.OnQueryTextListener {
        override fun onQueryTextChange(newText: String): Boolean {
            searchQueryListenerCallback?.onQueryTextChanged(newText)
            return false
        }

        override fun onQueryTextSubmit(query: String): Boolean {
            return false
        }
    })
}

fun NavDestination?.getClassName(): String {
    return try {
        when {
            this == null -> EMPTY_STRING
            this is FragmentNavigator.Destination -> className
            this is DialogFragmentNavigator.Destination -> className
            else -> EMPTY_STRING
        }
    } catch (e: Exception) {
        EMPTY_STRING
    }
}

@BindingAdapter("apply_start_margin")
fun View.applyStartMargin(dimen: Float?) {
    updateLayoutParams<ViewGroup.MarginLayoutParams> {
        marginStart = dimen?.toInt().default
    }
}

/*
@BindingAdapter("update_drag_mode")
fun SwipeRevealLayout.updateDragMode(isLeftDrag: Boolean) {
    dragEdge =
        if (isLeftDrag) SwipeRevealLayout.DRAG_EDGE_RIGHT else SwipeRevealLayout.DRAG_EDGE_LEFT
}

@BindingAdapter("update_layout_mode")
fun SwipeRevealLayout.updateLayoutMode(layoutMode: RevealLayoutMode) {
    if (layoutMode == RevealLayoutMode.Close) close(true) else if (layoutMode == RevealLayoutMode.Open) open(
        true
    )
}*/
