package com.littlenum.hash;

import com.littlenum.AbsTest;

import java.util.*;

/**
 * Created by hero on 2017/11/9.
 */
public class GroupAnagrams extends AbsTest<Object> {

    @Override
    public Object doTest() {
        return null;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<String>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
