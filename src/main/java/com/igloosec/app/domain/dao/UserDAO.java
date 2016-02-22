package com.igloosec.app.domain.dao;

import com.igloosec.app.dto.response.ResultResponse;
import com.igloosec.app.dto.response.UserResponse;

/**
 * Created by enrico on 9/8/14.
 */
public interface UserDAO {
    int verifyUser(String userId, String password);
    UserResponse getUserDetails(String userId);
}
