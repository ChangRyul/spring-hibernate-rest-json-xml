package com.lesula.app.error.handlers;

import com.lesula.app.dto.response.ErrorResponse;
import com.lesula.app.error.AppException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by enrico on 9/5/14.
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public @ResponseBody ErrorResponse unknownExceptionHandler(HttpServletRequest request, Exception e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("An error has occurred. Please try again later.");
        errorResponse.setType("UnknownException");
        return errorResponse;
    }

    @ExceptionHandler(value=AppException.class)
    public @ResponseBody ErrorResponse defaultExceptionHandler(HttpServletRequest request, Exception e){
        AppException appException = (AppException) e;
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(appException.getMessage());
        errorResponse.setType(appException.getClass().getSimpleName());
        errorResponse.setCode(appException.getCode());
        return errorResponse;
    }
}