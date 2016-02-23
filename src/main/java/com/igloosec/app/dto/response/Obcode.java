package com.igloosec.app.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.igloosec.app.dto.ShortDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * Created by CRChoi on 2016. 2. 22..
 */
@XmlRootElement(name="obcode")
@JsonRootName("obcode")
public class Obcode {
    private String code;
    private String name;
    private Date date;

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

    @XmlElement
    @JsonFormat(pattern = "yyyy-MM-dd")
    @XmlJavaTypeAdapter(value=ShortDateAdapter.class, type = Date.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
