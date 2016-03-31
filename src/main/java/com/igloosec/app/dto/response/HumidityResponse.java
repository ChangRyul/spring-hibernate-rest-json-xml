package com.igloosec.app.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.igloosec.app.dto.ShortDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * Created by User on 2016-03-31.
 */
@XmlRootElement(name="humidity")
@JsonRootName("humidity")
public class HumidityResponse {
    private String agentcode;
    private Long time;
    private int dw;
    private String type;
    private double val;
    private String unit;

    @XmlElement
    public String getAgentcode() {
        return agentcode;
    }

    public void setAgentcode(String agentcode) {
        this.agentcode = agentcode;
    }

    @XmlElement
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
//    @XmlJavaTypeAdapter(value=ShortDateAdapter.class, type = Date.class)
    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @XmlElement
    public int getDw() {
        return dw;
    }

    public void setDw(int dw) {
        this.dw = dw;
    }

    @XmlElement
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement
    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    @XmlElement
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "TemperatureResponse{" +
                "time=" + time +
                ", dw=" + dw +
                ", type='" + type + '\'' +
                ", val=" + val +
                ", unit='" + unit + '\'' +
                '}';
    }
}
