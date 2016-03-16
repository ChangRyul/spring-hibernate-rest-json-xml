package com.igloosec.app.domain.dao;

import com.igloosec.app.dto.request.InState;
import com.igloosec.app.dto.response.InstateResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by CRChoi on 2016. 2. 22..
 */
@Repository
public class JdbcInStateDAO implements InStateDAO {
    private static final Logger logger = LogManager.getLogger(JdbcInStateDAO.class);

    private JdbcTemplate jdbcTemplate;
    @Autowired
    JdbcReportDAO jdbcReportDAO;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int createInState(String userId, InState inState) {
        if (checkExistInState(jdbcReportDAO.getOlcode(userId), inState.getIn_date(), null))
            return 2;

        String query = "insert into layer_in_state (ol_code, \"in\", in_date, out_date, litter) VALUES (?,?,?,?,?)";

        int result = jdbcTemplate.update(query, new Object[]{jdbcReportDAO.getOlcode(userId), inState.getIn(), inState.getIn_date(), inState.getOut_date(), inState.getLitter()});

        return result;
    }

    @Override
    public List<InstateResponse> getInstateList(String userId) {
        List<InstateResponse> instateResponseList = new ArrayList<>();

        String query = "SELECT * FROM layer_in_state WHERE ol_code = ? ORDER BY in_date DESC;";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, new Object[]{jdbcReportDAO.getOlcode(userId)});

        for (Map<String, Object> row : rows) {
            InstateResponse instateResponse = new InstateResponse();
            instateResponse.setIn_date((Date)row.get("in_date"));
            instateResponse.setIn((int)row.get("in"));
            instateResponse.setOut_date((Date)row.get("out_date"));
            instateResponse.setLitter((int)row.get("litter"));

            instateResponseList.add(instateResponse);
        }

        return instateResponseList;
    }

    @Override
    public int updateInState(String userId, String targetDate, InState inState) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String inDate = format.format(inState.getIn_date());

        if (!targetDate.equals(inDate)) {
            if (checkExistInState(jdbcReportDAO.getOlcode(userId), inState.getIn_date(), targetDate))
                return 2;
        }

        String query = "UPDATE layer_in_state SET \"in\" = ?, in_date = ?, out_date = ?, litter = ? WHERE ol_code = ? AND to_char(in_date, 'YYYY-MM-DD') = ?";

        int result = jdbcTemplate.update(query, new Object[]{inState.getIn(), inState.getIn_date(), inState.getOut_date(), inState.getLitter(), jdbcReportDAO.getOlcode(userId), targetDate});

        return result;
    }

    private boolean checkExistInState(String olcode, Date inDate, String targetDate) {
        String stDate = "";

//        if (targetDate == null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            stDate = format.format(inDate);
//        } else {
//            stDate = targetDate;
//        }

        String query = "select count(*) from layer_in_state where to_char(in_date, 'YYYY-MM-DD') = ? and ol_code = ?";
        int count = this.jdbcTemplate.queryForObject(query, new Object[]{stDate, olcode}, Integer.class);

        if (count >= 1) {
            return true;
        } else {
            return false;
        }
    }
}