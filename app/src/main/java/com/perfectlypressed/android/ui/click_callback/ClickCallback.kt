package com.perfectlypressed.android.ui.click_callback

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout

@FunctionalInterface
interface ClickCallback<in T> where T : Any {
    fun onClick(type: T)
}

@FunctionalInterface
interface NoInternetClickHandler {
    fun canDismiss(): Boolean
    fun onClick()
}

internal val EMPTY_CLICK_CALL_BACK = object : ClickCallback<Any> {
    override fun onClick(type: Any) {
        // no-op
    }
}
@FunctionalInterface
interface LoginButtonCallback {
    fun onClick(
            email: String,
            password: String?
    )
}





@FunctionalInterface
interface GiftFilterClickEvent {
    fun onClick(
        asc: Boolean?,
        from: String?,
    )
}


@FunctionalInterface
interface PasswordValidation {
    fun onError(context:Context,error:Int)
    fun onClick(
        password1: String?
    )
}

@FunctionalInterface
interface DiscountValidation {
    fun onError(context:Context,error:Int)

    fun onClick(
        codeField: String?
    )
}

@FunctionalInterface
interface AccountSetupButtonCallback {

    fun onClick(
        ibanNumber: String,
        idNumber: String,
        beneficiaryAddress: String,
        accountTitle: String,
    )
}

@FunctionalInterface
interface UpdatePerformerProfileButtonCallback {

    fun onClick(
        fullName: String,
        email: String,
        city: String,
        gender: String,
        idNumber: String,
        beneficiaryAddress: String,
        bankNumber: String,
        accountTitle: String,
        context: Context
    )
}


@FunctionalInterface
interface UpdateSeekerProfileButtonCallback {

    fun onClick(
        fullName: String,
        email: String,
        country: String,
        city: String,
        gender: String,
        context:Context
    )
}

@FunctionalInterface
interface PageSelectionCallback {

    fun onPageSelected(pageNumber: Int)
}
