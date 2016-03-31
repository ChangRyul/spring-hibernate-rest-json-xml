package com.igloosec.app.service;

import com.igloosec.app.domain.dao.StatisticsDAO;
import com.igloosec.app.dto.response.Flux;
import com.igloosec.app.dto.response.HumidityResponse;
import com.igloosec.app.dto.response.TemperatureResponse;
import com.igloosec.app.dto.response.Weight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016-03-31.
 */
@Service
public class StatServiceImpl implements StatService {
    private final static Logger LOG = LogManager.getLogger(StatServiceImpl.class);

    @Autowired
    StatisticsDAO statisticsDAO;

    @Override
    public List<TemperatureResponse> getTemperatureList(String buildNo) {
        List<TemperatureResponse> temperatureResponseList = statisticsDAO.getTemperatureList(Integer.parseInt(buildNo));
        return temperatureResponseList;
    }

    @Override
    public List<HumidityResponse> getHumidityList(String buildNo) {
        List<HumidityResponse> humidityResponseList = statisticsDAO.getHumidityList(Integer.parseInt(buildNo));
        return humidityResponseList;
    }

    @Override
    public List<Flux> getFluxList(String buildNo) {
        List<Flux> fluxList = statisticsDAO.getFluxList(Integer.parseInt(buildNo));

        if (buildNo.equals("1")) {
            List<Flux> fluxList1 = new ArrayList<>();
            List<Flux> fluxList2 = new ArrayList<>();

            for (Flux flux : fluxList) {
                if (flux.getAgentcode().equals("272F057C-D7F6-4960-8FA1-F7A809B323BF")) {
                    fluxList1.add(flux);
                } else {
                    fluxList2.add(flux);
                }
            }

//            for (int i = 1; i < fluxList1.size(); i++) {
//                fluxList1.get(i).setVal(fluxList1.get(i - 1).getVal() + fluxList1.get(i).getVal());
//            }
//
//            for (int i = 1; i < fluxList2.size(); i++) {
//                fluxList2.get(i).setVal(fluxList2.get(i - 1).getVal() + fluxList2.get(i).getVal());
//            }

            fluxList.clear();
            fluxList.addAll(fluxList1);
            fluxList.addAll(fluxList2);
        } else if (buildNo.equals("2")) {
            for (int i = 1; i < fluxList.size(); i++) {
                fluxList.get(i).setVal(fluxList.get(i - 1).getVal() + fluxList.get(i).getVal());
            }
        }

        return fluxList;
    }

    @Override
    public List<Weight> getWeightList(String buildNo) {
        List<Weight> weightList = statisticsDAO.getWeightList(Integer.parseInt(buildNo));
        return weightList;
    }
}
