package com.igloosec.app.dto.response;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by CRChoi on 2016. 2. 22..
 */
@XmlRootElement(name="obcode")
@JsonRootName("obcode")
public class Obcode {
    private String code;
    private String name;

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
