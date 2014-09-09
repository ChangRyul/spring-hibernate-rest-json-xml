package com.lesula.app.controller;

import com.lesula.app.dto.request.UserUploadRequest;
import com.lesula.app.dto.response.UserResponse;
import com.lesula.app.exceptions.DtoValidationException;
import com.lesula.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by enrico on 9/5/14.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping(value="/{userId}.htm", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getUserHtml(ModelMap map, @PathVariable int userId) throws Exception{
        UserResponse userDetails = getUserApi(userId);
        map.put("user", userDetails);
        return "user";
    }

    @RequestMapping(value="/{userId}", method=RequestMethod.GET,
            produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody UserResponse getUserApi(@PathVariable int userId) throws Exception{
        UserResponse userDetails = userService.getUserDetails(userId);
        return userDetails;
    }

    @RequestMapping(value="/create", method = RequestMethod.POST,
            produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody UserResponse uploadUser(@Valid @RequestBody UserUploadRequest request,
                                                 BindingResult result) throws DtoValidationException {

        if(result.hasErrors()){
            throw new DtoValidationException(result.getFieldErrors());
        }

        UserResponse userResponse = userService.uploadUser(request);
        return userResponse;
    }

}
