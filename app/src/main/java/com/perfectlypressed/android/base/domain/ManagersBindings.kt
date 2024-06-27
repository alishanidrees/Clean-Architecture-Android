package com.perfectlypressed.android.base.domain


import com.perfectlypressed.android.cache.DefaultSharedPreferencesManager
import com.perfectlypressed.android.cache.SharedPreferencesManager
import com.perfectlypressed.android.ui.dialog.DefaultDialogProvider
import com.perfectlypressed.android.ui.dialog.DefaultErrorDialogProvider
import com.perfectlypressed.android.ui.dialog.DefaultGeneralDialogProvider
import com.perfectlypressed.android.ui.dialog.DefaultNoInternetDialogProvider
import com.perfectlypressed.android.ui.dialog.DefaultSuccessDialogProvider
import com.perfectlypressed.android.ui.dialog.DialogProvider
import com.perfectlypressed.android.ui.dialog.ErrorDialogProvider
import com.perfectlypressed.android.ui.dialog.GeneralDialogProvider
import com.perfectlypressed.android.ui.dialog.NoInternetDialogProvider
import com.perfectlypressed.android.ui.dialog.SuccessDialogProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ManagersBindings {

    @Binds
    @Singleton
    fun bindSharedPreferencesManager(default: DefaultSharedPreferencesManager): SharedPreferencesManager


}

@Module
@InstallIn(SingletonComponent::class)
interface ProvidersBindings {


    @Binds
    fun bindErrorDialogProvider(default: DefaultErrorDialogProvider): ErrorDialogProvider

    @Binds
    fun bindSuccessDialogProvider(default: DefaultSuccessDialogProvider): SuccessDialogProvider

    @Binds
    fun bindNoInternetDialogProvider(default: DefaultNoInternetDialogProvider): NoInternetDialogProvider

    @Binds
    fun bindInfoDialogProvider(default: DefaultGeneralDialogProvider): GeneralDialogProvider


    @Binds
    fun bindDialogProvider(default: DefaultDialogProvider): DialogProvider

}
