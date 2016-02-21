package com.igloosec.app.controller;

import com.igloosec.app.dto.request.UserUploadRequest;
import com.igloosec.app.dto.response.UserResponse;
import com.igloosec.app.service.IUserService;
import com.igloosec.app.exceptions.DtoValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by enrico on 9/5/14.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LogManager.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    @RequestMapping(value = "/{userId}.htm", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getUserHtml(ModelMap map, @PathVariable int userId) throws Exception {
        LOG.debug("html get for user " + userId);
        //UserResponse userDetails = getUserApi(userId);
        //map.put("user", userDetails);
        return "user";
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> getUserApi(@PathVariable int userId) throws Exception {
        LOG.debug("api get for user" + userId);
        UserResponse userDetails = userService.getUserDetails(userId);

        return new ResponseEntity<UserResponse>(userDetails, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST,
            produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    UserResponse uploadUser(@Valid @RequestBody UserUploadRequest request,
                            BindingResult result) throws DtoValidationException {
        LOG.debug("user upload request");
        if (result.hasErrors()) {
            throw new DtoValidationException(result.getFieldErrors());
        }

        UserResponse userResponse = userService.uploadUser(request);
        return userResponse;
    }

}
