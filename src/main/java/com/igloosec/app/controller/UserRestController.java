package com.igloosec.app.controller;

import com.igloosec.app.dto.response.UserResponse;
import com.igloosec.app.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by enrico on 9/5/14.
 */
@RestController
@RequestMapping("/user")
public class UserRestController {

    private static final Logger LOG = LogManager.getLogger(UserRestController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> getUserApi(@PathVariable String userId) throws Exception {
        LOG.debug("api get for user" + userId);
        UserResponse userDetails;

        try {
            userDetails = userService.getUserDetails(userId);
        } catch (Exception ex) {
            userDetails = new UserResponse();
            userDetails.setUser_id("UserNotFound");
        }

        return new ResponseEntity<UserResponse>(userDetails, HttpStatus.OK);
    }
}
