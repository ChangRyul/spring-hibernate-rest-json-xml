package com.igloosec.app.dto.response;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by User on 2016-03-31.
 */
public class AgentDesc {
    private String code;
    private String name;

    @XmlElement
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
