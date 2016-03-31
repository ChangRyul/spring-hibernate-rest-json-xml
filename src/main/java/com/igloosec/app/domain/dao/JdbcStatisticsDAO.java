package com.igloosec.app.domain.dao;

import com.igloosec.app.dto.response.Flux;
import com.igloosec.app.dto.response.HumidityResponse;
import com.igloosec.app.dto.response.TemperatureResponse;
import com.igloosec.app.dto.response.Weight;
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
import java.util.concurrent.ExecutionException;

/**
 * Created by User on 2016-03-31.
 */
@Repository
public class JdbcStatisticsDAO implements StatisticsDAO {
    private static final Logger logger = LogManager.getLogger(JdbcStatisticsDAO.class);
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
            temperature.setTime(((Date)row.get("time")).getTime());
            temperature.setDw((Integer)row.get("dw"));
            temperature.setType((String)row.get("type"));
            temperature.setVal((Double)row.get("val"));
            temperature.setUnit((String)row.get("unit"));

            temperatureResponseList.add(temperature);
        }

        return temperatureResponseList;
    }

    @Override
    public List<HumidityResponse> getHumidityList(int buildNo) {
        List<HumidityResponse> humidityResponseList = new ArrayList<>();

        String query = "";

        if (buildNo == 1) {
            query = "SELECT time, dw, agentcode, type, val, unit FROM (SELECT * FROM ST_030_20160326) sttable " +
                    "where upper(agentcode) in ('996FBB5D-7436-4A22-AF9D-C56504A2478B', '022F035D-DF75-4A9C-924E-DA61D82E3214') " +
                    "and time BETWEEN to_timestamp('2016-03-26 00:00:00','yyyy-mm-dd HH24:mi:ss') AND to_timestamp('2016-03-26 23:59:00','yyyy-mm-dd HH24:mi:ss') and type = 'EA009'  order by agentcode, time";
        } else if (buildNo == 2) {
            query = "SELECT time, dw, agentcode, type, val, unit FROM (SELECT * FROM ST_030_20160326) sttable " +
                    "where upper(agentcode) in ('10B18372-D3B9-4C2D-8B47-EE0165FE3A52', '68DAE242-41FB-4EF3-8528-A42AE45308FA') " +
                    "and time BETWEEN to_timestamp('2016-03-26 00:00:00','yyyy-mm-dd HH24:mi:ss') AND to_timestamp('2016-03-26 23:59:00','yyyy-mm-dd HH24:mi:ss') and type = 'EA009'  order by agentcode, time";
        }

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, new Object[]{});

        for (Map<String, Object> row : rows) {
            HumidityResponse humidity = new HumidityResponse();

            humidity.setAgentcode((String)row.get("agentcode"));
            humidity.setTime(((Date)row.get("time")).getTime());
            humidity.setDw((Integer)row.get("dw"));
            humidity.setType((String)row.get("type"));
            humidity.setVal((Double)row.get("val"));
            humidity.setUnit((String)row.get("unit"));

            humidityResponseList.add(humidity);
        }

        return humidityResponseList;
    }

    @Override
    public List<Flux> getFluxList(int buildNo) {
        List<Flux> fluxList = new ArrayList<>();

        String query = "SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160226 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160227 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160228 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160229 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160301 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160302 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160303 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160304 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160307 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160308 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160309 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160310 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160311 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160312 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160313 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160314 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160315 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160316 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160317 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160318 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160319 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160320 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160321 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160322 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160323 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160324 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160325 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME  " +
                "UNION ALL SELECT AGENTCODE, AGENTNAME, MAX(cast(ext5 as float)) AS Max, MIN(cast(ext5 as float)) as Min,  to_timestamp(to_char(MAX(servertime),'yyyy-mm-dd'),'yyyy-mm-dd') as Time FROM  EVENT_EA_20160326 A where subeventtype = 'EA007_E03_SE04' GROUP BY AGENTCODE, AGENTNAME";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, new Object[]{});

        for (Map<String, Object> row : rows) {
            if (buildNo == 1) {
                if (((String)row.get("AGENTCODE")).equals("6BBC4A35-A50D-458A-9663-9F324BAEFFEF")) {
                    continue;
                }
            } else if (buildNo == 2) {
                if (((String)row.get("AGENTCODE")).equals("272F057C-D7F6-4960-8FA1-F7A809B323BF") || ((String)row.get("AGENTCODE")).equals("E06FE0F6-A4D5-40AA-AB7F-56FF787A1B95")) {
                    continue;
                }
            }

            Flux flux = new Flux();

            flux.setAgentcode((String)row.get("AGENTCODE"));
            flux.setName((String)row.get("AGENTNAME"));
            flux.setMax((double)row.get("Max"));
            flux.setMin((double)row.get("Min"));
            flux.setTime(((Date)row.get("Time")).getTime());
            flux.setVal(flux.getMax() - flux.getMin());

            fluxList.add(flux);
        }

        return fluxList;
    }

    @Override
    public List<Weight> getWeightList(int buildNo) {
        List<Weight> weightList = new ArrayList<>();

        String query = "select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160226 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160227 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160228 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160229 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160301 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160302 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160303 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160304 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160307 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160308 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160309 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160310 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160311 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160312 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160313 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160314 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160315 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160316 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160317 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160318 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160319 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160320 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160321 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160322 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160323 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160324 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160325 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "UNION ALL select agentcode, agentname, avg(CAST(coalesce(ext3, '0') AS real)), to_char(servertime, 'YYYY-MM-DD') from EVENT_EA_20160326 where subeventtype = 'EA007_E03_SE03' GROUP BY agentcode, agentname, to_char(servertime, 'YYYY-MM-DD') " +
                "";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, new Object[]{});

        for (Map<String, Object> row : rows) {
            if (buildNo == 1) {
                if (((String)row.get("agentcode")).equals("18EE5BD3-3AEB-49D0-AE48-E630C758051D")) {
                    continue;
                }
            } else if (buildNo == 2) {
                if (((String)row.get("agentcode")).equals("41C3E71F-4EF0-48BD-AAAE-D43C49A3C29F") || ((String)row.get("agentcode")).equals("4C8605E9-2FC0-4506-9D58-BC8619F3AAB0")) {
                    continue;
                }
            }

            Weight weight = new Weight();

            weight.setAgentcode((String)row.get("agentcode"));
            weight.setName((String)row.get("agentname"));
            weight.setVal((double)row.get("avg"));

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                weight.setTime(format.parse((String)row.get("to_char")));
            } catch (Exception ex) {

            }

            weightList.add(weight);
        }

        return weightList;
    }
}
