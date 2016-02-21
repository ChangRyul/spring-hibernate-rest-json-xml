package com.igloosec.app.service;

import com.igloosec.app.dto.response.ResultResponse;
import com.igloosec.app.exceptions.UserNotFoundException;

/**
 * Created by User on 2016-02-19.
 */
public interface LoginService {
    ResultResponse verify(String userId, String userPw) throws UserNotFoundException;
}
