package com.drivingtalking.service.impl;

import com.drivingtalking.dao.NoticeDAO;
import com.drivingtalking.dao.NoticeMemberReadDAO;
import com.drivingtalking.model.base.UUIDGenerator;
import com.drivingtalking.model.common.Notice;
import com.drivingtalking.model.common.NoticeMemberRead;
import com.drivingtalking.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NoticeService extends BaseService<Notice, NoticeDAO> implements INoticeService {

    @Autowired
    private NoticeMemberReadDAO noticeMemberReadDAO;


    @Override
    public boolean readNotice(String noticeId, String memberId) {
        if (noticeMemberReadDAO.countNoticeByMemberIdAndNoticeId(memberId,noticeId) > 0) {
            return  true;
        }
        NoticeMemberRead noticeMemberRead = new NoticeMemberRead();
        noticeMemberRead.setId(UUIDGenerator.generateUUID());
        noticeMemberRead.setCreateDate(new Date());
        noticeMemberRead.setMemberId(memberId);
        noticeMemberRead.setStatus(NoticeMemberRead.Status.ALREADY_READ.ordinal());
        noticeMemberRead.setReadDate(new Date());
        noticeMemberReadDAO.save(noticeMemberRead);
        return true;
    }

    @Override
    public int countNoticeByMemberId(String memberId) {

        return noticeMemberReadDAO.countNoticeByMemberId(memberId);
    }

    @Override
    public int countNoticeByMemberIdAndNoticeId(String memberId, String noticeId) {
        return noticeMemberReadDAO.countNoticeByMemberIdAndNoticeId(memberId,noticeId);
    }
}
