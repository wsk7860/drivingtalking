package com.drivingtalking.dao;


import com.drivingtalking.model.member.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberDAO extends BaseDAO<Member> {

    public Member getByLoginName(String loginName){
        Map<String,Object> params = new HashMap<>();
        params.put("loginName",loginName);
        return getByParam(Member.class,params);
    }



}
