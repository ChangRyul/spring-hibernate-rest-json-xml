package com.igloosec.app.service;

import com.igloosec.app.domain.dao.ReportDAO;
import com.igloosec.app.dto.request.Report;
import com.igloosec.app.dto.response.Obcode;
import com.igloosec.app.dto.response.ReportResponse;
import com.igloosec.app.dto.response.Reports;
import com.igloosec.app.dto.response.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        int result = reportDAO.createReport(userId, request);

        ResultResponse resultResponse;

        if (result == 1) {
            resultResponse = new ResultResponse();
            resultResponse.setCode("R001");
            resultResponse.setMessage("리포트 등록 성공");
        } else if (result == 2) {
            resultResponse = new ResultResponse();
            resultResponse.setCode("R002");
            resultResponse.setMessage("이미 리포트가 등록 되어 있음");
        } else {
            resultResponse = new ResultResponse();
            resultResponse.setCode("R003");
            resultResponse.setMessage("리포트 등록 실패");
        }

        return resultResponse;
    }

    @Override
    public List<Obcode> getUserObcodeList(String userId) {
        List<Obcode> obcodeList = reportDAO.getObcodeList(userId);
        return obcodeList;
    }

    @Override
    public List<ReportResponse> getReportList(String userId, String obcode) {
        List<ReportResponse> reportList = reportDAO.getReportList(userId, obcode);
        return reportList;
    }

    @Override
    public ResultResponse updateReport(String userId, String reportDate, Report request) {
        int result = reportDAO.updateReport(userId, reportDate, request);

        ResultResponse resultResponse;

        if (result == 1) {
            resultResponse = new ResultResponse();
            resultResponse.setCode("R001");
            resultResponse.setMessage("리포트 수정 성공");
        } else if (result == 2) {
            resultResponse = new ResultResponse();
            resultResponse.setCode("R002");
            resultResponse.setMessage("이미 리포트가 등록 되어 있음");
        } else {
            resultResponse = new ResultResponse();
            resultResponse.setCode("R003");
            resultResponse.setMessage("리포트 수정 실패");
        }

        return resultResponse;
    }
}
