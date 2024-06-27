package com.tawkeel.base.domain

interface Mapper<F, T> {
    fun map(from: F): T
}