package Algorithms.greedy;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 贪心算法 (广播覆盖问题)
 * @author wangguoqiang
 * @date 2020/4/12 10:21
 */
public class Greedy {
    public static void main(String[] args) {
        Map<String, HashSet<String>> radioStations = Maps.newHashMap();
        HashSet<String> radioStation1 = Sets.newHashSet("北京","上海","天津");
        HashSet<String> radioStation2 = Sets.newHashSet("北京","广州","深圳");
        HashSet<String> radioStation3 = Sets.newHashSet("成都","上海","杭州");
        HashSet<String> radioStation4 = Sets.newHashSet("上海","天津");
        HashSet<String> radioStation5 = Sets.newHashSet("杭州","大连");
        radioStations.put("K1",radioStation1);
        radioStations.put("K2",radioStation2);
        radioStations.put("K3",radioStation3);
        radioStations.put("K4",radioStation4);
        radioStations.put("K5",radioStation5);

        HashSet<String> allAreas = Sets.newHashSet("北京","上海","天津","广州","深圳","成都","杭州","大连");


        List<String> selects = Lists.newArrayList();//存放选择的电台
        HashSet<String> tempSet = Sets.newHashSet();//临时存在选择的电台覆盖的尚未覆盖的地区
        int size = 0;//存放每次比较所能覆盖尚未被覆盖地区最大的个数
        String maxKey = null;//存放每次比较所能覆盖尚未被覆盖地区最大的set key
        
        while (!allAreas.isEmpty()){
            maxKey = null;
            for (String key : radioStations.keySet()) {
                tempSet.clear();//每次循环前需要进行清理
                tempSet.addAll(radioStations.get(key));
                tempSet.retainAll(allAreas);//计算交集
                if(tempSet.size()>0 && (maxKey == null || tempSet.size()>size)){//此处体现贪心，每次都选择能覆盖尚未被覆盖地区最多的电台
                    maxKey = key;
                    size = tempSet.size();
                }
            }
            if(maxKey!=null){
                selects.add(maxKey);
                //将maxKey对应的电台覆盖区域从所有区域中移除，留下的是尚未被覆盖的
                allAreas.removeAll(radioStations.get(maxKey));
            }
        }

        System.out.println("选择的电台是："+selects);
    }
}
