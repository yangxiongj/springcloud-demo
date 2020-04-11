package com.springcloud.algorithm;

import java.util.*;

/**
 *  路由中心
 */
public class RouteServer {

    /**
     * 服务可分配的url
     */
    public static final List<String> ROUTELIST = new ArrayList<>();

     static {
        ROUTELIST.add("192.168.0.0");
        ROUTELIST.add("192.168.0.1");
        ROUTELIST.add("192.168.0.2");
        ROUTELIST.add("192.168.0.3");
        ROUTELIST.add("192.168.0.4");
        ROUTELIST.add("192.168.0.5");
        ROUTELIST.add("192.168.0.6");
        ROUTELIST.add("192.168.0.7");
    }

    /**
     * 当前Url的权重
     */
    public static final Map<String,Integer> ROUTEWEIGHT = new HashMap<>();

    static {
        ROUTEWEIGHT.put("192.168.0.0",1);
        ROUTEWEIGHT.put("192.168.0.1",6);
        ROUTEWEIGHT.put("192.168.0.2",1);
        ROUTEWEIGHT.put("192.168.0.3",2);
        ROUTEWEIGHT.put("192.168.0.4",7);
        ROUTEWEIGHT.put("192.168.0.5",8);
        ROUTEWEIGHT.put("192.168.0.6",1);
        ROUTEWEIGHT.put("192.168.0.7",9);
    }

    // 当前服务器最小活跃数
    public static final Map<String, Integer> ACTIVITY_LIST = new LinkedHashMap<String, Integer>();
    static {
        ACTIVITY_LIST.put("192.168.0.1", 2);
        ACTIVITY_LIST.put("192.168.0.2", 0);
        ACTIVITY_LIST.put("192.168.0.3", 1);
        ACTIVITY_LIST.put("192.168.0.4", 3);
        ACTIVITY_LIST.put("192.168.0.5", 0);
        ACTIVITY_LIST.put("192.168.0.6", 1);
        ACTIVITY_LIST.put("192.168.0.7", 4);
    }
}
