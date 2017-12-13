package com.littlenum.sort;

import com.littlenum.AbsTest;

import java.util.Arrays;

/**
 * Created by hero on 2017/12/13.
 */
public class ValidAnagram extends AbsTest<Object> {
    @Override
    public Object doTest() {
        return null;
    }

    public boolean isAnagram(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0 || s.length() != t.length()) {
            return "".equals(s) && "".equals(t);
        }
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        Arrays.sort(cs);
        Arrays.sort(ct);
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] != ct[i]) {
                return false;
            }
        }
        return true;
    }
}
