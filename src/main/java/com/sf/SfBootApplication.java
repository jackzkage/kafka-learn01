package com.sf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 * @author lijie.zh
 */
@EnableCaching
@EnableTransactionManagement
@SpringBootApplication
@ComponentScan("com.sf")
public class SfBootApplication implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("服务启动完成!");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SfBootApplication.class, args);
    }

}
