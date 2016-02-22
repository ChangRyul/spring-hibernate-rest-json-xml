package com.igloosec.app.domain.dao;

import com.igloosec.app.dto.request.Report;
import com.igloosec.app.dto.response.Obcode;

import java.util.List;

/**
 * Created by User on 2016-02-21.
 */
public interface ReportDAO {
    int createReport(String userId, Report request);

    List<Obcode> getObcodeList(String userId);
}
