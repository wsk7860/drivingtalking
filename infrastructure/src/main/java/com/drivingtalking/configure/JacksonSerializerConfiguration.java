package com.drivingtalking.configure;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonSerializerConfiguration {

    @Bean
    public ObjectMapper skipEmptyObjectMapper() {
        return new SkipEmptyObjectMapper();
    }

    public static class SkipEmptyObjectMapper extends ObjectMapper {
        public  SkipEmptyObjectMapper(){
            super();
            super.setSerializationInclusion(JsonInclude.Include.NON_NULL);        }

    }
}
