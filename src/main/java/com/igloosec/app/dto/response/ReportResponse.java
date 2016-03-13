package com.igloosec.app.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.igloosec.app.dto.ShortDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * Created by User on 2016-02-19.
 */
public class ReportResponse {
    private String report_date;
    private String ob_code;
    private String bd_name;
    private int selection;
    private int perish;
    private int litter;
    private int preventive;
    private int disease;
    private int equipment;

    @XmlElement
    public String getReport_date() {
        return report_date;
    }

    public void setReport_date(String report_date) {
        this.report_date = report_date;
    }

    @XmlElement
    public String getOb_code() {
        return ob_code;
    }

    public void setOb_code(String ob_code) {
        this.ob_code = ob_code;
    }

    @XmlElement
    public String getBd_name() {
        return bd_name;
    }

    public void setBd_name(String bd_name) {
        this.bd_name = bd_name;
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

    @Override
    public String toString() {
        return "ReportResponse{" +
                "report_date=" + report_date +
                ", ob_code='" + ob_code + '\'' +
                ", bd_name='" + bd_name + '\'' +
                ", selection=" + selection +
                ", perish=" + perish +
                ", litter=" + litter +
                ", preventive=" + preventive +
                ", disease=" + disease +
                ", equipment=" + equipment +
                '}';
    }
}
