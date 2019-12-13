package com.drivingtalking.model.cache;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoomOnline implements Serializable {

    private String id;

    private List<String> memberIds;

    private Integer limit;

    private boolean isFull = false;
}
