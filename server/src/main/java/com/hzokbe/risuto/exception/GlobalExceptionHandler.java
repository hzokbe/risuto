package com.hzokbe.risuto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hzokbe.risuto.exception.user.InvalidPasswordException;
import com.hzokbe.risuto.exception.user.InvalidUsernameException;
import com.hzokbe.risuto.exception.user.UserAlreadyRegisteredException;
import com.hzokbe.risuto.model.exception.response.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionResponse exceptionHandler(Exception exception) {
        return new ExceptionResponse("internal server error");
    }

    @ExceptionHandler(InvalidUsernameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse invalidUsernameExceptionHandler(InvalidUsernameException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse invalidPasswordExceptionHandler(InvalidPasswordException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse userAlreadyRegisteredExceptionHandler(UserAlreadyRegisteredException exception) {
        return new ExceptionResponse(exception.getMessage());
    }
}
