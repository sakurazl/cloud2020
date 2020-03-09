package com.zl.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ConsumerController {


    //注册进consul的订单服务别名
    public static final String INVOKE_URL="http://consul-provider-payment";



    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/consumer/payment/consul")
    public String paymentInfo() {
        return restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
    }


}
