package com.igloosec.app.dto.response;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by User on 2016-03-31.
 */
@XmlRootElement(name="weight")
@JsonRootName("weight")
public class Weight {
    private String agentcode;
    private String name;
    private Date time;
    private double val;

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
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @XmlElement
    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Weight{" +
                "agentcode='" + agentcode + '\'' +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", val=" + val +
                '}';
    }
}
