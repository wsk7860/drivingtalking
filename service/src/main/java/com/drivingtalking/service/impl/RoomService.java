package com.drivingtalking.service.impl;

import com.drivingtalking.dao.RoomDAO;
import com.drivingtalking.model.room.Room;
import com.drivingtalking.service.IRoomService;
import org.springframework.stereotype.Service;

@Service
public class RoomService extends BaseService<Room, RoomDAO> implements IRoomService {
}
