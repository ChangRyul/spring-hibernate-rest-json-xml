package com.igloosec.app.domain.dao;

import com.igloosec.app.dto.response.TemperatureResponse;
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
 * Created by User on 2016-03-31.
 */
@Repository
public class JdbcTemperatureDAO implements TemperatureDAO {
    private static final Logger logger = LogManager.getLogger(JdbcTemperatureDAO.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<TemperatureResponse> getTemperatureList(int buildNo) {
        List<TemperatureResponse> temperatureResponseList = new ArrayList<>();

        String query = "";

        if (buildNo == 1) {
            query = "SELECT time, dw, agentcode, type, val, unit FROM (SELECT * FROM ST_030_20160326) sttable " +
                    "where upper(agentcode) in ('996FBB5D-7436-4A22-AF9D-C56504A2478B', '022F035D-DF75-4A9C-924E-DA61D82E3214') " +
                    "and time BETWEEN to_timestamp('2016-03-26 00:00:00','yyyy-mm-dd HH24:mi:ss') AND to_timestamp('2016-03-26 23:59:00','yyyy-mm-dd HH24:mi:ss') and type = 'EA010'  order by agentcode, time";
        } else if (buildNo == 2) {
            query = "SELECT time, dw, agentcode, type, val, unit FROM (SELECT * FROM ST_030_20160326) sttable " +
                    "where upper(agentcode) in ('10B18372-D3B9-4C2D-8B47-EE0165FE3A52', '68DAE242-41FB-4EF3-8528-A42AE45308FA') " +
                    "and time BETWEEN to_timestamp('2016-03-26 00:00:00','yyyy-mm-dd HH24:mi:ss') AND to_timestamp('2016-03-26 23:59:00','yyyy-mm-dd HH24:mi:ss') and type = 'EA010'  order by agentcode, time";
        }

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, new Object[]{});

        for (Map<String, Object> row : rows) {
            TemperatureResponse temperature = new TemperatureResponse();

            temperature.setAgentcode((String)row.get("agentcode"));
            temperature.setTime((Date)row.get("time"));
            temperature.setDw((Integer)row.get("dw"));
            temperature.setType((String)row.get("type"));
            temperature.setVal((Double)row.get("val"));
            temperature.setUnit((String)row.get("unit"));

            temperatureResponseList.add(temperature);
        }

        return temperatureResponseList;
    }
}
