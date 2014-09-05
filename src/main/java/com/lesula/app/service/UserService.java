package com.lesula.app.service;

import com.lesula.app.domain.dao.UserDAO;
import com.lesula.app.domain.tables.User;
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
    public User getUser(int userId){
        return userDAO.findById(userId);
    }
}
