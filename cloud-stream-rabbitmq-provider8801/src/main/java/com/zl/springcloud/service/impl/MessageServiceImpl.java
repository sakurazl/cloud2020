package com.zl.springcloud.service.impl;

import com.zl.springcloud.service.IMessageService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;


@EnableBinding(Source.class)//定义消息的推送管道
public class MessageServiceImpl implements IMessageService {
    /**
     * 消息发送管道
     */
    @Resource
    private MessageChannel output;


    @Override
    public String sendMessage() {
        String uuid= UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(uuid).build());
        System.out.println("消息生产者，生产："+uuid);
        return uuid;
    }


















}
