package com.drivingtalking.configure;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfigure {

    @Bean
    public Mapper getMapper(){
        return  new DozerBeanMapper();
    }
}
