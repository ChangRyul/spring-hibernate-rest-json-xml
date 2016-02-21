package com.igloosec.app.service;

import com.igloosec.app.domain.dao.ReportDAO;
import com.igloosec.app.dto.request.Report;
import com.igloosec.app.dto.response.ReportResponse;
import com.igloosec.app.dto.response.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User on 2016-02-19.
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportDAO reportDAO;

    @Override
    public ReportResponse getReportDetails(String userId) {
        return null;
    }

    @Override
    public ResultResponse createReport(String userId, Report request) {
        reportDAO.createReport(userId, request);
        return null;
    }
}
