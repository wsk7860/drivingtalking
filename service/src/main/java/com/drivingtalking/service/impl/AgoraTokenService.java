package com.drivingtalking.service.impl;

import com.drivingtalking.dao.AgoraTokenDAO;
import com.drivingtalking.model.token.AgoraToken;
import com.drivingtalking.service.IAgoraTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AgoraTokenService extends BaseService<AgoraToken, AgoraTokenDAO> implements IAgoraTokenService {
    @Override
    public AgoraToken getBeforeEffectDate(Date paramDate) {
        return dao.getBeforeEffectDate(paramDate);
    }
}
