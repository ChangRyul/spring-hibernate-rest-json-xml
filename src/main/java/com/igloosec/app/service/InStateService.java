package com.igloosec.app.service;

import com.igloosec.app.dto.request.InState;
import com.igloosec.app.dto.response.ResultResponse;

/**
 * Created by CRChoi on 2016. 2. 22..
 */
public interface InStateService {
    ResultResponse createInState(String userId, InState request);
}
