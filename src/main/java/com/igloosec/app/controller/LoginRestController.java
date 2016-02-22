package com.igloosec.app.controller;

import com.igloosec.app.dto.response.ResultResponse;
import com.igloosec.app.dto.response.UserResponse;
import com.igloosec.app.service.LoginService;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
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

import java.net.URLDecoder;
import java.security.MessageDigest;

/**
 * Created by User on 2016-02-19.
 */
@RestController
@RequestMapping("/login")
public class LoginRestController {
    private static final Logger logger = LogManager.getLogger(LoginRestController.class);

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/{userId}/{userPw}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResultResponse> verifyUser(@PathVariable String userId, @PathVariable String userPw) throws Exception {
        logger.debug("api get for user login" + userId + ", userPw: " + userPw);
        userPw = hashText(new String(Base64.decode(URLDecoder.decode(userPw, "utf-8")))).toUpperCase();
        //logger.error("userPw: " + userPw);
        ResultResponse result = loginService.verify(userId, userPw);

        return new ResponseEntity<ResultResponse>(result, HttpStatus.OK);
    }

    public static String convertByteToHex(byte data[]) {
        StringBuffer hexData = new StringBuffer();
        for (int byteIndex = 0; byteIndex < data.length; byteIndex++)
            hexData.append(Integer.toString((data[byteIndex] & 0xff) + 0x100, 16).substring(1));

        return hexData.toString();
    }

    public static String hashText(String textToHash) throws Exception {
        final MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
        sha512.update(textToHash.getBytes());

        return convertByteToHex(sha512.digest());
    }
}
