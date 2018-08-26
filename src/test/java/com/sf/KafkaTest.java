package com.sf;

import com.sf.learn.KafkaConsumer;
import com.sf.learn.KafkaProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhonglj on 2017/12/2.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SfBootApplication.class,
       }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class KafkaTest {

    @Autowired
    KafkaProvider kafkaSender;

    @Autowired
    KafkaConsumer kafkaConsumer;

    @Test
    public void send() throws Exception{
        int num = 5;

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(num);
        for( int i=0;i<num; i++){
            final int n = i;
            fixedThreadPool.execute(() -> {
                kafkaSender.send(new Long(n));
            });
        }

        Thread.sleep(Integer.MAX_VALUE);
    }



}
