package com.tawkeel.base.data

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import com.perfectlypressed.android.base.domain.RuntimePermissionListener

data class RuntimePermissionData(
    val permissions: List<String>,
    val runtimePermissionHandler: RuntimePermissionListener
)

val cameraPermission: List<String> = listOf(
    Manifest.permission.CAMERA
)
val storageAndCameraPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    listOf(
        Manifest.permission.READ_MEDIA_IMAGES,
        Manifest.permission.READ_MEDIA_VIDEO,
        Manifest.permission.READ_MEDIA_AUDIO,
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO
    )
} else {
    listOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO
    )
}
val storagePermission: List<String> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    listOf(
        Manifest.permission.READ_MEDIA_IMAGES,
        Manifest.permission.READ_MEDIA_VIDEO,
    )
} else {
    listOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
}

val locationPermission: List<String> =
    listOf(
        Manifest.permission.ACCESS_FINE_LOCATION
    )

@RequiresApi(Build.VERSION_CODES.Q)
var backgroundLocationPermission: List<String> =
    listOf(
        Manifest.permission.ACCESS_BACKGROUND_LOCATION
    )

val recordingPermission: List<String> =
    listOf(Manifest.permission.RECORD_AUDIO)

@RequiresApi(Build.VERSION_CODES.Q)
val activityRecognition: List<String> =
    listOf(Manifest.permission.ACTIVITY_RECOGNITION)
