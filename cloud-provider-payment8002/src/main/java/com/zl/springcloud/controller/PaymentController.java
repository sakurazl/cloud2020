package com.zl.springcloud.controller;

import com.zl.springcloud.entities.CommonResult;
import com.zl.springcloud.entities.Payment;
import com.zl.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * 端口号
     * 用于查看负载均衡效果
     */
    @Value("${server.port}")
    private String serverPort;

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
            return new CommonResult(200,"新增成功，serverPort:"+serverPort,payment);
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

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }



}
