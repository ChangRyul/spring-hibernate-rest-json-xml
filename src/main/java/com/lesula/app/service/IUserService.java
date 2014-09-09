package com.lesula.app.service;

import com.lesula.app.dto.request.UserUploadRequest;
import com.lesula.app.dto.response.UserResponse;
import com.lesula.app.exceptions.UserNotFoundException;

/**
 * Created by enrico on 9/8/14.
 */
public interface IUserService {

    public UserResponse getUserDetails(int userId) throws UserNotFoundException;

    public UserResponse uploadUser(UserUploadRequest request);

}
