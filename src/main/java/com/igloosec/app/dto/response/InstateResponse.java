package com.igloosec.app.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.igloosec.app.dto.ShortDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * Created by User on 2016-03-13.
 */
@XmlRootElement(name="instate")
public class InstateResponse {
    private int in;
    private Date in_date;
    private Date out_date;

    @XmlElement
    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    @XmlElement
    @JsonFormat(pattern = "yyyy-MM-dd")
    @XmlJavaTypeAdapter(value=ShortDateAdapter.class, type = Date.class)
    public Date getIn_date() {
        return in_date;
    }

    public void setIn_date(Date in_date) {
        this.in_date = in_date;
    }

    @XmlElement
    @JsonFormat(pattern = "yyyy-MM-dd")
    @XmlJavaTypeAdapter(value=ShortDateAdapter.class, type = Date.class)
    public Date getOut_date() {
        return out_date;
    }

    public void setOut_date(Date out_date) {
        this.out_date = out_date;
    }

    @Override
    public String toString() {
        return "InstateResponse{" +
                "in=" + in +
                ", in_date=" + in_date +
                ", out_date=" + out_date +
                '}';
    }
}
