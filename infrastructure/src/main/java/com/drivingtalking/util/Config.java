package com.drivingtalking.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("project")
@Data
public class Config {

    private Agora agora;

    @Data
    public static class Agora {
        // 声网appId
        private String appId;
        //证书
        private String  certificate;
    }

}
