package com.igloosec.app.dto.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CRChoi on 2016. 2. 22..
 */
@XmlRootElement(name="items")
public class Obcodes implements Serializable {
    private static final long serialVersionUID = 1L;

//    private String code;
//    private String message;
    private List<Obcode> obcode = new ArrayList<Obcode>();

    public List<Obcode> getObcode() {
        return obcode;
    }

    public void setObcode(List<Obcode> obcode) {
        this.obcode = obcode;
    }

//    @XmlElement
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    @XmlElement
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
}