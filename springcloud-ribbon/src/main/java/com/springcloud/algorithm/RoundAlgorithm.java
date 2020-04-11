package com.springcloud.algorithm;

import com.springcloud.algorithm.demo.Weight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 原理是
 * 每个权重对应的都有一个最大权重
 * 最大权重每次都加上原始权重
 * 取最大权重 对应ip  最大权重 - 权重之和
 */
//*平滑 轮询 AABBAACA  A5 B2 C1
public class RoundAlgorithm implements AlgorithmBase{

    private static Map<String, Weight> weightMap = new HashMap();
    List<String> RouteList = new ArrayList<>();
    Map<String,Integer> RouteWeight = new HashMap<>();

    @Override
    public void init(List<String> RouteList, Map<String, Integer> RouteWeight) {
        this.RouteList=RouteList;
        this.RouteWeight=RouteWeight;

    }

    @Override
    public String getServers() {
        //轮询获取sum
        int totalWeight = RouteWeight.values().stream().reduce(0,(w1,w2)-> w1+w2);
        //初始化 权重集合，初始化时将curentWeight赋值为weight
        if(weightMap.isEmpty()){
            RouteWeight.forEach((key,value)->{
                weightMap.put(key,new Weight(key,value,value));
            });
        }
        Weight maxCurrentWeight = null;
        for (Weight weight : weightMap.values()){
            if(maxCurrentWeight == null || weight.getCurrentWeight()>maxCurrentWeight.getCurrentWeight()){
                maxCurrentWeight = weight;
            }
        }
        //将maxCurrentWeight减去权重总和
        maxCurrentWeight.setCurrentWeight(maxCurrentWeight.getCurrentWeight()-totalWeight);
        //将所有的ip的currentWeight统一加上原始权重
        for (Weight weight : weightMap.values()) {
            weight.setCurrentWeight(weight.getCurrentWeight()+weight.getWeight());
        }
        return maxCurrentWeight.getIp();
    }
}
