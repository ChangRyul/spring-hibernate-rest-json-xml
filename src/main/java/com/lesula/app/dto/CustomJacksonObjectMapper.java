package com.lesula.app.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by enrico on 9/9/14.
 */
public class CustomJacksonObjectMapper extends ObjectMapper {

    public CustomJacksonObjectMapper(){
        super();
//        this.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        this.configure(SerializationFeature.WRAP_ROOT_VALUE.WRAP_ROOT_VALUE, true);
    }
}
