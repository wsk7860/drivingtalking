package com.drivingtalking.service;

import com.drivingtalking.model.member.Member;

public interface IMemberService extends IBaseService<Member> {

    Member getByLoginName(String name);

    void testException();

    void saveMember(Member member);


}
