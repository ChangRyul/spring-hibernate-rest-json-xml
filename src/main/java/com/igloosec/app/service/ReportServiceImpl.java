package com.igloosec.app.service;

import com.igloosec.app.dto.request.ReportCreateRequest;
import com.igloosec.app.dto.response.ReportResponse;
import com.igloosec.app.dto.response.ResultResponse;
import org.springframework.stereotype.Service;

/**
 * Created by User on 2016-02-19.
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Override
    public ReportResponse getReportDetails(String userId) {
        return null;
    }

    @Override
    public ResultResponse createReport(String userId, ReportCreateRequest request) {
        return null;
    }
}
