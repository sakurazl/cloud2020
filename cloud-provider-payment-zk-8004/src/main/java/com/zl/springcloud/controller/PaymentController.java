package com.zl.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;


    @GetMapping(value = "payment/zk")
    public String payemntZk(){
        return "SpringCloud with zookeeper:" + serverPort + "\t" + UUID.randomUUID().toString();

    }


}
