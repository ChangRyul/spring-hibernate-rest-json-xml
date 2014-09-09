package com.lesula.app.exceptions;

import com.lesula.app.dto.response.ErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by enrico on 9/5/14.
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    static Logger LOG = LogManager.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(value=Exception.class)
    public @ResponseBody ErrorResponse unknownExceptionHandler(HttpServletRequest request, Exception e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("An exceptions has occurred. Please try again later.");
        errorResponse.setType("UnknownException");
        LOG.error("Unknown exception", e);
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