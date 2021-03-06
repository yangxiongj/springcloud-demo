package com.springcloud.openfeign;

import com.springcloud.openfeign.service.IndexFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class IndexController {

    @Autowired
    private IndexFeign indexFeign;
    //此处写注册中心地址
    private final String SERVER_URL = "http://SERVER/";

    @GetMapping("index")
    public Object index(){
        return indexFeign.index();
    }

}
