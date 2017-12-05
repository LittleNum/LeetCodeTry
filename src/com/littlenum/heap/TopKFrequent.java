package com.littlenum.heap;

import com.littlenum.AbsTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hero on 2017/12/5.
 */
public class TopKFrequent extends AbsTest<Object> {
    @Override
    public Object doTest() {
        return topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
    }

    Map<Integer, Integer> map = new HashMap<>();
    int[] items;
    int current = 0;

    public List<Integer> topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        current = map.size();
        items = new int[current + 1];
        int i = 1;
        for (int key : map.keySet()) {
            items[i++] = key;
        }
        for (int n = current / 2; n > 0; n--) {
            percolateDown(n);
        }
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < k && current > 0; j++) {
            list.add(deleteMin());
        }
        return list;
    }

    private int deleteMin() {
        int min = items[1];
        items[1] = items[current--];
        percolateDown(1);
        return min;
    }

    private void percolateDown(int hole) {
        int tmp = items[hole];
        int child;
        for (; hole * 2 <= current; hole = child) {
            child = hole * 2;
            if (child != current && map.get(items[child]) < map.get(items[child + 1])) {
                child += 1;
            }
            if (map.get(tmp) < map.get(items[child])) {
                items[hole] = items[child];
            } else {
                break;
            }
        }
        items[hole] = tmp;
    }
}
