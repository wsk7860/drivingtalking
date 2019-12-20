package com.drivingtalking.service.impl;

import com.drivingtalking.dao.MemberDAO;
import com.drivingtalking.exception.ServiceException;
import com.drivingtalking.model.member.Member;
import com.drivingtalking.service.IMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class MemberService extends BaseService<Member, MemberDAO> implements IMemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Override
    public Member getByLoginName(String name) {
        return memberDAO.getByLoginName(name);
    }



    @Override
    public void testException() {
        throw new ServiceException("test1");
    }

    @Override
    public void saveMember(Member member) {
           if (StringUtils.isEmpty(member.getId())) {
               throw new ServiceException("成员ID不能为空");
           }
           Map<String,Object> params = new HashMap<>();
           params.put("carNumber",member.getCarNumber());
           params.put("carInfo",member.getCarInfo());
           params.put("labelIds",member.getLabelIds());
    }
}
