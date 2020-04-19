package com.springcloud.openfeign.service.fallback;


import com.springcloud.openfeign.service.IndexFeign;

import java.util.Map;

public class IndexFeignFallback implements IndexFeign {
    @Override
    public Map<String, Object> index() {
        return null;
    }
}
