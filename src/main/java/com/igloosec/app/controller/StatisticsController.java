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

		if (buildNo.equals("1")) {
			temperatures.setAgentDescs(getAgentDesc(buildNo, "TempHumi"));
		} else if (buildNo.equals("2")) {
			temperatures.setAgentDescs(getAgentDesc(buildNo, "TempHumi"));
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

		if (buildNo.equals("1")) {
			humidities.setAgentDescs(getAgentDesc(buildNo, "TempHumi"));
		} else if (buildNo.equals("2")) {
			humidities.setAgentDescs(getAgentDesc(buildNo, "TempHumi"));
		}

		return new ResponseEntity<Humidities>(humidities, HttpStatus.OK);
	}

	@RequestMapping(value = "/weight/{buildNo}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Weights> getWeightList(@PathVariable String buildNo) throws DtoValidationException {
		List<Weight> weightList = new ArrayList<>();

		weightList = statService.getWeightList(buildNo);
		LOG.error("tempListSize: " + weightList.size());
		Weights weights = new Weights();
		weights.setWeight(weightList);

		if (buildNo.equals("1")) {
			weights.setAgentDescs(getAgentDesc(buildNo, "weight"));
		} else if (buildNo.equals("2")) {
			weights.setAgentDescs(getAgentDesc(buildNo, "weight"));
		}

		return new ResponseEntity<Weights>(weights, HttpStatus.OK);
	}

	@RequestMapping(value = "/flux/{buildNo}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Fluxes> getFluxList(@PathVariable String buildNo) throws DtoValidationException {
		List<Flux> fluxList = new ArrayList<>();

		fluxList = statService.getFluxList(buildNo);
		LOG.error("tempListSize: " + fluxList.size());
		Fluxes fluxes = new Fluxes();
		fluxes.setFlux(fluxList);

		if (buildNo.equals("1")) {
			fluxes.setAgentDescs(getAgentDesc(buildNo, "flux"));
		} else if (buildNo.equals("2")) {
			fluxes.setAgentDescs(getAgentDesc(buildNo, "flux"));
		}

		return new ResponseEntity<Fluxes>(fluxes, HttpStatus.OK);
	}

	@RequestMapping(value = "/standard", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ChickManual> getStandardList() throws DtoValidationException {
		List<Table1> table1List = new ArrayList<>();

		Table1 table1 = new Table1();
		table1.setDate(1);
		table1.setWater(23);
		table1.setWeight(40);
		table1.setTemp(35);
		table1.setHum(80);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(2);
		table1.setWater(32);
		table1.setWeight(40);
		table1.setTemp(35);
		table1.setHum(80);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(3);
		table1.setWater(38);
		table1.setWeight(40);
		table1.setTemp(34);
		table1.setHum(80);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(4);
		table1.setWater(42);
		table1.setWeight(40);
		table1.setTemp(34);
		table1.setHum(75);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(5);
		table1.setWater(47);
		table1.setWeight(40);
		table1.setTemp(33);
		table1.setHum(75);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(6);
		table1.setWater(53);
		table1.setWeight(40);
		table1.setTemp(33);
		table1.setHum(75);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(7);
		table1.setWater(60);
		table1.setWeight(195);
		table1.setTemp(32);
		table1.setHum(75);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(8);
		table1.setWater(65);
		table1.setWeight(195);
		table1.setTemp(32);
		table1.setHum(65);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(9);
		table1.setWater(74);
		table1.setWeight(195);
		table1.setTemp(31);
		table1.setHum(65);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(10);
		table1.setWater(80);
		table1.setWeight(195);
		table1.setTemp(31);
		table1.setHum(65);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(11);
		table1.setWater(86);
		table1.setWeight(195);
		table1.setTemp(30);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(12);
		table1.setWater(95);
		table1.setWeight(195);
		table1.setTemp(30);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(13);
		table1.setWater(102);
		table1.setWeight(195);
		table1.setTemp(29);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(14);
		table1.setWater(110);
		table1.setWeight(195);
		table1.setTemp(29);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(15);
		table1.setWater(116);
		table1.setWeight(508);
		table1.setTemp(28);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(16);
		table1.setWater(123);
		table1.setWeight(558);
		table1.setTemp(28);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(17);
		table1.setWater(131);
		table1.setWeight(612);
		table1.setTemp(27);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(18);
		table1.setWater(137);
		table1.setWeight(667);
		table1.setTemp(27);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(19);
		table1.setWater(145);
		table1.setWeight(721);
		table1.setTemp(26);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(20);
		table1.setWater(152);
		table1.setWeight(780);
		table1.setTemp(26);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(21);
		table1.setWater(159);
		table1.setWeight(844);
		table1.setTemp(25);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(22);
		table1.setWater(167);
		table1.setWeight(903);
		table1.setTemp(25);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(23);
		table1.setWater(173);
		table1.setWeight(971);
		table1.setTemp(24);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(24);
		table1.setWater(180);
		table1.setWeight(1034);
		table1.setTemp(24);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(25);
		table1.setWater(188);
		table1.setWeight(1102);
		table1.setTemp(24);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(26);
		table1.setWater(194);
		table1.setWeight(1170);
		table1.setTemp(24);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(27);
		table1.setWater(201);
		table1.setWeight(1238);
		table1.setTemp(24);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(28);
		table1.setWater(208);
		table1.setWeight(1311);
		table1.setTemp(24);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(29);
		table1.setWater(214);
		table1.setWeight(1379);
		table1.setTemp(24);
		table1.setHum(60);

		table1List.add(table1);

		table1 = new Table1();
		table1.setDate(30);
		table1.setWater(222);
		table1.setWeight(1452);
		table1.setTemp(24);
		table1.setHum(60);

		table1List.add(table1);

		ChickManual chickManual = new ChickManual();
		chickManual.setTable1(table1List);

		return new ResponseEntity<ChickManual>(chickManual, HttpStatus.OK);
	}

	private List<AgentDesc> getAgentDesc(String buildNo, String type) {
		List<AgentDesc> agentDescs = new ArrayList<>();

		if (type.equals("TempHumi")) {
			if (buildNo.equals("1")) {
				AgentDesc agentDesc = new AgentDesc();
				agentDesc.setName("1동 온습도1");
				agentDesc.setCode("996FBB5D-7436-4A22-AF9D-C56504A2478B");

				agentDescs.add(agentDesc);

				agentDesc = new AgentDesc();
				agentDesc.setName("1동 온습도2");
				agentDesc.setCode("022F035D-DF75-4A9C-924E-DA61D82E3214");

				agentDescs.add(agentDesc);
			} else if (buildNo.equals("2")) {
				AgentDesc agentDesc = new AgentDesc();
				agentDesc.setName("2동 온습도1");
				agentDesc.setCode("10B18372-D3B9-4C2D-8B47-EE0165FE3A52");

				agentDescs.add(agentDesc);

				agentDesc = new AgentDesc();
				agentDesc.setName("2동 온습도2");
				agentDesc.setCode("68DAE242-41FB-4EF3-8528-A42AE45308FA");

				agentDescs.add(agentDesc);
			}
		} else if (type.equals("flux")) {
			if (buildNo.equals("1")) {
				AgentDesc agentDesc = new AgentDesc();
				agentDesc.setName("1동 유량1");
				agentDesc.setCode("272F057C-D7F6-4960-8FA1-F7A809B323BF");

				agentDescs.add(agentDesc);

				agentDesc = new AgentDesc();
				agentDesc.setName("1동 유량2");
				agentDesc.setCode("E06FE0F6-A4D5-40AA-AB7F-56FF787A1B95");

				agentDescs.add(agentDesc);
			} else if (buildNo.equals("2")) {
				AgentDesc agentDesc = new AgentDesc();
				agentDesc.setName("2동 유량1");
				agentDesc.setCode("6BBC4A35-A50D-458A-9663-9F324BAEFFEF");

				agentDescs.add(agentDesc);
			}
		} else if (type.equals("weight")) {
			if (buildNo.equals("1")) {
				AgentDesc agentDesc = new AgentDesc();
				agentDesc.setName("1동 중량1");
				agentDesc.setCode("41C3E71F-4EF0-48BD-AAAE-D43C49A3C29F");

				agentDescs.add(agentDesc);

				agentDesc = new AgentDesc();
				agentDesc.setName("1동 중량2");
				agentDesc.setCode("4C8605E9-2FC0-4506-9D58-BC8619F3AAB0");

				agentDescs.add(agentDesc);
			} else if (buildNo.equals("2")) {
				AgentDesc agentDesc = new AgentDesc();
				agentDesc.setName("2동 중량1");
				agentDesc.setCode("18EE5BD3-3AEB-49D0-AE48-E630C758051D");

				agentDescs.add(agentDesc);
			}
		}

		return agentDescs;
	}
}