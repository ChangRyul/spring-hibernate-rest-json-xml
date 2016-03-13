package com.igloosec.app.service;

import com.igloosec.app.dto.request.Report;
import com.igloosec.app.dto.response.Obcode;
import com.igloosec.app.dto.response.ReportResponse;
import com.igloosec.app.dto.response.Reports;
import com.igloosec.app.dto.response.ResultResponse;

import java.util.List;

/**
 * Created by User on 2016-02-19.
 */
public interface ReportService {
    ReportResponse getReportDetails(String userId);
    ResultResponse createReport(String userId, Report request);
    List<Obcode> getUserObcodeList(String userId);
    List<ReportResponse> getReportList(String userId, String obcode);
    ResultResponse updateReport(String userId, String reportDate, Report request);
}
