package com.zl.springcloud.controller;


import com.zl.springcloud.service.IMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageController {

    @Resource
    private IMessageService iMessageService;


    @GetMapping("/sendMessage")
    public String send(){
        return iMessageService.sendMessage();
    }

}
