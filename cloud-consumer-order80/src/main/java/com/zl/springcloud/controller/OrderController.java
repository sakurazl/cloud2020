package com.zl.springcloud.controller;

import com.zl.springcloud.entities.CommonResult;
import com.zl.springcloud.entities.Payment;
import com.zl.springcloud.lib.LoadBalancer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {

    //public static final String PAYMENT_URL="http://localhost:8001";  单机版
    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE"; //注册进eureka的微服务

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 自定义负载均衡规则
     */
    @Resource
    private LoadBalancer loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    /**
     * 测试自己写的轮询规则
     * @return
     */
    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instanceList=discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instanceList==null||instanceList.size()<=0){
            return  null;
        }
        ServiceInstance instance=loadBalancer.instances(instanceList);
        URI uri=instance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

    /**
     * 测试链路监控 zipkin
     */
    @GetMapping("payment/zipkin")
    public String getPamentZipkin(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin",String.class);
    }



}
