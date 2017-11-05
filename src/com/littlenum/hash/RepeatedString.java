package com.littlenum.hash;

import com.littlenum.AbsTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hero on 2017/10/13.
 */
public class RepeatedString extends AbsTest<List<String>> {
    @Override
    public List<String> doTest() {
        return null;
    }

    private Map<String, Integer> map = new HashMap<>();

    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null || s == "") {
            return new ArrayList<>();
        }
        for (int i = 0; i < s.length() - 9; i++) {
            String temp = s.substring(i, i + 10);
            if (map.containsKey(temp)) {
                map.put(temp, map.get(temp) + 1);
            } else {
                map.put(temp, 1);
            }
        }
        List<String> list = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key) > 1)
                list.add(key);
        }
        return list;
    }
}
