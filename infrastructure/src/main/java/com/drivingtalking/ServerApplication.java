package com.drivingtalking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.drivingtalking","com.drivingtalking.exception"})
@ServletComponentScan
public class ServerApplication {

    public static void main(String[] args){
        SpringApplication.run(ServerApplication.class,args);
    }
}
