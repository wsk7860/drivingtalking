package com.drivingtalking.service;

import com.drivingtalking.model.room.Room;

public interface IRoomService extends IBaseService<Room> {
    String getRandomRoom(String longitude,String latitude);
}
