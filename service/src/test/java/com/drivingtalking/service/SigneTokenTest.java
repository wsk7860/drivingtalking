package com.drivingtalking.service;

import com.drivingtalking.ServerApplication;
import com.drivingtalking.util.SignalingToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SigneTokenTest {


    @Test
    public void test(){
        String token = SignalingToken.getToken("5e8f25a35b9e4441923c13d542e74c1c","5039b9df22a9444897061be68a3727af","0","15762309710975bzRhNFAWO4PtiCT",3600);
        System.out.println(token);
    }
}
