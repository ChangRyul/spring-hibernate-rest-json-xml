package com.igloosec.app.domain.dao;

import com.igloosec.app.dto.request.Report;
import com.igloosec.app.dto.response.Obcode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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
        if (checkExistTodayReport(userId, request.getOb_code()))
            return 2;

        String query = "INSERT INTO daily_report (report_time, ob_code, ol_code, user_id, in_count, out_count, total, selection, perish, litter, feed, preventive, disease, equipment) \n" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        int result = jdbcTemplate.update(query, new Object[]{new Date(), request.getOb_code(), getOlcode(userId), userId, request.getIn_count(), request.getOut_count(), request.getTotal(), request.getSelection(), request.getPerish(), request.getLitter(), request.getFeed(), request.getPreventive(), request.getDisease(), request.getEquipment()});
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
            logger.error(obcode.getCode() + ":" + obcode.getName());
            obcodeList.add(obcode);
        }

        return obcodeList;
    }

    private boolean checkExistTodayReport(String userId, String obcode) {
        String query = "select count(*) from daily_report where to_char(report_time, 'YYYY-MM-DD') = to_char(current_date, 'YYYY-MM-DD') and ob_code = ? and user_id = ?";
        int count = this.jdbcTemplate.queryForObject(query, new Object[]{obcode, userId}, Integer.class);

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
