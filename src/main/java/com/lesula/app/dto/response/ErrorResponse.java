package com.lesula.app.dto.response;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by enrico on 9/8/14.
 */
@XmlRootElement(name="error")
@JsonRootName(value = "error")
public class ErrorResponse {

    private String type;
    private String message;
    private String code;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
