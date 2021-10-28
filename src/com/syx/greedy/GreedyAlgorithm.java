package com.syx.greedy;

import java.util.*;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> map = new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        map.put("k1", hashSet1);
        map.put("k2", hashSet2);
        map.put("k3", hashSet3);
        map.put("k4", hashSet4);
        map.put("k5", hashSet5);
        HashSet<String> allAreas = new HashSet<>();
        map.values().forEach(allAreas::addAll);

        String maxKey = null;
        HashSet<String> tempSet = new HashSet<>();
        HashMap<String, Integer> countMap = new HashMap<>();
        List<String> selects = new ArrayList<>();
        while (!allAreas.isEmpty()) {
            maxKey = null;
            for (Map.Entry<String, HashSet<String>> entry : map.entrySet()) {
                tempSet.clear();
                tempSet.addAll(entry.getValue());
                tempSet.retainAll(allAreas);
                countMap.put(entry.getKey(), tempSet.size());

                if (tempSet.size() > (countMap.get(maxKey) != null ? countMap.get(maxKey) : 0)) {
                    maxKey = entry.getKey();
                }
            }

            selects.add(maxKey);
            allAreas.removeAll(map.get(maxKey));
        }

        System.out.println(selects);
    }
}
