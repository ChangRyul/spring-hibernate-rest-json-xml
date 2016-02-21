package com.igloosec.app.domain.dao;

import com.igloosec.app.dto.request.Report;
import com.igloosec.app.dto.response.ResultResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by User on 2016-02-21.
 */
@Repository
public class JdbcReportDAO implements ReportDAO {
    private static final Logger logger = LogManager.getLogger(JdbcReportDAO.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ResultResponse createReport(String userId, Report request) {

        return null;
    }
}
