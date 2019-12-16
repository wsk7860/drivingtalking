package com.drivingtalking.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket setRestApi() {
        return  new Docket(DocumentationType.SWAGGER_2).pathMapping("/").select()
                .apis(RequestHandlerSelectors.basePackage("com.drivingtalking.controller")).paths(PathSelectors.any())
                .build().apiInfo(new ApiInfo("swagger","api说明","1.0","test1",
                        new Contact("huxiaoyuan","www.dringtalking.com","wsk7860@163.com"),"the apache license","www.baidu.com", Arrays.asList(new StringVendorExtension("test","test2"))));
    }
}
