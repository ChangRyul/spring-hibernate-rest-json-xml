package com.igloosec.app.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.igloosec.app.dto.ShortDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * Created by User on 2016-02-19.
 */
@XmlRootElement(name="items")
public class Report {
    private String ob_code;
    private int in_count;
    private int out_count;
    private int total;
    private int selection;
    private int perish;
    private int litter;
    private int feed;
    private int preventive;
    private int disease;
    private int equipment;
    private Date report_date;

    @XmlElement
    public String getOb_code() {
        return ob_code;
    }

    public void setOb_code(String ob_code) {
        this.ob_code = ob_code;
    }

    @XmlElement
    public int getIn_count() {
        return in_count;
    }

    public void setIn_count(int in_count) {
        this.in_count = in_count;
    }

    @XmlElement
    public int getOut_count() {
        return out_count;
    }

    public void setOut_count(int out_count) {
        this.out_count = out_count;
    }

    @XmlElement
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @XmlElement
    public int getSelection() {
        return selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

    @XmlElement
    public int getPerish() {
        return perish;
    }

    public void setPerish(int perish) {
        this.perish = perish;
    }

    @XmlElement
    public int getLitter() {
        return litter;
    }

    public void setLitter(int litter) {
        this.litter = litter;
    }

    @XmlElement
    public int getFeed() {
        return feed;
    }

    public void setFeed(int feed) {
        this.feed = feed;
    }

    @XmlElement
    public int getPreventive() {
        return preventive;
    }

    public void setPreventive(int preventive) {
        this.preventive = preventive;
    }

    @XmlElement
    public int getDisease() {
        return disease;
    }

    public void setDisease(int disease) {
        this.disease = disease;
    }

    @XmlElement
    public int getEquipment() {
        return equipment;
    }

    public void setEquipment(int equipment) {
        this.equipment = equipment;
    }

    @XmlElement
    @JsonFormat(pattern = "yyyy-MM-dd")
    @XmlJavaTypeAdapter(value=ShortDateAdapter.class, type = Date.class)
    public Date getReport_date() {
        return report_date;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    @Override
    public String toString() {
        return "Report{" +
                "ob_code='" + ob_code + '\'' +
                ", in_count=" + in_count +
                ", out_count=" + out_count +
                ", total=" + total +
                ", selection=" + selection +
                ", perish=" + perish +
                ", litter=" + litter +
                ", feed=" + feed +
                ", preventive=" + preventive +
                ", disease=" + disease +
                ", equipment=" + equipment +
                ", report_date=" + report_date +
                '}';
    }
}
