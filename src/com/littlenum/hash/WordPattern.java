package com.littlenum.hash;

import com.littlenum.AbsTest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by hero on 2017/11/21.
 */
public class WordPattern extends AbsTest<Object> {
    @Override
    public Object doTest() {
        return null;
    }

    public boolean wordPattern(String pattern, String str) {
        if (pattern == null || str == null) {
            return false;
        }
        String[] arrays = str.trim().split(" ");
        if (arrays == null || arrays.length == 0 || arrays.length != pattern.length()) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String temp = arrays[i];
            if (map.containsKey(c)) {
                if (!map.get(c).equals(temp)) {
                    return false;
                }
            } else {
                if(set.contains(temp)){
                    return false;
                }
                set.add(temp);
                map.put(c, temp);
            }
        }
        return true;
    }
}
