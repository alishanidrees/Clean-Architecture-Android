package com.tawkeel.base.data

sealed interface AppError {
    sealed interface AccountError : AppError {
        object UnAuthorizedUser : AccountError
        object UserNotFound : AccountError
    }

    data class WrongQuiz(val message: String) : AppError
    object PerformerNotFound : AppError
    data class NoPerformersFound(val message: String) : AppError
}
