package com.springcloud.ribbon.config;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.Server;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.springcloud.ribbon")
public class RestConfig {

    @Bean
    //使用负载均衡策略
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
