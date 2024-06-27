package com.perfectlypressed.android.base.data

import com.perfectlypressed.android.ui.click_callback.ClickCallback
import com.perfectlypressed.android.ui.click_callback.EMPTY_CLICK_CALL_BACK
import com.perfectlypressed.android.ui.dialog.GeneralDialogUiData
import com.perfectlypressed.android.ui.dialog.NoInternetDialogUiData
import com.perfectlypressed.android.ui.dialog.SuccessDialogUiData
import com.perfectlypressed.network.ApiErrors
import com.perfectlypressed.network.ErrorWithCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

sealed class ViewState<out T> {
    data class Success<T>(val data: T) : ViewState<T>()
    object Empty : ViewState<Nothing>()
    data class Loading(val loading: Boolean) : ViewState<Nothing>()
    data class Error(val uiMessage: UIMessage) : ViewState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$uiMessage]"
            is Loading -> "Loading[loading=$loading]"
            Empty -> "Empty"
            else -> {"Empty"}
        }
    }
}

sealed interface UIMessage {
    data class DialogMessage(
        val dialogMessageType: DialogMessageType,
        val clickCallback: ClickCallback<Any> = EMPTY_CLICK_CALL_BACK
    ) : UIMessage

    data class ToastMessage(val message: String) : UIMessage
}

sealed interface DialogMessageType {
    data class Success(val successDialogUiData: SuccessDialogUiData) : DialogMessageType
    data class Error(val message: String?= null, val description: String? = null, var isCancelable: Boolean = true, val errorCode:Int? = null) :
        DialogMessageType
    data class GeneralDialog(val generalDialogUiData: GeneralDialogUiData) : DialogMessageType

    data class NoInternet(val noInternetDialogUiData: NoInternetDialogUiData) : DialogMessageType
}

fun getDefaultViewStateForError(error: ErrorWithCode, isCancelable: Boolean = true): ViewState.Error {
    val dialogMessageType = when (error.code) {
        ApiErrors.NO_NETWORK_CODE -> DialogMessageType.NoInternet(NoInternetDialogUiData())
        else -> DialogMessageType.Error(
            description = error.message,
            isCancelable = isCancelable,
            errorCode = error.code
        )
    }
    return ViewState.Error(UIMessage.DialogMessage(dialogMessageType, EMPTY_CLICK_CALL_BACK))
}

suspend fun <T> Flow<ViewState<T>>.unZipViewState(
    loading: Boolean.() -> Unit,
    success: T.() -> Unit,
    error: UIMessage.() -> Unit,
    empty: () -> Unit = {}
) {
    collectLatest { value: ViewState<T> ->
        when (value) {
            is ViewState.Success -> success(value.data)
            is ViewState.Error -> error(value.uiMessage)
            is ViewState.Loading -> loading(value.loading)
            ViewState.Empty -> empty()
            else -> {

            }
        }
    }
}
