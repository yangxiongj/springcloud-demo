package com.springcloud.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AppConfigClient {
    public static void main(String[] args) {
        SpringApplication.run(AppConfigClient.class);
    }
}
