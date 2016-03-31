package com.igloosec.app.service;

import com.igloosec.app.dto.response.Flux;
import com.igloosec.app.dto.response.HumidityResponse;
import com.igloosec.app.dto.response.TemperatureResponse;
import com.igloosec.app.dto.response.Weight;

import java.util.List;

/**
 * Created by User on 2016-03-31.
 */
public interface StatService {
    List<TemperatureResponse> getTemperatureList(String buildNo);
    List<HumidityResponse> getHumidityList(String buildNo);
    List<Flux> getFluxList(String buildNo);
    List<Weight> getWeightList(String buildNo);
}
