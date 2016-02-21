package com.igloosec.app.domain.dao;

import com.igloosec.app.dto.response.ResultResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by enrico on 9/5/14.
 */
@Repository
public class JdbcUserDAO implements UserDAO {
    private static final Logger logger = LogManager.getLogger(JdbcUserDAO.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int verifyUser(String userId, String password) {
        logger.error("userId: " + userId + ", userPw: " + password);
        int rowCount = this.jdbcTemplate.queryForObject("select count(*) from user_list where user_id = ? and passwd = ?", new Object[]{userId, password}, Integer.class);

        return rowCount;
    }
}
