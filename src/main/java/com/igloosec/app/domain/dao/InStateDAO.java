package com.igloosec.app.domain.dao;

import com.igloosec.app.dto.request.InState;
import org.springframework.stereotype.Repository;

/**
 * Created by CRChoi on 2016. 2. 22..
 */
public interface InStateDAO {
    int createInState(String userId, InState inState);
}
