package com.sf.controller;

import com.sf.learn.KafkaProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijie.zh
 */
@RestController
public class KafkaController {

    @Autowired
    KafkaProvider kafkaProvider;


    @GetMapping("/send/{id}")
    public String send(@PathVariable("id") Long id) {
        kafkaProvider.send(id);
        return String.valueOf(id);
    }

}
