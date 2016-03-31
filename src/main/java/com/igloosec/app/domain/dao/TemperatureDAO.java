package com.igloosec.app.domain.dao;

import com.igloosec.app.dto.response.TemperatureResponse;

import java.util.List;

/**
 * Created by User on 2016-03-31.
 */
public interface TemperatureDAO {
    List<TemperatureResponse> getTemperatureList(int buildNo);
}
