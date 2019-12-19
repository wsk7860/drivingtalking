package com.drivingtalking.controller;



import com.drivingtalking.model.room.Room;
import com.drivingtalking.service.IRoomService;
import com.drivingtalking.util.ContextManager;
import com.drivingtalking.util.RedisUtils;
import com.drivingtalking.util.ResponseModel;
import com.drivingtalking.vo.room.RoomVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RequestMapping("/room")
@RestController
@Api(tags = "聊天室房间相关接口")
public class RoomController extends BaseController {

    @Autowired
    private IRoomService roomService;

    @Autowired
    private RedisUtils redisUtils;

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
    @ApiOperation(value = "加入房间",httpMethod = "GET")
    public ResponseModel<Boolean> joinRoom(@PathVariable("id") String id){
        return  new ResponseModel<>(roomService.joinRoom(id));
    }

    @GetMapping("/leaveRoom/{id}")
    @ApiOperation(value = "离开房间",httpMethod = "GET")
    public ResponseModel<Boolean> leaveRoom(@PathVariable("id") String id) {
        return new ResponseModel<>(roomService.leaveRoom(id));
    }

    /**
     *  随机获取房间
     * @param longitude 经度
     * @param latitude  纬度
     */
    @GetMapping("/getRandomRoom")
    @ApiOperation(value = "随机获取房间ID",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "longitude",value = "经度",defaultValue = "123.12222"),
            @ApiImplicitParam(name = "latitude",value = "纬度",defaultValue = "123.12222")
    })
    public ResponseModel<String> getRandomRoom(String longitude,String latitude) {
       return new ResponseModel<>(roomService.getRandomRoom(longitude,latitude));
    }





}
