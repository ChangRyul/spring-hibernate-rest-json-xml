package com.igloosec.app.dto.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by User on 2016-02-19.
 */
@XmlRootElement
public class ReportCreateRequest {
    private int dotaeCount;
    private int pesaCount;
    private int ggalzipAmount;
    private int disinfectionYN;
    private int diseaseCode;
    private int deviceAbnormalCode;

    @XmlElement
    public int getDotaeCount() {
        return dotaeCount;
    }

    public void setDotaeCount(int dotaeCount) {
        this.dotaeCount = dotaeCount;
    }

    @XmlElement
    public int getPesaCount() {
        return pesaCount;
    }

    public void setPesaCount(int pesaCount) {
        this.pesaCount = pesaCount;
    }

    @XmlElement
    public int getGgalzipAmount() {
        return ggalzipAmount;
    }

    public void setGgalzipAmount(int ggalzipAmount) {
        this.ggalzipAmount = ggalzipAmount;
    }

    @XmlElement
    public int getDisinfectionYN() {
        return disinfectionYN;
    }

    public void setDisinfectionYN(int disinfectionYN) {
        this.disinfectionYN = disinfectionYN;
    }

    @XmlElement
    public int getDiseaseCode() {
        return diseaseCode;
    }

    public void setDiseaseCode(int diseaseCode) {
        this.diseaseCode = diseaseCode;
    }

    @XmlElement
    public int getDeviceAbnormalCode() {
        return deviceAbnormalCode;
    }

    public void setDeviceAbnormalCode(int deviceAbnormalCode) {
        this.deviceAbnormalCode = deviceAbnormalCode;
    }
}
