package com.igloosec.app.domain.dao;

import com.igloosec.app.dto.request.Report;
import com.igloosec.app.dto.response.Obcode;
import com.igloosec.app.dto.response.ReportResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public int createReport(String userId, Report request) {
        if (checkExistReport(userId, request, null))
            return 2;

        String query = "INSERT INTO daily_report (report_time, ob_code, ol_code, user_id, in_count, out_count, total, selection, perish, litter, feed, preventive, disease, equipment) \n" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        int result = jdbcTemplate.update(query, new Object[]{request.getReport_date(), request.getOb_code(), getOlcode(userId), userId, request.getIn_count(), request.getOut_count(), request.getTotal(), request.getSelection(), request.getPerish(), request.getLitter(), request.getFeed(), request.getPreventive(), request.getDisease(), request.getEquipment()});
        return result;
    }

    @Override
    public int updateReport(String userId, String reportDate, Report report) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String reportDateOri = format.format(report.getReport_date());
        if (!reportDate.equals(reportDateOri)) {
            if (checkExistReport(userId, report, reportDate))
                return 2;
        }

        logger.error(report.toString());

        String query = "UPDATE daily_report SET report_time = ?, selection = ?, perish = ?, litter = ?, preventive = ?, disease = ?, equipment = ? WHERE to_char(report_time, 'YYYY-MM-DD') = ? AND ob_code = ? AND ol_code = ? AND user_id = ?";

        int result = -1;

        try {
            result= jdbcTemplate.update(query, new Object[]{report.getReport_date(), report.getSelection(), report.getPerish(), report.getLitter(), report.getPreventive(), report.getDisease(), report.getEquipment(), reportDate, report.getOb_code(), getOlcode(userId), userId});
        } catch (Exception ex) {
            logger.error(ex);
        }

        return result;
    }

    @Override
    public List<Obcode> getObcodeList(String userId) {
        List<Obcode> obcodeList = new ArrayList<>();

        String query = "select b.ob_code, b.name from user_layer_mapping a, sp_out_bdlist b where a.user_id = ? and a.ol_code = b.ol_code";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, new Object[]{userId});

        for (Map<String, Object> row : rows) {
            Obcode obcode = new Obcode();
            obcode.setCode((String)row.get("ob_code"));
            obcode.setName((String)row.get("name"));

            Date outDate = jdbcTemplate.queryForObject("select date from building_inout_state where ob_code = ? and type = 0", new Object[]{obcode.getCode()}, Date.class);
            obcode.setDate(outDate);
            //logger.error(obcode.getCode() + ":" + obcode.getName());
            obcodeList.add(obcode);
        }

        return obcodeList;
    }

    @Override
    public List<ReportResponse> getReportList(String userId, String obcode) {
        List<ReportResponse> reportList = new ArrayList<>();

        String query = "SELECT a.report_time, b.name, a.selection, a.perish, a.litter, a.preventive, a.disease, a.equipment, a.ob_code FROM daily_report a, sp_out_bdlist b WHERE a.ob_code = b.ob_code AND a.user_id = ? ORDER BY report_time DESC";
        //String query = "SELECT a.report_time, b.name, a.selection, a.perish, a.litter, a.preventive, a.disease, a.equipment FROM daily_report a, sp_out_bdlist b WHERE a.ob_code = b.ob_code AND a.user_id = ? AND a.ob_code = ? ORDER BY report_time DESC";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, new Object[]{userId});
        //List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, new Object[]{userId, obcode});

        for (Map<String, Object> row : rows) {
            ReportResponse report = new ReportResponse();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            report.setReport_date(format.format((Date)row.get("report_time")));
            report.setOb_code((String)row.get("ob_code"));
            report.setBd_name((String)row.get("name"));
            report.setSelection((int)row.get("selection"));
            report.setPerish((int)row.get("perish"));
            report.setLitter((int)row.get("litter"));
            report.setPreventive((int)row.get("preventive"));
            report.setDisease((int)row.get("disease"));
            report.setEquipment((int)row.get("equipment"));

            reportList.add(report);
        }

        return reportList;
    }

    private boolean checkExistReport(String userId, Report report, String targetDate) {
        String query = "select count(*) from daily_report where to_char(report_time, 'YYYY-MM-DD') = ? and ob_code = ? and user_id = ?";

        int count = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        if (targetDate == null) {
            count = this.jdbcTemplate.queryForObject(query, new Object[]{format.format(report.getReport_date()), report.getOb_code(), userId}, Integer.class);
        } else {
            logger.error(targetDate);
            count = this.jdbcTemplate.queryForObject(query, new Object[]{format.format(report.getReport_date()), report.getOb_code(), userId}, Integer.class);
        }

        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    public String getOlcode(String userId) {
        return this.jdbcTemplate.queryForObject("select a.ol_code from user_layer_mapping a, sp_out_layerlist b where a.user_id = ? and a.ol_code = b.ol_code", new Object[]{userId}, String.class);
    }
}
