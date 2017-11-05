package com.littlenum.hash;

import com.littlenum.AbsTest;

import java.util.*;

/**
 * Created by hero on 2017/10/14.
 */
public class BullCow extends AbsTest<String> {
    @Override
    public String doTest() {
        return null;
    }

    public String getHint(String secret, String guess) {
        if (secret == null || guess == null) {
            return null;
        }
        Map<Character, Integer> bull = new HashMap<>();
        List<Character> gg = new ArrayList<>();
        int b = 0, gc = 0;
        for (int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            if (guess.length() < i + 1) {
                bull.put(s, bull.containsKey(s) ? bull.get(s) + 1 : 1);
                continue;
            }
            char g = guess.charAt(i);
            if (s == g) {
                b++;
            } else {
                bull.put(s, bull.containsKey(s) ? bull.get(s) + 1 : 1);
                gg.add(g);
            }
        }
        for (int j = 0; j < gg.size(); j++) {
            char x = gg.get(j);
            if (bull.containsKey(x) && bull.get(x) > 0) {
                gc++;
                bull.put(x, bull.get(x) - 1);
            }
        }
        return b + "A" + gc + "B";
    }
}
