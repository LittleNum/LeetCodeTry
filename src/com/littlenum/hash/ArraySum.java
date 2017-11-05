package com.littlenum.hash;

import com.littlenum.AbsTest;

import java.util.*;

/**
 * Created by hero on 2017/10/28.
 */
public class ArraySum extends AbsTest<List<List<Integer>>> {
    @Override
    public List<List<Integer>> doTest() {


        return fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        list = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
                list.add(key);
            }
        }
        Collections.sort(list);
         return    fourSum(map,target,4,0);

    }

    private ArrayList<Integer> list ;

    public List<List<Integer>> fourSum(HashMap<Integer, Integer> map, int target, int count,int last) {
        List<List<Integer>> thisRes = new ArrayList<>();
        if (count < 1) {
            return null;
        }
        if (count == 1) {
            if (map.containsKey(target) && map.get(target) > 0 && target >= list.get(last)) {
                List<Integer> s = new ArrayList<>();
                s.add(target);
                thisRes.add(s);
                return thisRes;
            }
            return null;
        }
        for (int i=0;i<list.size();i++){
            int k = list.get(i);
            if(i<last){
                continue;
            }
            if (map.get(k) > 0) {
                map.put(k, map.get(k) - 1);
                List<List<Integer>> temp = fourSum(map, target - k, count - 1,i);
                if (temp != null) {
                    for (List<Integer> x : temp) {
                        List<Integer> s = new ArrayList<>();
                        s.add(k);
                        s.addAll(x);
                        thisRes.add(s);
                    }
                }
                    map.put(k, map.get(k) + 1);
            }
        }
        return thisRes;
    }

    List<List<Integer>> result = new ArrayList<>();
    HashMap<Integer, Integer> map = new HashMap<>();

    private boolean getSum(Map<Integer, Integer> map, int sum, int num, List<Integer> list) {
        if (num == 0) {
            return false;
        }
        if (num == 1) {
            if (!map.containsKey(sum) || map.get(sum) <= 0) {
                return false;
            }
            list.add(sum);
            result.add(list);
            return true;
        } else {
            for (int key : map.keySet()) {
                if (num == 4) {
                    map = (HashMap<Integer, Integer>) this.map.clone();
                    list = new ArrayList<>();
                }
                int count = map.get(key) - 1;
                if (count >= 0) {
                    map.put(key, count);
                    if (getSum(map, sum - key, num - 1, list)) {
                        list.add(key);
                    } else {
                        map.put(key, count + 1);
                    }
                }
            }
        }
        return false;
    }
}
