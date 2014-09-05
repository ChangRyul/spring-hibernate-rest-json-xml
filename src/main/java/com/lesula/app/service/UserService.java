package com.lesula.app.service;

import com.lesula.app.domain.dao.UserDAO;
import com.lesula.app.domain.tables.User;
import com.lesula.app.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by enrico on 9/5/14.
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional(readOnly = true)
    public UserResponse getUserDetails(int userId){
        User dbUser = userDAO.findById(userId);

        if(dbUser != null){
            UserResponse userResponse = new UserResponse();
            userResponse.setFirstName(dbUser.getFirstName());
            userResponse.setLastName(dbUser.getLastName());
            userResponse.setId(dbUser.getId());
            return userResponse;
        }

        return null;
    }
}
