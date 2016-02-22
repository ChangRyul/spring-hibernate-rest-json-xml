package com.igloosec.app.domain.dao;

import com.igloosec.app.dto.request.InState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by CRChoi on 2016. 2. 22..
 */
@Repository
public class JdbcInStateDAO implements InStateDAO {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    JdbcReportDAO jdbcReportDAO;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int createInState(String userId, InState inState) {
        if (checkExistInState(jdbcReportDAO.getOlcode(userId)))
            return 2;

        String query = "insert into layer_in_state (ol_code, \"in\", in_date, out_date) VALUES (?,?,?,?)";

        int result = jdbcTemplate.update(query, new Object[]{jdbcReportDAO.getOlcode(userId), inState.getIn(), inState.getIn_date(), inState.getOut_date()});

        return result;
    }

    private boolean checkExistInState(String olcode) {
        String query = "select count(*) from layer_in_state where to_char(in_date, 'YYYY-MM-DD') = to_char(current_date, 'YYYY-MM-DD') and ol_code = ?";
        int count = this.jdbcTemplate.queryForObject(query, new Object[]{olcode}, Integer.class);

        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }
}