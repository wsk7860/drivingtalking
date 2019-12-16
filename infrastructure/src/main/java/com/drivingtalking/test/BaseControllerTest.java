package com.drivingtalking.test;


import com.alibaba.fastjson.JSONObject;
import com.drivingtalking.ServerApplication;
import com.drivingtalking.util.ResponseModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseControllerTest {


    @Autowired
    private TestRestTemplate testRestTemplate;

    private String sessionIDStr;

    protected <T> T requestForGet(String uri, Class<T> t, Map<String, Object> paramMap) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionIDStr);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<T> responseEntity = testRestTemplate.exchange(uri, HttpMethod.GET, httpEntity, t, paramMap);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    protected <T> T requestForPost(String uri, Class<T> t, MultiValueMap<String, Object> params) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionIDStr);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(params, headers);
        ResponseEntity<ResponseModel> responseEntity = testRestTemplate.postForEntity(uri, httpEntity, ResponseModel.class);
        return null;
    }

    protected <T> T requestForPostJson(String uri, Object paramsObject, Class<T> t) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("Cookie", sessionIDStr);
        HttpEntity<String> formEntity = new HttpEntity<>(JSONObject.toJSONString(paramsObject), headers);
        ResponseEntity<T> responseEntity = testRestTemplate.postForEntity(uri, formEntity, t);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        return responseEntity.getBody();
    }


    @Before
    public void setUp() {
        if (sessionIDStr == null) {
            MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
            paramMap.add("loginName", "18666982982");
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap, headers);
            ResponseEntity<ResponseModel> responseEntity = testRestTemplate.postForEntity("/login", httpEntity, ResponseModel.class);
            if (responseEntity.getBody() != null) {
                if (responseEntity.getBody().getStatus() == 1) {
                    HttpHeaders httpHeaders = new HttpHeaders();
                    Object cookie = responseEntity.getHeaders().get("Set-Cookie");
                    if (cookie != null) {
                        sessionIDStr = cookie.toString().replace("[", "").replace("]", "");
                    }
                } else {
                    throw new RuntimeException("初始化登录失败");
                }
            } else {
                throw new RuntimeException("请求登录接口失败，请确保接口正常");
            }
        }
    }
}
