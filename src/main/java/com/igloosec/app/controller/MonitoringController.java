package com.igloosec.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yuganji on 2016. 3. 31..
 */
@Controller
public class MonitoringController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String mainMonitoring() {
        return "index";
    }
}
