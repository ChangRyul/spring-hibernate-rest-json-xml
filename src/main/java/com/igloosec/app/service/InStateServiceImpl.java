package com.igloosec.app.service;

import com.igloosec.app.domain.dao.InStateDAO;
import com.igloosec.app.dto.request.InState;
import com.igloosec.app.dto.response.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CRChoi on 2016. 2. 22..
 */
@Service
public class InStateServiceImpl implements InStateService {
    @Autowired
    InStateDAO inStateDAO;

    @Override
    public ResultResponse createInState(String userId, InState request) {
        int result = inStateDAO.createInState(userId, request);

        ResultResponse resultResponse;

        if (result == 1) {
            resultResponse = new ResultResponse();
            resultResponse.setCode("I001");
            resultResponse.setMessage("입추정보 등록 성공");
        } else if (result == 2) {
            resultResponse = new ResultResponse();
            resultResponse.setCode("I002");
            resultResponse.setMessage("이미 입추정보가 등록 되어 있음");
        } else {
            resultResponse = new ResultResponse();
            resultResponse.setCode("I003");
            resultResponse.setMessage("입추정보 등록 실패");
        }

        return resultResponse;
    }
}
