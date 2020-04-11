package com.springcloud.algorithm;

import java.util.*;

/**
 * 原理
 * 取出最小活跃数得 ip
 * 如果多个 则按权重大的取一个
 * 如果权重相等 则随机取一个
 *
 * 最小活跃算法的实现
 *
 */
public class LeastActiveAlgorithm {

    List<String> RouteList = new ArrayList<>();

    Map<String,Integer> RouteWeight = new HashMap<>();
    // 当前服务器最小活跃数
    Map<String, Integer> ACTIVITY_LIST = new LinkedHashMap<String, Integer>();
    public void init(List<String> RouteList, Map<String, Integer> RouteWeight,Map<String, Integer> ACTIVITY_LIST) {
        this.RouteList=RouteList;
        this.RouteWeight=RouteWeight;
        this.ACTIVITY_LIST = ACTIVITY_LIST;
    }

    public String getServers() {
        List<String> minActivityIps = new ArrayList<>();

        Optional<Integer> minValue = RouteWeight.values().stream().min(Comparator.naturalOrder());
        if (minValue.isPresent()) {
            ACTIVITY_LIST.forEach((ip, activity) -> {
                if (activity.equals(minValue.get())) {
                    minActivityIps.add(ip);
                }
            });
        }
        //如果最小活跃数是多个 ，则按权重大小取
        if (minActivityIps.size() > 1) {
            Map<String, Integer> weightList = new LinkedHashMap<String, Integer>();
            RouteWeight.forEach((ip, weight) -> {
                if (minActivityIps.contains(ip)) {
                    weightList.put(ip, RouteWeight.get(ip));
                }
            });
        }

        //其他情况选择方法，可以轮询权重，可以随机权重
        //weightList
        return null;
    }
}
