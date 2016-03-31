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
@XmlRootElement(name="flux")
@JsonRootName("flux")
public class Flux {
    private String agentcode;
    private String name;
    private double val;
    private Long time;
    private double max;
    private double min;

    @XmlElement
    public String getAgentcode() {
        return agentcode;
    }

    public void setAgentcode(String agentcode) {
        this.agentcode = agentcode;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    @XmlElement
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @XmlJavaTypeAdapter(value=ShortDateAdapter.class, type = Date.class)
    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "Flux{" +
                "agentcode='" + agentcode + '\'' +
                ", name='" + name + '\'' +
                ", val=" + val +
                ", time=" + time +
                ", max=" + max +
                ", min=" + min +
                '}';
    }
}
