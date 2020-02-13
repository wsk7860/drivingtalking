package com.drivingtalking.controller;

import com.drivingtalking.model.base.UUIDGenerator;
import com.drivingtalking.model.common.Notice;
import com.drivingtalking.service.INoticeService;
import com.drivingtalking.util.Constant;
import com.drivingtalking.util.ContextManager;
import com.drivingtalking.util.ResponseModel;
import com.drivingtalking.vo.notice.NoticeMainVO;
import com.drivingtalking.vo.notice.NoticeSimpleVO;
import com.drivingtalking.vo.notice.NoticeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/notice")
@Api(tags = "消息相关接口")
public class NoticeController  extends BaseController{


    @Autowired
    private INoticeService noticeService;

    @PostMapping("/saveNotice")
    @ApiOperation("新增提醒消息")
    public ResponseModel<Boolean> saveNotice(NoticeVO noticeVO) {
        Notice notice = map(noticeVO,Notice.class);
        notice.setId(UUIDGenerator.generateUUID());
        notice.setAudience(Notice.Audience.ALL.ordinal());
        notice.setStatus(Notice.Status.DISPLAY.ordinal());
        notice.setType(Notice.Type.TEXT.ordinal());
        notice.setCreateDate(new Date());
        notice.setPushDate(new Date());
        notice.setCreateMemberId(Constant.CREATE_USER_ID);
        noticeService.save(notice);
        return new ResponseModel<>(true);
    }

    @GetMapping("/findNoticeByUser")
    @ApiOperation("根据用户获取通知消息")
    public ResponseModel<NoticeMainVO> findNoticeByUser() {
        String memberId = ContextManager.getSessionMember().getId();
        List<Notice> notices = noticeService.findAll(Notice.class);
        NoticeMainVO noticeMainVO = new NoticeMainVO();
        if (!CollectionUtils.isEmpty(notices)) {
            List<NoticeSimpleVO> noticeSimpleVOS = new ArrayList<>();
            int noReadCount = 0;
            NoticeSimpleVO simpleVO;
            for (Notice notice : notices) {
                simpleVO = map(notice,NoticeSimpleVO.class);
                if (noticeService.countNoticeByMemberIdAndNoticeId(memberId,notice.getId()) > 0) {
                    simpleVO.setIsRead(1);
                } else {
                    simpleVO.setIsRead(0);
                    noReadCount ++;
                }
                noticeSimpleVOS.add(simpleVO);
            }
            noticeMainVO.setUnReadCounts(noReadCount);
            noticeMainVO.setNotices(noticeSimpleVOS);
        }
        return  new ResponseModel<>(noticeMainVO);
    }

    @ApiOperation("消息提醒列表")
    @GetMapping("/findNotice")
    public ResponseModel<List<NoticeVO>> findNotice() {
        List<NoticeVO> results = new ArrayList<>();
        List<Notice> notices = noticeService.findAll(Notice.class);
        if (!CollectionUtils.isEmpty(notices)) {
            results = notices.stream().map(notice -> map(notice,NoticeVO.class)).collect(Collectors.toList());
        }
        return new ResponseModel<>(results);
    }

    @GetMapping("/readNotice/{id}")
    @ApiOperation("已读消息")
    public ResponseModel<Boolean> readNotice(@PathVariable("id") String id) {
        String memberId = ContextManager.getSessionMember().getId();
        noticeService.readNotice(id,memberId);
        return new ResponseModel<>(true);
    }

}
