package com.littlenum.sort;

import com.littlenum.AbsTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hero on 2017/12/13.
 */
public class IntersectionOfTwoArrays extends AbsTest<Object> {
    @Override
    public Object doTest() {
        return null;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[]{};
        }
        int count1 = nums1.length;
        int count2 = nums2.length;
        int i = 0;
        int j = 0;
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        while (i < count1 && j < count2) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        if (list.size() == 0) {
            return new int[]{};
        }
        int[] answer = new int[list.size()];
        for (int n = 0; n < list.size(); n++) {
            answer[n] = list.get(n);
        }
        return answer;
    }
}
