package com.drivingtalking.service;

import com.drivingtalking.util.FileUploadModel;
import com.drivingtalking.util.GridFsMetaData;


import java.io.InputStream;

public interface IFileUploadService {

     String saveFile(InputStream stream, String fileName, String contentType, GridFsMetaData metaData);

     FileUploadModel findById(String id);
}
