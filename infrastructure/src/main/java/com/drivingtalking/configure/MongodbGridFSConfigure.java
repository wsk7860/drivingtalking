package com.drivingtalking.configure;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;

@Configurable
public class MongodbGridFSConfigure {

    @Autowired
    private MongoDbFactory mongoDbFactory;

    @Autowired
    private GridFSBucket gridFSBucket;


    @Bean
    public GridFSBucket getGridFSBuckets() {
        MongoDatabase db = mongoDbFactory.getDb();
        return GridFSBuckets.create(db);
    }
}
