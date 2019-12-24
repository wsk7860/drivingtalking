package com.drivingtalking.service;

import com.drivingtalking.model.cache.RoomOnline;
import com.drivingtalking.model.room.Room;

public interface IRoomService extends IBaseService<Room> {

    String getRandomRoom(String longitude,String latitude);

    Boolean joinRoom(String roomId);

    Boolean leaveRoom(String roomId);

    Boolean handleRoomMember(String roomId,String memberId);

    RoomOnline getRoomById(String roomId);
}
