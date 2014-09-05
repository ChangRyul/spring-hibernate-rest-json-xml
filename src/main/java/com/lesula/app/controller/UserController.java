package com.lesula.app.controller;

import com.lesula.app.domain.tables.User;
import com.lesula.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by enrico on 9/5/14.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public String getUser(ModelMap map, @PathVariable int userId){
        User user = userService.getUser(userId);
        map.put("name", user != null ? user.getFirstName() : ":(");
        return "user";
    }
}
