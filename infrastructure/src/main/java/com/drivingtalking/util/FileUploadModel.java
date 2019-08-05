package com.drivingtalking.util;

import lombok.Data;

import java.io.InputStream;

@Data
public class FileUploadModel {

    private String fileName;

    private long  length;

    private GridFsMetaData metaData;

    private InputStream inputStream;

    private String contextType;
}
