package com.zl.springcloud.controller;

import com.zl.springcloud.entities.CommonResult;
import com.zl.springcloud.entities.Payment;
import com.zl.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;


    /**
     * @author zl
     * @description: 新增流水号
     * @date: 2020/3/7
     * @param payment
     * @Return com.zl.springcloud.entities.CommonResult
     */
    @PostMapping(value = "payment/create")
    public CommonResult create(@RequestBody Payment payment){//RequestBody注解不要忘
        int result=paymentService.create(payment);
        if(result>0){
            return new CommonResult(200,"新增成功：serverPort:"+serverPort,payment);
        }else{
            return new CommonResult(500,"新增失败",result);
        }
    }

    @GetMapping(value = "payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment=paymentService.getPaymentById(id);
        log.info("查询结果："+payment);
        if(payment!=null){
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        }else{
            return new CommonResult(404,"没有对应记录，查询id："+id,null);
        }
    }

    /**
     * 服务发现
     * @return
     */
    @GetMapping(value = "payment/discovery")
    public Object discovery(){
        //获取所有注册微服务
        List<String> services=discoveryClient.getServices();
        for (String serviceName:services) {
            log.info("serviceName:"+serviceName);
        }

        //获取一个微服务下的所有实例
        List<ServiceInstance> instances=discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;

    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping("payment/zipkin")
    public String paymentZipkin(){
        return "test zipkin !!!!!!!!!!!!!!!!!!";
    }




}
