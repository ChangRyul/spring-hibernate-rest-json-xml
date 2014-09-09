package com.lesula.app.service;

import com.lesula.app.domain.dao.IUserDAO;
import com.lesula.app.domain.tables.User;
import com.lesula.app.dto.request.UserUploadRequest;
import com.lesula.app.dto.response.UserResponse;
import com.lesula.app.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by enrico on 9/5/14.
 */
@Service
public class UserService implements IUserService{

    @Autowired
    private IUserDAO userDAO;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public UserResponse getUserDetails(int userId) throws UserNotFoundException {
        User dbUser = userDAO.findById(userId);

        if(dbUser == null){
            throw new UserNotFoundException(userId);
        }

        UserResponse userResponse = new UserResponse();
        userResponse.setFirstName(dbUser.getFirstName());
        userResponse.setLastName(dbUser.getLastName());
        userResponse.setBirthday(dbUser.getBirthday());
        userResponse.setId(dbUser.getId());
        return userResponse;
    }

    @Transactional
    public UserResponse uploadUser(UserUploadRequest request) {
        User dbUser = new User(request.getFirstName(), request.getLastName(), request.getBirthday());
        userDAO.save(dbUser);
        userDAO.flush();

        UserResponse userResponse = new UserResponse();
        userResponse.setFirstName(dbUser.getFirstName());
        userResponse.setLastName(dbUser.getLastName());
        userResponse.setBirthday(dbUser.getBirthday());
        userResponse.setId(dbUser.getId());
        return userResponse;
    }
}
