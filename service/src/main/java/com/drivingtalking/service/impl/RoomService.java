package com.drivingtalking.service.impl;

import com.drivingtalking.dao.RoomDAO;
import com.drivingtalking.exception.ServiceException;
import com.drivingtalking.model.cache.RoomOnline;
import com.drivingtalking.model.room.Room;
import com.drivingtalking.service.IRoomService;
import com.drivingtalking.util.ContextManager;
import com.drivingtalking.util.PagerManager;
import com.drivingtalking.util.PagerSupporter;
import com.drivingtalking.util.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RoomService extends BaseService<Room, RoomDAO> implements IRoomService {

    @Autowired
    private RedisUtils redisUtils;

    public static String DEFAULT_ROOM_KEY = "00";
    private static Logger logger = LoggerFactory.getLogger(RoomService.class);



    @Override
    public String getRandomRoom(String longitude, String latitude) {
        if (!redisUtils.hasRedisKey(DEFAULT_ROOM_KEY)) {
           List<Room> rooms =  PagerManager.paging(new PagerSupporter(0,10,null),()-> findAll(Room.class));
           for (Room room: rooms) {
               redisUtils.setKeyValue(DEFAULT_ROOM_KEY,room.getId(),transformByRoom(room));
           }
           return  rooms.get(0).getId();
        } else {
               return getRandomRoomForCache(null);
        }
    }

    @Override
    public Boolean joinRoom(String roomId) {
        RoomOnline roomOnline = checkRoomValidate(roomId);
        roomOnline.getMemberIds().add(ContextManager.getSessionMember().getId());
        if (roomOnline.getMemberIds().size() == roomOnline.getLimit()) {
            roomOnline.setFull(true);
            logger.info("房间ID ：" + roomId +"已满员");
        }
        redisUtils.setKeyValue(DEFAULT_ROOM_KEY,roomId,roomOnline);
        logger.info(ContextManager.getSessionMember().getId() +"加入房间ID" + roomId);
        return true;
    }

    @Override
    public Boolean leaveRoom(String roomId) {
        RoomOnline roomOnline = checkRoomValidate(roomId);
        roomOnline.getMemberIds().remove(roomId);
        if(roomOnline.getMemberIds().size() < roomOnline.getLimit()) {
            roomOnline.setFull(false);
        }
        redisUtils.setKeyValue(DEFAULT_ROOM_KEY,roomId,roomOnline);
        return true;
    }

    @Override
    public Boolean handleRoomMember(String roomId,String memberId) {
        RoomOnline roomOnline = redisUtils.getKeyValue(DEFAULT_ROOM_KEY,roomId,RoomOnline.class);
        if (roomOnline != null) {
            roomOnline.getMemberIds().remove(memberId);
        }
        return true;
    }

    @Override
    public RoomOnline getRoomById(String roomId) {
        RoomOnline roomOnline = redisUtils.getKeyValue(DEFAULT_ROOM_KEY,roomId,RoomOnline.class);
        return roomOnline;
    }

    private RoomOnline checkRoomValidate(String roomId) {
        if (StringUtils.isEmpty(roomId)) {
            throw  new ServiceException("房间ID不能为空");
        }
        RoomOnline roomOnline = redisUtils.getKeyValue(DEFAULT_ROOM_KEY,roomId,RoomOnline.class);
        if (roomOnline == null) {
            throw  new ServiceException("加入房间不存在");
        }
        return roomOnline;
    }

    private String getRandomRoomForCache(String roomId) {
    List<RoomOnline> roomOnlineList = redisUtils.keyValueList(DEFAULT_ROOM_KEY,RoomOnline.class);
    List<String> validRoomIds = new ArrayList<>();
    int index = 0;
        for (RoomOnline roomOnline : roomOnlineList) {
            //满员的不进了
            if (roomOnline.isFull()) {
                continue;
            }
            //当前的不进
            if (StringUtils.isNotEmpty(roomId)) {
                if (roomOnline.getId().equals(roomId)) {
                    continue;
                }
            }
            validRoomIds.add(roomOnline.getId());
            index++;
            if(index == 5) {
                break;
            }
        }
        if (index == 0) {
            throw  new ServiceException("所有房间已满不能切换房间");
        }
        return  validRoomIds.get(new Random().nextInt((index-1)));
    }

    private RoomOnline transformByRoom(Room room) {
        RoomOnline roomOnline = new RoomOnline();
        roomOnline.setId(room.getId());
        roomOnline.setLimit(room.getLimit());
        roomOnline.setMemberIds(new ArrayList<>());
        return roomOnline;
    }


}
