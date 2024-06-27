package com.tawkeel.base.domain

import com.perfectlypressed.android.base.data.ViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface FlowUseCase<in P, R> {
    operator fun invoke(parameters: P): Flow<ViewState<R>> =
        execute(parameters).flowOn(dispatcher())
    fun execute(parameters: P): Flow<ViewState<R>>
    fun dispatcher(): CoroutineDispatcher
}
