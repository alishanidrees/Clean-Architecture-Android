package com.perfectlypressed.android.cache

import android.content.Context
import androidx.core.content.edit
import com.bugsee.library.Bugsee
import com.perfectlypressed.R
import com.perfectlypressed.android.extentions.EMPTY_STRING
import com.perfectlypressed.android.extentions.convertJsonToModel
import com.perfectlypressed.android.extentions.convertObjectToJsonString
import com.perfectlypressed.android.extentions.default
import com.perfectlypressed.android.language.data.AppLanguageData
import com.perfectlypressed.android.utils.Constants
import com.perfectlypressed.network.NetworkPreferencesManager
import com.tawkeel.resources.StringsResourceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.reflect.Type
import javax.inject.Inject

interface SharedPreferencesManager {
    fun setAuthToken(token: String?)
    fun removeAuthToken()
    fun isOnBoardingVisited(): Boolean
    fun setOnBoardingVisited(visited: Boolean)
    fun isLoggedIn(): Boolean
    fun setLoggedIn(loggedIn: Boolean)
    fun isLanguageSelected(): Boolean
    fun setAppLanguage(languageJson: String)
    fun getAppLanguageJson(): String
    fun setLocale(locale: String)
    fun clearSharedPrefs()
    fun setUserId(id: String?)
    fun setEncryptedPassword(value: String)
    fun getUserId(): String
    fun getEncryptedEmailOrMobile(): String?
    fun getEncryptedPassword(): String?
    fun setBiometricStatus(value: Boolean)
    fun needToChangeAccount(value: Boolean)
    fun getChangeAccount(): Boolean
    fun getBiometricStatus(): Boolean
    fun setEncryptedMobile(value: String)
    fun setEncryptionInitVector(value: String)
    fun setVerifiedUserData(value: String)
    fun getVerifiedUserData(): String
    fun getEncryptionInitVector(): String
    fun getFirstTimeOpened() : Boolean
    fun isProductVideoDialogShownFirstTime(isFirstTime: Boolean?)
    fun getIsProductVideoDialogShownFirstTime() : Boolean
    fun getAuthToken():String
    fun getPrimaryClicked() : Boolean
    fun setPrimaryClicked(isFirstTime: Boolean?)

}

class DefaultSharedPreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val stringsResourceManager: StringsResourceManager,
    private val networkPreferencesManager: NetworkPreferencesManager,
) : SharedPreferencesManager {

    private val sharedPreferences by lazy {
        context.getSharedPreferences(
            stringsResourceManager.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }

    override fun setAuthToken(token: String?) {
        networkPreferencesManager.setAuthToken(token)
    }
    override fun removeAuthToken() {
        networkPreferencesManager.removeAuthToken()
    }

    override fun isOnBoardingVisited(): Boolean {
        return getBoolean(IS_ON_BOARDING_VISITED)
    }

    override fun setOnBoardingVisited(visited: Boolean) {
        setBoolean(IS_ON_BOARDING_VISITED, visited)
    }

    override fun isLoggedIn(): Boolean {
        return getBoolean(IS_USER_LOGGED_IN)
    }

    override fun setEncryptedPassword(value: String) =
        sharedPreferences.edit {
            putString(IS_ENCRYPTED_PASSWORD, value)
        }

    override fun setEncryptionInitVector(value: String) =
        sharedPreferences.edit {
            putString(ENCRYPTED_IV, value)
        }

    override fun setVerifiedUserData(value: String) {
        sharedPreferences.edit {
            putString(VERIFIED_USER, value)
        }
    }

    override fun getVerifiedUserData(): String =
        getString(VERIFIED_USER)


    override fun setLoggedIn(loggedIn: Boolean) {
        Bugsee.trace(Constants.LOGGED_IN, loggedIn)
        setBoolean(IS_USER_LOGGED_IN, loggedIn)
    }

    override fun getEncryptedPassword(): String =
        sharedPreferences.getString(IS_ENCRYPTED_PASSWORD, "").default

    override fun setBiometricStatus(value: Boolean) {
        sharedPreferences.edit {
            putBoolean(IS_BIOMETRIC, value)
        }
    }

    override fun needToChangeAccount(value: Boolean) {
        sharedPreferences.edit {
            putBoolean(IS_ACCOUNT_NEED_TO_CHANGE, value)
        }
    }


    override fun getChangeAccount(): Boolean =
        sharedPreferences.getBoolean(IS_BIOMETRIC, false).default

    override fun getBiometricStatus(): Boolean =
        sharedPreferences.getBoolean(IS_BIOMETRIC, false).default


    override fun setEncryptedMobile(value: String) =
        sharedPreferences.edit {
            putString(IS_ENCRYPTED_MOBILE, value)
        }

    override fun isLanguageSelected(): Boolean {
        val languageJson = getString(APP_LANGUAGE)
        return if (languageJson.isNotEmpty()) {
            convertJsonToModel<AppLanguageData>(languageJson) != null
        } else false
    }

    override fun setAppLanguage(languageJson: String) {
        setString(APP_LANGUAGE, convertObjectToJsonString(languageJson))
    }

    override fun getEncryptionInitVector(): String =
        sharedPreferences.getString(ENCRYPTED_IV, "").default

    override fun getAppLanguageJson(): String {
        return getString(APP_LANGUAGE)
    }

    override fun setLocale(locale: String) {
        networkPreferencesManager.setLocale(locale)
    }



    override fun clearSharedPrefs() {
        clearPrefs()
    }

    override fun setUserId(id: String?) {
        setString(KEY_USER_ID, id.default)
    }

    override fun getUserId(): String =
        getString(KEY_USER_ID)

    override fun getEncryptedEmailOrMobile(): String =
        sharedPreferences.getString(IS_ENCRYPTED_MOBILE, "").default

    private fun getBoolean(key: String): Boolean =
        sharedPreferences.getBoolean(key, false).default

    private fun getString(key: String): String =
        sharedPreferences.getString(key, EMPTY_STRING).default

    private fun getLong(key: String): Long =
        sharedPreferences.getLong(key, 0L)

    private fun setString(key: String, value: String) =
        sharedPreferences.edit {
            putString(key, value)
        }

    private fun getInt(key: String, default: Int = 0): Int =
        sharedPreferences.getInt(key, default).default

    private fun setInt(key: String, value: Int) =
        sharedPreferences.edit {
            putInt(key, value)
        }

    private fun setLong(key: String, value: Long) =
        sharedPreferences.edit {
            putLong(key, value)
        }

    private fun clearPrefs() =
        sharedPreferences.edit().clear()


    private fun setBoolean(key: String, value: Boolean) =
        sharedPreferences.edit {
            putBoolean(key, value)
        }
    override fun getAuthToken(): String {
        return networkPreferencesManager.getAuthToken()
    }


    override fun getFirstTimeOpened(): Boolean {
        return getBoolean(KEY_FIRST_OPENED)
    }


    override fun isProductVideoDialogShownFirstTime(isFirstTime: Boolean?) {
        setBoolean(PRODUCT_DIALOG_VISIBILITY,isFirstTime.default)
    }

    override fun getIsProductVideoDialogShownFirstTime(): Boolean {
        return getBoolean(PRODUCT_DIALOG_VISIBILITY)
    }

    override fun getPrimaryClicked(): Boolean {
        return getBoolean(IS_PRIMARY_CLICKED)
    }
    override fun setPrimaryClicked(isFirstTime: Boolean?) {
        setBoolean(IS_PRIMARY_CLICKED,isFirstTime.default)
    }

    companion object {
        private const val IS_ON_BOARDING_VISITED = "IS_ON_BOARDING_VISITED"
        private const val IS_USER_LOGGED_IN = "IS_USER_LOGGED_IN"
        private const val USER_GENDER = "gender"
        private const val PRODUCT_DIALOG_VISIBILITY = "productDialogShown"
        private const val PRODUCT_DIALOG_COUNTER = "dialogCounter"
        private const val IS_ACCOUNT_NEED_TO_CHANGE = "change_account"
        private const val APP_LANGUAGE = "APP_LANGUAGE"
        private const val KEY_FIRST_OPENED = "firstTimeAppOpened"
        private const val KEY_USER_ID = "KEY_USER_ID"
        private const val IS_BIOMETRIC = "IS_BIOMETRIC"
        private const val IS_ENCRYPTED_MOBILE = "IS_ENCRYPTED_MOBILE"
        private const val IS_ENCRYPTED_PASSWORD = "IS_ENCRYPTED_PASSWORD"
        private const val ENCRYPTED_IV = "ENCRYPTED_IV"
        private const val VERIFIED_USER = "verified_user"
        private const val MEDIA_REQUEST_LIST = "MEDIA_REQUEST_LIST"
        private const val IS_PRIMARY_CLICKED = "is_primary_clicked"
    }
}
