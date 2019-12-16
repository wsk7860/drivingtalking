package com.drivingtalking.service;

import com.drivingtalking.model.token.AgoraToken;

import java.util.Date;

public interface IAgoraTokenService extends IBaseService<AgoraToken> {

    AgoraToken getBeforeEffectDate(Date paramDate);

}
