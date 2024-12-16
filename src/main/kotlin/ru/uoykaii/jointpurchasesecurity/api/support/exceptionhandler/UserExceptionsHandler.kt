package ru.uoykaii.jointpurchasesecurity.api.support.exceptionhandler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ru.uoykaii.jointpurchasesecurity.api.model.response.ExceptionResponse
import ru.uoykaii.jointpurchasesecurity.domain.model.exception.UserAlreadyExistsException
import ru.uoykaii.jointpurchasesecurity.domain.model.exception.UserNotFoundException
import ru.uoykaii.jointpurchasesecurity.domain.model.exception.WrongPasswordException

@RestControllerAdvice
class UserExceptionsHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler
    fun handleUserAlreadyExistsException(ex: UserAlreadyExistsException) = makeResponse(HttpStatus.CONFLICT, ex)

    @ExceptionHandler
    fun handleUserNotFoundException(ex: UserNotFoundException) = makeResponse(HttpStatus.NOT_FOUND, ex)

    @ExceptionHandler
    fun handleWrongPasswordException(ex: WrongPasswordException) = makeResponse(HttpStatus.FORBIDDEN, ex)

    private fun makeResponse(status: HttpStatus, ex: Exception) = ResponseEntity(
        ExceptionResponse(
            status.toString(),
            ex.message ?: "An unexpected error occurred"
        ),
        status
    )
}