package com.sf.learn;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lijie.zh
 */
@Component
@Slf4j
public class KafkaConsumer {


    @KafkaListener(topics = {Constant.TEST_TOPIC}, groupId = Constant.DEFAULT_GROUP)
    public void consumerMsg(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
        try {
            for (ConsumerRecord record : records) {
                log.debug("\n消费到消息：{},{}", record, String.format("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //手动提交偏移量
            ack.acknowledge();
        }
    }

}