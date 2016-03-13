package com.igloosec.app.domain.dao;

import com.igloosec.app.dto.request.Report;
import com.igloosec.app.dto.response.Obcode;
import com.igloosec.app.dto.response.ReportResponse;

import java.util.List;

/**
 * Created by User on 2016-02-21.
 */
public interface ReportDAO {
    int createReport(String userId, Report request);
    int updateReport(String userId, String reportDate, Report request);
    List<Obcode> getObcodeList(String userId);
    List<ReportResponse> getReportList(String userId, String obcode);
}
