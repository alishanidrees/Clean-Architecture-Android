package com.tawkeel.resources

import com.perfectlypressed.android.resources.ColorResourceManager
import com.perfectlypressed.android.resources.DefaultColorResourceManager
import com.perfectlypressed.android.resources.DefaultDrawableResourceManager
import com.perfectlypressed.android.resources.DrawableResourceManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ResourcesBindings {

    @Binds
    fun bindStringsResourceManager(default: DefaultStringsResourceManager): StringsResourceManager

    @Binds
    fun bindDrawableResourceManager(default: DefaultDrawableResourceManager): DrawableResourceManager

    @Binds
    fun bindColorResourceManager(default: DefaultColorResourceManager): ColorResourceManager
}
