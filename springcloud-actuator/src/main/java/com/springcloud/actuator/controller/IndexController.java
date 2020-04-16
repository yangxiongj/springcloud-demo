package com.springcloud.actuator.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class IndexController {

    @Autowired
    private RestTemplate restTemplate;
    //此处写注册中心地址
    private final String SERVER_URL = "http://SERVER/";

    //短路保护
    @GetMapping("index/throw")
    @HystrixCommand(fallbackMethod="fallbackIndex")
    public Object index(){
        System.out.println("我重试了！");
        return restTemplate.getForObject(SERVER_URL+"main/throw",Object.class);
    }

    //限流
    @GetMapping("index/sleep")
    @HystrixCommand(fallbackMethod = "getFallbackIndex",threadPoolKey = "server",
            threadPoolProperties ={@HystrixProperty(name = "coreSize",value = "5")
            })
    public Object indexSleep(){
        return restTemplate.getForObject(SERVER_URL+"main/sleep",Object.class);
    }

    public Object fallbackIndex() {
        Map<String,Object> result = new HashMap<> ();
        result.put("code","500");
        result.put("msg","服务正在维护中");
        return result;
    }

    //断路器
    public Object getFallbackIndex() {
        Map<String,Object> result = new HashMap<> ();
        result.put("code","500");
        result.put("msg","服务器降级");
        return result;
    }
}
