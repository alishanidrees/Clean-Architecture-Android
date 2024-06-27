package com.perfectlypressed.android.extentions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.findNavController
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.perfectlypressed.R
import com.perfectlypressed.android.base.domain.RuntimePermissionListener
import com.perfectlypressed.android.host.HostActivity


fun AppCompatActivity.showToast(message: String, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, length).show()

fun AppCompatActivity.hideKeyboard() =
    (getSystemService(android.app.Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
        hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

fun AppCompatActivity.openViewer(filePath: String) {
    startActivity(
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(filePath)
        }
    )
}

fun AppCompatActivity.connectivityManager() =
    getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

fun Context.getTelephonyManager(): TelephonyManager =
    getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager


fun Activity.keepScreenAlive() {
    this.apply {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}

fun HostActivity.requestForPermission(
    permissions: List<String>,
    listener: RuntimePermissionListener
) {
    Dexter.withActivity(this)
        .withPermissions(permissions)
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                report?.apply {
                    when {
                        isAnyPermissionPermanentlyDenied -> {
                            showToast(getString(R.string.permission_denied_permanently))
                            listener.onLocationPermissionDenied(emptyList())
                        }

                        areAllPermissionsGranted() -> listener.onPermissionGranted(
                            grantedPermissionResponses?.map { it.permissionName }
                                .orEmptyArrayList()
                        )


                        else -> listener.onPermissionGranted(emptyList())
                    }
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: MutableList<PermissionRequest>?,
                token: PermissionToken?
            ) {
                token?.continuePermissionRequest()
            }
        })
        .withErrorListener { _ ->
            showToast(getString(R.string.no_permission_message))
        }
        .onSameThread()
        .check()
}

fun AppCompatActivity.navigate(navDirections: NavDirections, navOptions: NavOptions? = null) {
    findNavController(R.id.nav_host_fragment).navigate(navDirections, navOptions)
}

fun AppCompatActivity.navigate(
    resId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navExtras: Navigator.Extras? = null
) {
    findNavController(R.id.nav_host_fragment).navigate(resId, args, navOptions, navExtras)
}

fun AppCompatActivity.navigateBack() {
    findNavController(R.id.nav_host_fragment).navigateUp()
}

fun AppCompatActivity.popBackStack(@IdRes menuId: Int, inclusive: Boolean) {
    findNavController(R.id.nav_host_fragment).popBackStack(menuId, inclusive)
}

fun AppCompatActivity.navController() =
    findNavController(R.id.nav_host_fragment)

fun AppCompatActivity.restartHost() {
    startActivity(
        Intent(this, HostActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    )
}
