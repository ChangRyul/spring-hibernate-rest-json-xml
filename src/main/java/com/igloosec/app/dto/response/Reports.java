package com.igloosec.app.dto.response;

import com.igloosec.app.dto.request.Report;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016-03-11.
 */
@XmlRootElement(name="reports")
public class Reports implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<ReportResponse> report = new ArrayList<ReportResponse>();

    public List<ReportResponse> getReport() {
        return report;
    }

    public void setReport(List<ReportResponse> report) {
        this.report = report;
    }
}
