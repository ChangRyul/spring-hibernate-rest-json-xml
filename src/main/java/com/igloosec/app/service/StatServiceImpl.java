package com.igloosec.app.service;

import com.igloosec.app.domain.dao.StatisticsDAO;
import com.igloosec.app.dto.response.HumidityResponse;
import com.igloosec.app.dto.response.TemperatureResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by User on 2016-03-31.
 */
@Service
public class StatServiceImpl implements StatService {
    @Autowired
    StatisticsDAO temperatureDAO;

    @Override
    public List<TemperatureResponse> getTemperatureList(String buildNo) {
        List<TemperatureResponse> temperatureResponseList = temperatureDAO.getTemperatureList(Integer.parseInt(buildNo));
        return temperatureResponseList;
    }

    @Override
    public List<HumidityResponse> getHumidityList(String buildNo) {
        List<HumidityResponse> humidityResponseList = temperatureDAO.getHumidityList(Integer.parseInt(buildNo));
        return humidityResponseList;
    }
}
