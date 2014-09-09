package com.lesula.app.exceptions;

import org.springframework.validation.FieldError;

import java.util.List;

/**
 * Created by enrico on 9/9/14.
 */
public class DtoValidationException extends AppException{

    private String message;

    public DtoValidationException(List<FieldError> errors){
        StringBuilder builder = new StringBuilder();
        for(FieldError error : errors){
            builder
                    .append(error.getField())
                    .append(":")
                    .append(error.getDefaultMessage())
                    .append(";");
        }

        this.message = builder.toString();

    }

    @Override
    public String getMessage(){
        return message;
    }
    @Override
    public String getCode() {
        return null;
    }
}
