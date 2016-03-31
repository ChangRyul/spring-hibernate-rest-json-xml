package com.igloosec.app.service;

import com.igloosec.app.dto.response.HumidityResponse;
import com.igloosec.app.dto.response.TemperatureResponse;

import java.util.List;

/**
 * Created by User on 2016-03-31.
 */
public interface StatService {
    List<TemperatureResponse> getTemperatureList(String buildNo);
    List<HumidityResponse> getHumidityList(String buildNo);
}
