package com.springcloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;


@EnableHystrix
@EnableEurekaClient
@SpringBootApplication
public class AppHystrix {
    public static void main(String[] args) {
        SpringApplication.run(AppHystrix.class);
    }
}
