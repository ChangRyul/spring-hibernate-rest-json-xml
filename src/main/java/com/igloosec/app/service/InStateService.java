package com.igloosec.app.service;

import com.igloosec.app.dto.request.InState;
import com.igloosec.app.dto.response.InstateResponse;
import com.igloosec.app.dto.response.ResultResponse;

import java.util.List;

/**
 * Created by CRChoi on 2016. 2. 22..
 */
public interface InStateService {
    ResultResponse createInState(String userId, InState request);
    List<InstateResponse> getInstateList(String userId);
    ResultResponse updateInState(String userId, String targetDate, InState request);
}
