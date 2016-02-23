package com.igloosec.app.service;

import com.igloosec.app.domain.dao.UserDAO;
import com.igloosec.app.dto.response.ResultResponse;
import com.igloosec.app.dto.response.UserResponse;
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
    public UserResponse verify(String userId, String userPw) throws UserNotFoundException {
        int result = userDAO.verifyUser(userId, userPw);

        ResultResponse resultResponse;

        if (result == 1) {
            resultResponse = new ResultResponse();
            resultResponse.setCode("U001");
            resultResponse.setMessage("로그인 성공");
        } else {
            resultResponse = new ResultResponse();
            resultResponse.setCode("U002");
            resultResponse.setMessage("로그인 실패");
        }

        if (result == 1) {
            UserResponse userResponse = userDAO.getUserDetails(userId);
            userResponse.setCode(resultResponse.getCode());
            userResponse.setMessage(resultResponse.getMessage());

            return userResponse;
        } else {
            UserResponse userResponse = new UserResponse();
            userResponse.setCode(resultResponse.getCode());
            userResponse.setMessage(resultResponse.getMessage());

            return userResponse;
        }
    }
}
