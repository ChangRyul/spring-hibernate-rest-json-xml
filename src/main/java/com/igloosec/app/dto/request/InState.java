package com.igloosec.app.dto.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by CRChoi on 2016. 2. 22..
 */
@XmlRootElement(name="items")
public class InState {
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
    public Date getIn_date() {
        return in_date;
    }

    public void setIn_date(Date in_date) {
        this.in_date = in_date;
    }

    @XmlElement
    public Date getOut_date() {
        return out_date;
    }

    public void setOut_date(Date out_date) {
        this.out_date = out_date;
    }

    @Override
    public String toString() {
        return "InState{" +
                "in=" + in +
                ", in_date=" + in_date +
                ", out_date=" + out_date +
                '}';
    }
}
