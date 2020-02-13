package com.drivingtalking.dao;

import com.drivingtalking.model.common.NoticeMemberRead;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class NoticeMemberReadDAO extends BaseDAO<NoticeMemberRead> {

    public int  countNoticeByMemberIdAndNoticeId(String memberId,String noticeId) {
        Map<String,Object> params = new HashMap<>();
        params.put("memberId",memberId);
        params.put("noticeId",noticeId);
        return this.count(NoticeMemberRead.class,params);
    }

    public int countNoticeByMemberId(String memberId) {
        Map<String,Object> params = new HashMap<>();
        params.put("memberId",memberId);
        return this.count(NoticeMemberRead.class,params);
    }
}
