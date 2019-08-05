package com.drivingtalking.controller;


import com.drivingtalking.model.room.Room;
import com.drivingtalking.util.ResponseModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@RequestMapping("/room")
@RestController
public class RoomController {


    @Path("/saveOrUpdateRoom")
    @POST
    public ResponseModel saveOrUpdateRoom(){
        return  null;
    }

    @Path("/joinRoom")
    public ResponseModel joinRoom(@PathVariable("id") String id){
        return  null;
    }



}
