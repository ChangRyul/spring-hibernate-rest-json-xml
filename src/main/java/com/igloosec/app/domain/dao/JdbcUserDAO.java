package com.igloosec.app.domain.dao;

import com.igloosec.app.dto.response.ResultResponse;
import com.igloosec.app.dto.response.UserResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        //logger.error("userId: " + userId + ", userPw: " + password);
        int rowCount = this.jdbcTemplate.queryForObject("select count(*) from user_list where user_id = ? and passwd = ?", new Object[]{userId, password}, Integer.class);
        return rowCount;
    }

    @Override
    public UserResponse getUserDetails(String userId) {
        String query = "select c.name, a.user_id, a.user_name, a.tel, a.note as address, b.ol_code, b.mi_code, b.birthday, b.person, b.career, b.contract, b.loan, b.grade, b.layer_grade, b.keep_grade\n" +
                "from ( select * from user_list ) a, ( select * from user_layer_mapping ) b, ( select * from sp_out_layerlist ) c\n" +
                "where a.user_id = b.user_id and a.user_id = ? and b.ol_code = c.ol_code and b.mi_code = c.mi_code";

        UserResponse userResponse = this.jdbcTemplate.queryForObject(query, new Object[]{userId}, new RowMapper<UserResponse>() {
                    public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                        UserResponse userResponse1 = new UserResponse();
                        userResponse1.setName(rs.getString("name"));
                        userResponse1.setUser_id(rs.getString("user_id"));
                        userResponse1.setUser_name(rs.getString("user_name"));
                        userResponse1.setTel(rs.getString("tel"));
                        userResponse1.setAddress(rs.getString("address"));
                        userResponse1.setBirthday(rs.getDate("birthday"));
                        userResponse1.setPerson(rs.getString("person"));
                        userResponse1.setCareer(rs.getInt("career"));
                        userResponse1.setContract(rs.getDate("contract"));
                        userResponse1.setLoan(rs.getInt("loan"));
                        userResponse1.setGrade(rs.getInt("grade"));
                        userResponse1.setLayer_grade(rs.getInt("layer_grade"));
                        userResponse1.setKeep_grade(rs.getInt("keep_grade"));
                        return userResponse1;
                    }
                });

        return userResponse;
    }
}
