package com.springcloud.ribbon;

import com.springcloud.config.ServerRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

@EnableEurekaClient
@SpringBootApplication
//不同服务使用不同负载均衡
@RibbonClients(@RibbonClient(name = "server",configuration = ServerRuleConfig.class))
public class AppRibbon {
    public static void main(String[] args) {
        SpringApplication.run(AppRibbon.class);
    }
}
