package com.igloosec.app.controller;

import com.igloosec.app.dto.response.*;
import com.igloosec.app.exceptions.DtoValidationException;
import com.igloosec.app.service.StatService;
import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stat")
public class StatisticsController {
    private final static Logger LOG = LogManager.getLogger(StatisticsController.class);

	@Autowired
	StatService statService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
        LOG.info("main controller");
        model.addAttribute("message", "Hello ChangRyul!");
		return "hello";
	}

	@RequestMapping(value = "/temperature/{buildNo}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Temperatures> getTemperatureList(@PathVariable String buildNo) throws DtoValidationException {
		List<TemperatureResponse> temperatureResponseList = new ArrayList<>();

		temperatureResponseList = statService.getTemperatureList(buildNo);
		LOG.error("tempListSize: " + temperatureResponseList.size());
		Temperatures temperatures = new Temperatures();
		temperatures.setTemperature(temperatureResponseList);

		List<AgentDesc> agentDescs = new ArrayList<>();

		if (buildNo.equals("1")) {
			AgentDesc agentDesc = new AgentDesc();
			agentDesc.setName("1동 온습도1");
			agentDesc.setCode("996FBB5D-7436-4A22-AF9D-C56504A2478B");

			agentDescs.add(agentDesc);

			agentDesc = new AgentDesc();
			agentDesc.setName("1동 온습도2");
			agentDesc.setCode("022F035D-DF75-4A9C-924E-DA61D82E3214");

			agentDescs.add(agentDesc);
			temperatures.setAgentDescs(agentDescs);
		} else if (buildNo.equals("2")) {
			AgentDesc agentDesc = new AgentDesc();
			agentDesc.setName("2동 온습도1");
			agentDesc.setCode("10B18372-D3B9-4C2D-8B47-EE0165FE3A52");

			agentDescs.add(agentDesc);

			agentDesc = new AgentDesc();
			agentDesc.setName("2동 온습도2");
			agentDesc.setCode("68DAE242-41FB-4EF3-8528-A42AE45308FA");

			agentDescs.add(agentDesc);
			temperatures.setAgentDescs(agentDescs);
		}

		return new ResponseEntity<Temperatures>(temperatures, HttpStatus.OK);
	}

	@RequestMapping(value = "/humidity/{buildNo}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Humidities> getHumidityList(@PathVariable String buildNo) throws DtoValidationException {
		List<HumidityResponse> humidityResponseList = new ArrayList<>();

		humidityResponseList = statService.getHumidityList(buildNo);
		LOG.error("tempListSize: " + humidityResponseList.size());
		Humidities humidities = new Humidities();
		humidities.setHumidity(humidityResponseList);

		List<AgentDesc> agentDescs = new ArrayList<>();

		if (buildNo.equals("1")) {
			AgentDesc agentDesc = new AgentDesc();
			agentDesc.setName("1동 온습도1");
			agentDesc.setCode("996FBB5D-7436-4A22-AF9D-C56504A2478B");

			agentDescs.add(agentDesc);

			agentDesc = new AgentDesc();
			agentDesc.setName("1동 온습도2");
			agentDesc.setCode("022F035D-DF75-4A9C-924E-DA61D82E3214");

			agentDescs.add(agentDesc);
			humidities.setAgentDescs(agentDescs);
		} else if (buildNo.equals("2")) {
			AgentDesc agentDesc = new AgentDesc();
			agentDesc.setName("2동 온습도1");
			agentDesc.setCode("10B18372-D3B9-4C2D-8B47-EE0165FE3A52");

			agentDescs.add(agentDesc);

			agentDesc = new AgentDesc();
			agentDesc.setName("2동 온습도2");
			agentDesc.setCode("68DAE242-41FB-4EF3-8528-A42AE45308FA");

			agentDescs.add(agentDesc);
			humidities.setAgentDescs(agentDescs);
		}

		return new ResponseEntity<Humidities>(humidities, HttpStatus.OK);
	}
//
//	@RequestMapping(value = "/weight/{buildNo}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
//	@ResponseStatus(HttpStatus.CREATED)
//	public ResponseEntity<Reports> getWeightList(@PathVariable String buildNo) throws DtoValidationException {
//
//
//		return new ResponseEntity<Reports>(null, HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "/flux/{buildNo}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
//	@ResponseStatus(HttpStatus.CREATED)
//	public ResponseEntity<Reports> getFluxList(@PathVariable String buildNo) throws DtoValidationException {
//
//
//		return new ResponseEntity<Reports>(null, HttpStatus.OK);
//	}
}