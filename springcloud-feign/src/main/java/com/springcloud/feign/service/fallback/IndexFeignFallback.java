package com.springcloud.feign.service.fallback;

import com.springcloud.feign.service.IndexFeign;

import java.util.Map;

public class IndexFeignFallback implements IndexFeign {
    @Override
    public Map<String, Object> index() {
        return null;
    }
}
