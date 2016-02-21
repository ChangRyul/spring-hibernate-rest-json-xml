package com.igloosec.app.controller;

import com.igloosec.app.dto.response.ResultResponse;
import com.igloosec.app.dto.response.UserResponse;
import com.igloosec.app.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by User on 2016-02-19.
 */
@RestController
@RequestMapping("/login")
public class LoginRestController {
    private static final Logger LOG = LogManager.getLogger(LoginRestController.class);

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResultResponse> verifyUser(@PathVariable String userId) throws Exception {
        LOG.debug("api get for user login" + userId);
        ResultResponse result = loginService.verify(userId);

        return new ResponseEntity<ResultResponse>(result, HttpStatus.OK);
    }
}
