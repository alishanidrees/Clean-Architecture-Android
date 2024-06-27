package com.perfectlypressed.android.language.data

import android.os.Parcelable
import androidx.databinding.ObservableBoolean
import com.perfectlypressed.android.extentions.getUiDataId
import com.perfectlypressed.android.extentions.inverse
import com.perfectlypressed.android.ui.click_callback.ClickCallback
import com.tawkeel.base.data.DialogFragDismissalNavArgCallback
import java.io.Serializable

data class AppLanguageUiData(
    val id: String = getUiDataId(),
    val slug: String,
    val language: String,
    val selected: ObservableBoolean = ObservableBoolean(),
    val callback: ClickCallback<String> = object : ClickCallback<String> {
        override fun onClick(type: String) {
            // no-op
        }
    }
) {
    fun toggle() {
        selected.set(selected.get().inverse)
        callback.onClick(id)
    }

    companion object {
        internal fun default(language: String) =
            AppLanguageUiData(
                language = language,
                slug = language.lowercase(),
                selected = ObservableBoolean(false),
                callback = object : ClickCallback<String> {
                    override fun onClick(type: String) {
                        //
                    }
                }
            )
    }
}

data class AppLanguageUiDataSettings(
    val id: String = getUiDataId(),
    val slug: String,
    val language: String,
    val selected: ObservableBoolean = ObservableBoolean(),
    val callback: ClickCallback<String> = object : ClickCallback<String> {
        override fun onClick(type: String) {
            // no-op
        }
    }
) {
    fun toggle() {
        selected.set(selected.get().inverse)
        callback.onClick(id)
    }

    companion object {
        internal fun default(language: String) =
            AppLanguageUiDataSettings(
                language = language,
                slug = language.lowercase(),
                selected = ObservableBoolean(false),
                callback = object : ClickCallback<String> {
                    override fun onClick(type: String) {

                    }
                }
            )
    }
}

fun AppLanguageUiData.toAppLanguageData(): AppLanguageData =
    AppLanguageData(slug, language)

fun AppLanguageUiDataSettings.toAppLanguageDataSettings(): AppLanguageDataSettings =
    AppLanguageDataSettings(slug, language)

data class AppLanguageData(
    val slug: String,
    val language: String
) : Serializable {
    companion object {
        internal val DEFAULT = AppLanguageData("en", "English")
    }
}

data class AppLanguageDataSettings(
    val slug: String,
    val language: String
) : Serializable {
    companion object {
        internal val DEFAULT = AppLanguageDataSettings("en", "English")
    }
}
data class SpokenLanguage(
    val slug: String,
    val language: String,
    val selected: ObservableBoolean = ObservableBoolean(),
) : Serializable {
    fun toggle() {
        selected.set(selected.get().inverse)
    }
}
data class SpokenLanguageUiData(
    val language: List<SpokenLanguage>,
    val buttonText: String,
    val selectMultiple: Boolean = true,
    @Transient val callback: DialogFragDismissalNavArgCallback? = null
) : Serializable


