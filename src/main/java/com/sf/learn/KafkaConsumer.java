package com.sf.learn;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = {"pg-topic"}, groupId = "group1")
    public void listen(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) throws InterruptedException {
        try {
            for (ConsumerRecord record : records) {
                Optional<?> kafkaMessage = Optional.ofNullable(record.value());
                if (kafkaMessage.isPresent()) {
                    Object message = kafkaMessage.get();
                    log.info("-----------------{}", record);
                }
            }
        } finally {
            ack.acknowledge();//手动提交偏移量
        }
    }


    @KafkaListener(topics = {"test-topic"})
    public void consumerMsg(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
        try {
            for (ConsumerRecord record : records) {
                log.debug("-----------------{},{}", record, String.format("offset = %d, key = %s, value = %s%n \n", record.offset(), record.key(), record.value()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ack.acknowledge();//手动提交偏移量
        }
    }

}