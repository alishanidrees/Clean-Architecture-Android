package com.perfectlypressed.android.ui.header

import android.graphics.drawable.Drawable
import com.perfectlypressed.R
import com.perfectlypressed.android.ui.click_callback.ClickCallback
import com.perfectlypressed.android.ui.click_callback.EMPTY_CLICK_CALL_BACK
import com.perfectlypressed.network.EMPTY_STRING

data class HeaderConfig(
    val itemBackground: Drawable? = null,
    val titleConfig: TitleConfig,
    val chatTitleConfig: ChatImageTitleConfig,
    val headerRightButtonConfig: HeaderRightButtonConfig,
    val backButtonConfig: BackButtonConfig,

    ) {

    companion object {
        internal val DEFAULT = HeaderConfig(
                titleConfig = TitleConfig.DEFAULT,
                headerRightButtonConfig = HeaderRightButtonConfig.DEFAULT,
                backButtonConfig = BackButtonConfig.DEFAULT,
                chatTitleConfig = ChatImageTitleConfig.DEFAULT
        )
    }
}

data class TitleConfig(
        val title: String,
        val textColor: Int,
        val textSize: Int
) {
    companion object {
        internal val DEFAULT = TitleConfig(
                title = EMPTY_STRING,
                textColor = R.color.darkest_blue_color,
                textSize = com.intuit.sdp.R.dimen._12sdp
        )
    }
}

data class ChatImageTitleConfig(
        val titleName: String,
        val imageUrl: String,
        val textColor: Int,
        val textSize: Int
) {
    companion object {
        internal val DEFAULT = ChatImageTitleConfig(
                titleName = EMPTY_STRING,
                imageUrl = EMPTY_STRING,
                textColor = R.color.darkest_blue_color,
                textSize = com.intuit.sdp.R.dimen._12sdp
        )
    }
}

data class BackButtonConfig(
        val canShowBackButton: Boolean,
        val backButtonIconRes: Int = R.drawable.ic_launcher_background,
        val clickCallback: ClickCallback<Unit> = EMPTY_CLICK_CALL_BACK
) {
    companion object {
        internal val DEFAULT = BackButtonConfig(canShowBackButton = false)
    }
}

data class HomeButtonConfig(
    val canShowHomeButton: Boolean,
    val homeButtonIconRes: Int = R.drawable.ic_launcher_background,
    val clickCallback: ClickCallback<Unit> = EMPTY_CLICK_CALL_BACK
) {

    companion object {
        internal val DEFAULT = HomeButtonConfig(canShowHomeButton = false)
    }
}

sealed interface HeaderRightButtonType {
    data class Notification(val notificationIconRes: Int = R.drawable.ic_launcher_background) :
        HeaderRightButtonType
    object Home : HeaderRightButtonType
    object None : HeaderRightButtonType
}

data class HeaderRightButtonConfig(
    val headerRightButtonType: HeaderRightButtonType,
    val clickCallback: ClickCallback<HeaderRightButtonType> = EMPTY_CLICK_CALL_BACK
) {

    companion object {
        internal val DEFAULT =
            HeaderRightButtonConfig(
                headerRightButtonType = HeaderRightButtonType.Notification(R.drawable.ic_launcher_background)
            )
    }
}



