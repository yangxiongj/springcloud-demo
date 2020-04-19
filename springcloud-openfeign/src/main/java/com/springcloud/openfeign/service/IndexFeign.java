package com.springcloud.openfeign.service;

import com.springcloud.openfeign.service.fallback.IndexFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(value = "server",fallback = IndexFeignFallback.class)
public interface IndexFeign {
    @GetMapping("/index")
    public Map<String,Object> index();
}
