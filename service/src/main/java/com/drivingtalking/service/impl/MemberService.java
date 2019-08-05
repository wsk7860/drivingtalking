package com.drivingtalking.service.impl;

import com.drivingtalking.dao.MemberDAO;
import com.drivingtalking.model.member.Member;
import com.drivingtalking.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberService extends BaseService<Member, MemberDAO> implements IMemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Override
    public Member getByLoginName(String name) {
        return memberDAO.getByLoginName(name);
    }
}
