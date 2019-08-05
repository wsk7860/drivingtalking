package com.drivingtalking.util;

import lombok.Data;

import java.util.Date;

@Data
public class GridFsMetaData {

    private String createMemberId;

    private Date uploadDate;



    public GridFsMetaData() {

    }

    public GridFsMetaData(String createMemberId){
        this.uploadDate = new Date();
        this.createMemberId = createMemberId;

    }


}
