package com.springcloud.algorithm;

import com.springcloud.algorithm.demo.Weight;

import java.util.*;

/**
 * 原理是
 * 将原有服务hash出一个环
 * 再将获取到hash 客户端获取最接近 一个环上ip得点
 */
//*Hash 权重
public class HashAlgorithm implements AlgorithmBase{

    private static SortedMap<Integer, String> virtualNodes = new TreeMap<>();
    List<String> RouteList = new ArrayList<>();
    Map<String,Integer> RouteWeight = new HashMap<>();
    @Override
    public void init(List<String> RouteList, Map<String, Integer> RouteWeight) {
        this.RouteList=RouteList;
        this.RouteWeight=RouteWeight;

        //对每个正式节点添加虚拟节点，虚拟节点会更具Hash算法进行散列
        for(String ip : RouteList){
            for(int i = 0; i <virtualNodes.size();i++){
                int hash = getHash(ip+"VN"+i);
                virtualNodes.put(hash,ip);
            }
        }
    }

    @Override
    public String getServers() {
        return null;
    }

    public String getServers(String client) {
        int hash = getHash(client);
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);

        //大于该Hash值得第一个元素得位置
        Integer nodeIndex = subMap.firstKey();
        //如果不存在大于该hash值得元素，则返回根节点
        if(nodeIndex == null){
            nodeIndex = virtualNodes.firstKey();
        }
        return subMap.get(nodeIndex);
    }

    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        // 如果算出来的是负值 取绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

}
