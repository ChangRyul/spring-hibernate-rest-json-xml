package com.igloosec.app.service;

import com.igloosec.app.domain.dao.UserDAO;
import com.igloosec.app.domain.tables.User;
import com.igloosec.app.dto.request.UserUploadRequest;
import com.igloosec.app.dto.response.UserResponse;
import com.igloosec.app.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by enrico on 9/5/14.
 */
@Service
public class UserService implements IUserService{

    @Autowired
    private UserDAO userDAO;

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
