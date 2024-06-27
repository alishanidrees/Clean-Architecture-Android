package com.tawkeel.base.domain

interface ProvideThroughParams<Params, ReturnType> {
    fun get(params: Params): ReturnType
}
