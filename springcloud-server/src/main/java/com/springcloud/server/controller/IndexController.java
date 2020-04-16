package com.springcloud.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class IndexController {

    @GetMapping({"","/index","main"})
    public Map<String,Object> index(){
        Map<String,Object> result = new HashMap();
        result.put("message","欢迎");
        result.put("code","200");
        return result;
    }
    @GetMapping({"","/index/throw","main/throw"})
    public Map<String,Object> indexThrow(){
        Map<String,Object> result = new HashMap();
        result.put("message","未知异常！");
        result.put("code","500");
        throw new RuntimeException(result.toString());
    }
    @GetMapping({"","/index/sleep","main/sleep"})
    public Map<String,Object> indexsleep(){
        try {
            Thread.sleep(13000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String,Object> result = new HashMap();
        result.put("message","欢迎");
        result.put("code","200");
        return result;
    }
}
