package com.igloosec.app.service;

import com.igloosec.app.dto.request.UserUploadRequest;
import com.igloosec.app.dto.response.ResultResponse;
import com.igloosec.app.dto.response.UserResponse;
import com.igloosec.app.exceptions.UserNotFoundException;

/**
 * Created by enrico on 9/8/14.
 */
public interface UserService {
    UserResponse getUserDetails(String userId) throws UserNotFoundException;
}
