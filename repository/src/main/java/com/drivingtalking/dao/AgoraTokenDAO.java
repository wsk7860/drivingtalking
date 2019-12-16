package com.drivingtalking.dao;

import com.drivingtalking.model.token.AgoraToken;
import com.drivingtalking.util.PagerManager;
import com.drivingtalking.util.PagerSupporter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Component
public class AgoraTokenDAO extends BaseDAO<AgoraToken> {

    public AgoraToken getBeforeEffectDate(Date paramDate) {
        List<Criteria> criteriaList = new ArrayList<>();
        criteriaList.add(this.ge("effectDate",paramDate));
        List<AgoraToken> results = PagerManager.paging(new PagerSupporter(0,1,null),() -> this.findByCriteriaList(AgoraToken.class,criteriaList));
        if (!CollectionUtils.isEmpty(results)) {
            return results.get(0);
        }
        return  null;
    }


}
