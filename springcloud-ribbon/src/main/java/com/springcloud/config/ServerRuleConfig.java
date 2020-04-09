package com.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//这个类不能被ComponentScan扫描到否则所有服务将使用这个策略
@Configuration
public class ServerRuleConfig {
    @Bean
    public IRule iRule(){
        return new RetryRule();
    }
}
