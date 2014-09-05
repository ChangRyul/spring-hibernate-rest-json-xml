package com.lesula.app.controller;

import com.lesula.app.dto.response.UserResponse;
import com.lesula.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by enrico on 9/5/14.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/{userId}.htm", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getUserHtml(ModelMap map, @PathVariable int userId){
        UserResponse userDetails = userService.getUserDetails(userId);
        map.put("user", userDetails);
        return "user";
    }

    @RequestMapping(value="/{userId}",
            method=RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody UserResponse getUserApi(@PathVariable int userId){
        UserResponse userDetails = userService.getUserDetails(userId);
        return userDetails;
    }
}
