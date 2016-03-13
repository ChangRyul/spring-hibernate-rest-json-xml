package com.igloosec.app.domain.dao;

import com.igloosec.app.dto.request.InState;
import com.igloosec.app.dto.response.InstateResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by CRChoi on 2016. 2. 22..
 */
public interface InStateDAO {
    int createInState(String userId, InState inState);
    List<InstateResponse> getInstateList(String userId);
    int updateInState(String userId, String targetDate, InState request);
}
