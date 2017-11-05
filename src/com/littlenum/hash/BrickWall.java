package com.littlenum.hash;

import com.littlenum.AbsTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hero on 2017/11/5.
 * 554. Brick Wall
 */
public class BrickWall extends AbsTest<Integer> {
    @Override
    public Integer doTest() {
        List<List<Integer>> wall = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        row.add(1);
        wall.add(row);
        wall.add(row);
        wall.add(row);
        return leastBricks(wall);
    }

    public int leastBricks(List<List<Integer>> wall) {
        if (wall == null || wall.size() == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int maxKey = 0;
        int max = 0;
        for (int i = 0; i < wall.size(); i++) {
            List<Integer> row = wall.get(i);
            if (row.size() == 1) {
                continue;
            }
            int count = map.getOrDefault(row.get(0), 0) + 1;
            if (count > max) {
                max = count;
                maxKey = row.get(0);
            }
            map.put(row.get(0), count);
            for (int j = 1; j < row.size() - 1; j++) {
                int s = row.get(j - 1) + row.get(j);
                row.set(j, s);
                count = map.getOrDefault(s, 0) + 1;
                if (count > max) {
                    max = count;
                    maxKey = s;
                }
                map.put(s, count);
            }
        }
        return wall.size() - map.getOrDefault(maxKey, 0);
    }

    private int sum(List<Integer> list, int index) {
        if (list == null || list.size() == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < list.size() && i <= index; i++) {
            sum += list.get(i);
        }
        return sum;
    }
}
