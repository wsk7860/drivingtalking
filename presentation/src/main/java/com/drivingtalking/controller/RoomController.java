package com.drivingtalking.controller;



import com.drivingtalking.model.room.Room;
import com.drivingtalking.service.IRoomService;
import com.drivingtalking.util.ContextManager;
import com.drivingtalking.util.ResponseModel;
import com.drivingtalking.vo.room.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RequestMapping("/room")
@RestController
public class RoomController extends BaseController {

    @Autowired
    private IRoomService roomService;


    /**
     *  创建房间
     */
    @PostMapping("/saveOrUpdateRoom")
    public ResponseModel<RoomVO> saveOrUpdateRoom(@RequestBody @Validated RoomVO room)
    {
        Room perRoom =  map(room, Room.class);
        perRoom.setCreateMemberId(ContextManager.getSessionMember().getId());
        perRoom.setCreateDate(new Date());
        roomService.save(perRoom);
        return  new ResponseModel<>(map(perRoom,RoomVO.class));
    }

    /**
     * 加入房间
     */
    @GetMapping("/joinRoom/{id}")
    public ResponseModel joinRoom(@PathVariable("id") String id){
        return  null;
    }



}
