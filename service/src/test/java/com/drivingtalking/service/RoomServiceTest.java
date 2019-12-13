package com.drivingtalking.service;

import com.drivingtalking.ServerApplication;
import com.drivingtalking.model.room.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomServiceTest {

    @Autowired
    private IRoomService roomService;

    @Test
    public void test(){
        for (int i=0;i<10;i++) {
        Room room = new Room();
        room.setCreateDate(new Date());
        room.setLimit(50);
        room.setType(Room.Type.MULTIPLE.ordinal());
        room.setLatitude("0");
        room.setLatitude("0");
        room.setName("珠海"+(i+1));
        roomService.save(room);
        }
    }
}
