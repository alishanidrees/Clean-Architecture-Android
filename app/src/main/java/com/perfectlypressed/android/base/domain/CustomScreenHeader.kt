package com.perfectlypressed.android.base.domain

import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageView
import com.tawkeel.base.data.ActivityResultContractData
import com.perfectlypressed.android.ui.header.HeaderConfig
import com.perfectlypressed.android.ui.header.HeaderRightButtonType
import com.perfectlypressed.network.EMPTY_STRING

interface ScreenAnalytics {
    fun trackScreenAnalyticsData()
}

interface CustomScreenHeader {

    fun getHeaderConfig(
        background: Drawable? = null,
        title: String = EMPTY_STRING,
        rightButtonType: HeaderRightButtonType = HeaderRightButtonType.None,
        showBackButton: Boolean = true,
        showFreshDeskIcon: Boolean = true,
        chatUserTitle: String = EMPTY_STRING,
        chatUserUrl: String = EMPTY_STRING
    ): HeaderConfig
}




interface DialogDismissalHandler {
    fun onDialogDismissed(value: Any? = null)
}

interface RuntimePermissionListener {
    fun onPermissionGranted(permissions: List<String>)
    fun onLocationPermissionDenied(permissions: List<String>)
}




interface GetImageFromCameraContractProvider {
    val getTakeImageRegistrationContract: ActivityResultContractData.Register<Uri, Boolean>
}

interface GetImageFromGalleryContractProvider {
    val getGalleryImageRegistrationContract: ActivityResultContractData.Register<String, Uri?>
}

interface CropImageContractProvider {
    val getCropImageContract: ActivityResultContractData.Register<CropImageContractOptions, CropImageView.CropResult>
}

interface GetImageFromCameraLauncherProvider {
    val takeImageContractLauncher: ActivityResultLauncher<Uri>
}

interface GetImageFromGalleryLauncherProvider {
    val galleryImageContractLauncher: ActivityResultLauncher<String>
}

interface CropImageLauncherProvider {
    val cropImageContractLauncher: ActivityResultLauncher<CropImageContractOptions>
}

