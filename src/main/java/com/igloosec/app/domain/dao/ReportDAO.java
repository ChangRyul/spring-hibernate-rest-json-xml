package com.igloosec.app.domain.dao;

import com.igloosec.app.dto.request.Report;
import com.igloosec.app.dto.response.ResultResponse;

/**
 * Created by User on 2016-02-21.
 */
public interface ReportDAO {
    ResultResponse createReport(String userId, Report request);
}
