package com.igloosec.app.service;

import com.igloosec.app.domain.dao.UserDAO;
import com.igloosec.app.dto.response.ResultResponse;
import com.igloosec.app.dto.response.UserResponse;
import com.igloosec.app.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by enrico on 9/5/14.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserResponse getUserDetails(String userId) throws UserNotFoundException {
        UserResponse userResponse = userDAO.getUserDetails(userId);

        return userResponse;
    }
}
