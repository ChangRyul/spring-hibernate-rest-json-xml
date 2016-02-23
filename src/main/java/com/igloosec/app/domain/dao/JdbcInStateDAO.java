package com.igloosec.app.domain.dao;

import com.igloosec.app.dto.request.InState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        if (checkExistInState(jdbcReportDAO.getOlcode(userId), inState.getIn_date()))
            return 2;

        String query = "insert into layer_in_state (ol_code, \"in\", in_date, out_date) VALUES (?,?,?,?)";

        int result = jdbcTemplate.update(query, new Object[]{jdbcReportDAO.getOlcode(userId), inState.getIn(), inState.getIn_date(), inState.getOut_date()});

        return result;
    }

    private boolean checkExistInState(String olcode, Date inDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String stDate = format.format(inDate);

        String query = "select count(*) from layer_in_state where to_char(in_date, 'YYYY-MM-DD') = ? and ol_code = ?";
        int count = this.jdbcTemplate.queryForObject(query, new Object[]{stDate, olcode}, Integer.class);

        if (count >= 1) {
            return true;
        } else {
            return false;
        }
    }
}