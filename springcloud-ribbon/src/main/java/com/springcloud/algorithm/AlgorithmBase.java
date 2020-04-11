package com.springcloud.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface AlgorithmBase {
    /**
     * 初始化 参数
     * @param ROUTELIST
     * @param ROUTEWEIGHT
     */
    void init(List<String> ROUTELIST, Map<String,Integer> ROUTEWEIGHT);

    /**
     * 获取服务
     * @return
     */
    public  String getServers();

}
