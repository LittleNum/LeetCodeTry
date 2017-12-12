package com.littlenum.sort;

import com.littlenum.AbsTest;

import java.util.*;

/**
 * Created by hero on 2017/12/12.
 */
public class IntersectionTwoArray extends AbsTest<Object> {
    @Override
    public Object doTest() {
        return null;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        Set<Integer> set = new HashSet<>();
        for (; ; ) {
            if (nums1[i] == nums2[j]) {
                set.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
            if (i == nums1.length || j == nums2.length) {
                break;
            }
        }
        if (set.size() == 0) {
            return new int[]{};
        }
        int[] answer = new int[set.size()];
        int n = 0;
        for (int key : set) {
            answer[n++] = key;
        }
        return answer;
    }
}
