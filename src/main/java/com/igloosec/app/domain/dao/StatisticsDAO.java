package com.igloosec.app.domain.dao;

import com.igloosec.app.dto.response.Flux;
import com.igloosec.app.dto.response.HumidityResponse;
import com.igloosec.app.dto.response.TemperatureResponse;

import java.util.List;

/**
 * Created by User on 2016-03-31.
 */
public interface StatisticsDAO {
    List<TemperatureResponse> getTemperatureList(int buildNo);
    List<HumidityResponse> getHumidityList(int i);
    List<Flux> getFluxList(int i);
}
