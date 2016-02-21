package com.igloosec.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class StatisticsController {

    private final static Logger LOG = LogManager.getLogger(StatisticsController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
        LOG.info("main controller");
        model.addAttribute("message", "Hello ChangRyul!");
		return "hello";
	}

}