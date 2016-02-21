package com.igloosec.app.service;

import com.igloosec.app.dto.request.Report;
import com.igloosec.app.dto.response.ReportResponse;
import com.igloosec.app.dto.response.ResultResponse;

/**
 * Created by User on 2016-02-19.
 */
public interface ReportService {
    ReportResponse getReportDetails(String userId);
    ResultResponse createReport(String userId, Report request);
}