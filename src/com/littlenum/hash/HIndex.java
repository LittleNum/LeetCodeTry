package com.littlenum.hash;

import com.littlenum.AbsTest;

import java.util.Arrays;

/**
 * Created by hero on 2017/11/10.
 */
public class HIndex extends AbsTest<Object> {
    @Override
    public Object doTest() {
        return hIndex(new int[]{3, 2, 1, 5, 6, 8, 9, 10,20});
    }

    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        Arrays.sort(citations);
        for (int i = citations.length; i > 0; i--) {
            int index = citations.length - i;
            if (citations[index] >= i) {
                return i;
            }
        }
        return 0;
    }
}
