package com.sf.learn;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class KafkaProvider {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private KafkaProperties kafkaProperties;

    //发送消息方法
    public void send(Long num) {
        Message message = new Message();
        message.setId(num);
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        log.info("+++++++++++++++++++++  message = {}", JSON.toJSONString(message));
        ListenableFuture listenableFuture = kafkaTemplate.send("test-topic", JSON.toJSONString(message));
        //发送成功后回调
        SuccessCallback successCallback = result -> System.out.println("发送成功" + result);

        //发送失败回调
        FailureCallback failureCallback = ex -> {
            System.out.println("发送失败");
            ex.printStackTrace();
        };

        listenableFuture.addCallback(successCallback, failureCallback);
    }
}