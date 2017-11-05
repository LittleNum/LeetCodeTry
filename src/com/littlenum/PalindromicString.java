package com.littlenum;

import java.util.Arrays;

/**
 * Created by hero on 2017/7/12.
 */
public class PalindromicString {
    public static void main(String[] args) {
        // write your code here
        System.out.println(longestPalindrome("aba"));
    }

    public static void test() {
        long start = System.currentTimeMillis();

        String s = findLongest("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        long end = System.currentTimeMillis();
        System.out.println(end - start + "\r\n" + s);
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        String max = "";
        for (int i = 0; i < s.length(); i++) {
            int end = s.lastIndexOf(s.charAt(i));
            while (end >= i) {
                boolean find = true;
                for (int j = i; j <= end; j++) {
                    if (s.charAt(j) != s.charAt(end - (j - i))) {
                        find = false;
                        break;
                    }
                }
                if (find) {
                    String temp = s.substring(i, end + 1);
                    if (temp.length() > max.length())
                        max = temp;
                }
                if (end == i) {
                    break;
                }
                end = s.substring(i, end - 1).lastIndexOf(s.charAt(i));
            }
        }
        return max;
    }

    public static String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        String max = "";
        int lastI = -1;
        for (int i = 0; i < s.length(); i++) {
            int end = s.lastIndexOf(s.charAt(i));
            while (end >= i && lastI != i) {
                boolean find = true;
                for (int j = i; j <= end; j++) {
                    if (s.charAt(j) != s.charAt(end - (j - i))) {
                        find = false;
                        break;
                    }
                }
                if (find) {
                    String temp = s.substring(i, end + 1);
                    if (temp.length() > max.length()) {
                        lastI = i;
                        max = temp;
                    }

                }
                if (end == i) {
                    break;
                }
                end = s.substring(0, end).lastIndexOf(s.charAt(i));
            }
        }
        return max;
    }

    public static String longestPalindrome3(String s) {
        if (s.isEmpty()) {
            return null;
        }
        if (s.length() == 1) {
            return s;
        }
        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            // get longest palindrome with center of i
            String tmp = helper(s, i, i);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }

            // get longest palindrome with center of i, i+1
            tmp = helper(s, i, i + 1);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }
        }
        return longest;
    }

    // Given a center, either one letter or two letter,
    // Find longest palindrome
    public static String helper(String s, int begin, int end) {
        while (begin >= 0 && end <= s.length() - 1
                && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        String subS = s.substring(begin + 1, end);
        return subS;
    }

    public static String findLongest(String s) {
        if (s == null || s == "") {
            return s;
        }
        String max = "";
        for (int i = 0; i < s.length(); i++) {
            String x = temp(s, i, i);
            String y = temp(s, i, i + 1);
            String z = x.length() > y.length() ? x : y;
            max = z.length() > max.length() ? z : max;
        }
        return max;
    }

    private static String temp(String s, int begin, int end) {
        if (s == null || s == "") {
            return s;
        }
        while (begin >= 0 && end < s.length()) {
            if (s.charAt(begin--) != s.charAt(end++)) {
                break;
            }
        }
        return s.substring(begin + 1, end);
    }

}
