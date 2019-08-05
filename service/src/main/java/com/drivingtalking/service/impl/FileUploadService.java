package com.drivingtalking.service.impl;

import com.drivingtalking.exception.ServiceException;
import com.drivingtalking.service.IFileUploadService;
import com.drivingtalking.util.FileUploadModel;
import com.drivingtalking.util.GridFsMetaData;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;


import java.io.InputStream;

@Service
public class FileUploadService implements IFileUploadService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Override
    public String saveFile(InputStream stream, String fileName, String contextType, GridFsMetaData metaData) {
        ObjectId objectId =  gridFsTemplate.store(stream,fileName,contextType,metaData);
        return objectId.toString();
    }

    @Override
    public FileUploadModel findById(String id) {
        FileUploadModel model  = new FileUploadModel();
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(new ObjectId(id)));
        GridFSFile file = gridFsTemplate.findOne(query);
        if(file == null) {
            throw new ServiceException("获取的文件不存在");
        }
        model.setFileName(file.getFilename());
        model.setLength(file.getLength());
        if(file.getMetadata() == null) {
            throw  new ServiceException("获取的文件没有记录类型");
        }
        model.setContextType(file.getMetadata().getString("_contentType"));


        return model;
    }
}
