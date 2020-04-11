package com.springcloud.algorithm;

import java.util.*;

/**
 * 另一种实现思想 ：复制对应权重个数进行随机 不推荐
 */
/**
 * 随机权重算法的实现
 */
public class RandomAlgorithm implements AlgorithmBase {

    List<String> RouteList = new ArrayList<>();

    Map<String,Integer> RouteWeight = new HashMap<>();

    @Override
    public void init(List<String> RouteList, Map<String, Integer> RouteWeight) {
        this.RouteList=RouteList;
        this.RouteWeight=RouteWeight;

    }

    @Override
    public String getServers() {
        int totalWeight = 0;
        //权重是否全部一致 全部一致随机返回一个
        boolean sameWeight  =  true; //
        Object[] vs = RouteWeight.values().toArray();
        for(int i = 0; i < vs.length; i++ ){
            Integer value = (Integer)vs[i];
            totalWeight += value;
            if (sameWeight && i > 0 && !vs.equals(vs[i - 1])) {
                sameWeight = false;
            }
        }

        int index = new Random().nextInt(totalWeight);
        for (String key : RouteWeight.keySet()){
            int weight = RouteWeight.get(key);
            if(index < weight){
                return key;
            }
            index = index - weight;
        }

        //随机返回一个
        return (String)RouteWeight.keySet().toArray()[new Random().nextInt(RouteList.size())];
    }

}
