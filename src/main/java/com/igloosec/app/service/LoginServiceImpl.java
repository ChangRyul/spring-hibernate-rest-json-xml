package com.igloosec.app.service;

import com.igloosec.app.domain.dao.UserDAO;
import com.igloosec.app.dto.response.ResultResponse;
import com.igloosec.app.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 2016-02-19.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResultResponse verify(String userId) throws UserNotFoundException {

        return null;
    }
}
