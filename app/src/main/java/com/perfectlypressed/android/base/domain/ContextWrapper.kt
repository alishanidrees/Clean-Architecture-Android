package com.perfectlypressed.android.base.domain

import android.annotation.SuppressLint
import android.content.Context
import android.os.LocaleList
import com.perfectlypressed.android.extentions.BuildUtils.isVersionGreaterThan
import java.util.*

class ContextWrapper(base: Context?) : android.content.ContextWrapper(base) {
    companion object {
        @SuppressLint("NewApi")
        fun wrap(context: Context?, newLocale: Locale?): ContextWrapper {
            var context = context
            val res = context?.resources
            val configuration = res?.configuration
            if (isVersionGreaterThan(24, true)) {
                configuration?.setLocale(newLocale)
                val localeList = LocaleList(newLocale)
                LocaleList.setDefault(localeList)
                configuration?.setLocales(localeList)
                context = context?.createConfigurationContext(configuration!!)
            } else if (isVersionGreaterThan(17, true)) {
                configuration?.setLocale(newLocale)
                context = context?.createConfigurationContext(configuration!!)
            } else {
                configuration?.locale = newLocale
                res?.updateConfiguration(configuration, res.displayMetrics)
            }
            return ContextWrapper(context)
        }
    }
}