package com.drivingtalking.service.impl;

import com.drivingtalking.dao.RoomDAO;
import com.drivingtalking.exception.ServiceException;
import com.drivingtalking.model.cache.RoomOnline;
import com.drivingtalking.model.room.Room;
import com.drivingtalking.service.IRoomService;
import com.drivingtalking.util.PagerManager;
import com.drivingtalking.util.PagerSupporter;
import com.drivingtalking.util.RedisUtils;
import org.apache.commons.lang3.StringUtils;
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
        Integer randomIndex = new Random().nextInt((index-1));
        return  validRoomIds.get(randomIndex);
    }

    private RoomOnline transformByRoom(Room room) {
        RoomOnline roomOnline = new RoomOnline();
        roomOnline.setId(room.getId());
        roomOnline.setLimit(room.getLimit());
        roomOnline.setMemberIds(new ArrayList<>());
        return roomOnline;
    }


}
