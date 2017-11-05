package com.littlenum;

import com.littlenum.hash.ArraySum;
import com.littlenum.hash.ReplaceWords;
import com.littlenum.tree.*;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // write your code here
        new ReplaceWords().test();
    }

    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    int x = array[i];
                    array[i] = array[j];
                    array[j] = x;
                }
            }
        }
    }

    private static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i - 1;
            int key = array[i];
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    private static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            int temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if ((nums1 == null || nums1.length == 0) && (nums2 == null || nums2.length == 0)) {
            return 0;
        }
        if (nums1 == null || nums1.length == 0) {
            int length = nums2.length;
            return length % 2 == 0 ? (nums2[length / 2] + nums2[length / 2 - 1]) / 2d : nums2[length / 2];
        }
        if (nums2 == null || nums2.length == 0) {
            int length = nums1.length;
            return length % 2 == 0 ? (nums1[length / 2] + nums1[length / 2 - 1]) / 2d : nums1[length / 2];
        }
        int[] array = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, array, 0, nums1.length);
        System.arraycopy(nums2, 0, array, nums1.length, nums2.length);
        mergeSort(array);
        int length = array.length;
        return length % 2 == 0 ? (array[length / 2] + array[length / 2 - 1]) / 2d : array[length / 2];
    }


    private static void mergeSort(int[] array) {
        int[] aux = new int[array.length];
        sort(array, aux, 0, array.length - 1);
    }

    private static void sort(int[] array, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(array, aux, lo, mid);
        sort(array, aux, mid + 1, hi);
        merge(array, aux, lo, mid, hi);
    }

    private static void merge(int[] array, int[] aux, int lo, int mid, int hi) {
        System.arraycopy(array, 0, aux, 0, array.length);
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) array[k] = aux[j++];
            else if (j > hi) array[k] = aux[i++];
            else if (aux[j] < aux[i]) array[k] = aux[j++];
            else array[k] = aux[i++];
        }
    }

    private static void sort2(int[] array, int[] aux, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        sort2(array, aux, left, mid);
        sort2(array, aux, mid + 1, right);
        merge2(array, aux, left, mid, right);
    }

    private static void merge2(int[] array, int[] aux, int left, int mid, int right) {
        System.arraycopy(array, 0, aux, 0, array.length);
        int rstart = mid + 1;
        int index = left;
        while (left <= mid && rstart <= right) {
            if (array[left] <= array[rstart]) {
                array[index++] = aux[left++];
            } else {
                array[index++] = aux[rstart++];
            }
        }
        while (rstart <= right) {
            array[index++] = aux[rstart++];
        }
        while (left <= mid) {
            array[index++] = aux[left++];
        }
    }

    private static void testTwo() {
        int[] s = new int[]{2, 7, 11, 13};
        twoSum(s, 9);
    }

    private static void testAddTwo() {
        ListNode l1 = new ListNode(1);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);

        ListNode l3 = addTwoNumbers(l1, l2);
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int length = 0;
        int max = 0;
        int last = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                length = i - map.get(c);
                if (length > max) {
                    max = length;
                }
                for (int j = last; map.containsKey(c) && j <= map.get(c); j++) {
                    if (map.containsKey(s.charAt(j)) && map.get(s.charAt(j)) == j) {
                        map.remove(s.charAt(j));
                    }
                    last = j;
                }
            } else {
                length++;
                if (length > max) {
                    max = length;
                }
            }
            map.put(c, i);
        }
        return length > max ? length : max;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 != null ? l1 : l2;
        }
        ListNode list = null;
        ListNode result = null;
        int value;
        int remain = 0;
        int high = 0;
        while (l1 != null || l2 != null) {
            value = getVal(l1) + getVal(l2) + high;
            high = value / 10;
            remain = value % 10;
            if (list == null) {
                list = new ListNode(high > 0 ? remain : value);
                result = list;
            } else {
                list.next = new ListNode(high > 0 ? remain : value);
                list = list.next;
            }
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        if (high > 0) {
            list.next = new ListNode(high);
        }
        return result;
    }

    private static int getVal(ListNode node) {
        return node != null ? node.val : 0;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) {
            return 0;
        }
        if (nums1 == null) {
            int length = nums2.length;
            int mid = (length - 1) / 2;
            return length % 2 == 1 ? nums2[mid] : (nums2[mid] + nums2[mid + 1]) / 2;
        }
        if (nums2 == null) {
            int length = nums1.length;
            int mid = (length - 1) / 2;
            return length % 2 == 1 ? nums1[mid] : (nums1[mid] + nums1[mid + 1]) / 2;
        }
        return sort(nums1, nums2);
    }

    private static double sort(int[] num1, int[] num2) {
        int start = 0;
        int end = num1.length;
        int[] dest = new int[num1.length + num2.length];
        for (int i = 0; i < num2.length; i++) {
            int x = start > end ? end : insert(num1, start, end, num2[i]);
            if (start <= end && x + 1 <= end)
                System.arraycopy(num1, start, dest, start + i, x - start + 1);
            dest[x + i] = num2[i];
            start = x + 1;
        }
        int mid = num1.length + num2.length;
        if (mid % 2 == 1) {
            return dest[mid / 2];
        }
        return (dest[mid / 2] + dest[mid / 2 - 1]) / 2d;
    }

    private static int insert(int[] array, int s, int e, int data) {
        if (s == e || s + 1 == e) {
            return s;
        }
        if (data == array[(s + e) / 2]) {
            return (s + e) / 2;
        }
        if (data > array[(s + e) / 2]) {
            return insert(array, (s + e) / 2, e, data);
        } else {
            return insert(array, s, (s + e) / 2, data);
        }
    }
}
