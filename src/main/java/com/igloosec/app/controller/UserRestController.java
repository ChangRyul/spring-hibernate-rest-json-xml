package com.igloosec.app.controller;

import com.igloosec.app.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
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

    @RequestMapping(value = "/{userId}.htm", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getUserHtml(ModelMap map, @PathVariable int userId) throws Exception {
        LOG.debug("html get for user " + userId);
        //UserResponse userDetails = getUserApi(userId);
        //map.put("user", userDetails);
        return "user";
    }

//    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<UserResponse> getUserApi(@PathVariable int userId) throws Exception {
//        LOG.debug("api get for user" + userId);
//        UserResponse userDetails = userService.getUserDetails(userId);
//
//        return new ResponseEntity<UserResponse>(userDetails, HttpStatus.OK);
//    }

//    @RequestMapping(value = "/create", method = RequestMethod.POST,
//            produces = {"application/xml", "application/json"})
//    @ResponseStatus(HttpStatus.CREATED)
//    public
//    @ResponseBody
//    UserResponse uploadUser(@Valid @RequestBody UserUploadRequest request,
//                            BindingResult result) throws DtoValidationException {
//        LOG.debug("user upload request");
//        if (result.hasErrors()) {
//            throw new DtoValidationException(result.getFieldErrors());
//        }
//
//        UserResponse userResponse = userService.uploadUser(request);
//        return userResponse;
//    }

}
