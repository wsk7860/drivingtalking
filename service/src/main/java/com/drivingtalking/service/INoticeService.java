package com.drivingtalking.service;

import com.drivingtalking.model.common.Notice;

public interface INoticeService extends IBaseService<Notice> {

     boolean readNotice(String noticeId,String memberId);

     int  countNoticeByMemberId(String memberId);

     int countNoticeByMemberIdAndNoticeId(String memberId,String noticeId);
}
