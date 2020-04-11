package com.springcloud.algorithm;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class RoundAlgorithmTest {

    @Test
    public void getServers() {
        RoundAlgorithm roundAlgorithm = new RoundAlgorithm();

        roundAlgorithm.init(RouteServer.ROUTELIST,RouteServer.ROUTEWEIGHT);
        Map<String,Integer> percentageMap = new HashMap();
        int size = 100;
        for(int i = 0 ; i<size; i++){
            String service = roundAlgorithm.getServers();
            Integer count = percentageMap.get(service);
            if(count == null){
                count = 0;
            }
            count = count + 1;
            percentageMap.put(service,count);
            System.out.println(service);
        }
        System.out.println(percentageMap.toString());
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
// 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        for(String key : percentageMap.keySet()){
            System.out.println(key+" : "+roundAlgorithm.RouteWeight.get(key)+" : "+numberFormat.format((float)percentageMap.get(key)/(float)size * 100)+"%");
        }
    }
}
