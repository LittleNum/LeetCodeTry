package com.littlenum.hash;

import com.littlenum.AbsTest;

import java.util.*;

/**
 * Created by hero on 2017/11/21.
 */
public class NumberOfAtoms extends AbsTest<String> {
    @Override
    public String doTest() {
        return countOfAtoms("(ScTh13)16Tb22C18Fl34Ag14(At41Bk4NpEsTc27Am20)3");
    }

    Map<String, Integer> map = new HashMap<>();

    public String countOfAtoms(String formula) {
        if (formula == null || formula == "") {
            return "";
        }
        for (int i = 0; i < formula.length(); ) {
            int origin = i;
            char c = formula.charAt(i);
            if (c == '(') {
                int next = findContain(formula, i);
                i = next + 1;
            } else if (c == ')') {

            } else {
                int end = findAtom(formula, i);
                int n = 1;
                int endOfNum = findNum(formula, end + 1);
                if (endOfNum >= end + 1 && endOfNum < formula.length()) {
                    n = Integer.valueOf(formula.substring(end + 1, endOfNum + 1));
                    i = endOfNum + 1;
                } else {
                    i = end + 1;
                }
                addKey(formula.substring(origin, end + 1), n);
            }
        }

        List<String> key = new ArrayList<>();
        key.addAll(map.keySet());
        Collections.sort(key, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                for (int i = 0; i < o1.length(); i++) {
                    if (o2.length() < i + 1) {
                        return 1;
                    }
                    if (o1.charAt(i) > o2.charAt(i)) {
                        return 1;
                    }
                    if (o1.charAt(i) == o2.charAt(i)) {
                        continue;
                    }
                    if (o1.charAt(i) < o2.charAt(i)) {
                        return -1;
                    }
                }
                return o2.length() == o1.length() ? 0 : -1;
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < key.size(); i++) {
            sb.append(key.get(i));
            int count = map.get(key.get(i));
            if (count > 1) {
                sb.append(count);
            }
        }
        return sb.toString();
    }

    private int findContain(String formula, int start) {
        int left = 0;
        int right = 0;
        List<Integer> levelCount = new ArrayList<>();
        List<String> arrays = new ArrayList<>();
        for (int i = start; i < formula.length(); ) {
            if (formula.charAt(i) == '(') {
                left++;
                arrays.add("(");
                i++;
            } else if (formula.charAt(i) == ')') {
                right++;
                int num = findNum(formula, i + 1);
                arrays.add(")");
                levelCount.add(Integer.valueOf(formula.substring(i + 1, num + 1)));
                if (left == right) {
                    int count = levelCount.size();
                    int index = 0;
                    int factor = 1;
                    Stack<Integer> stack = new Stack<>();
                    for (int n = arrays.size() - 1; n >= 0; n--) {
                        if (arrays.get(n).equals(")")) {
                            index++;
                            int now = levelCount.get(count - index);
                            stack.add(now);
                            factor *= now;
                        } else if (arrays.get(n).equals("(")) {
                            factor /= stack.pop();
                        } else {
                            String temp = arrays.get(n);
                            int si = findAtom(temp, 0);
                            int ci = findNum(temp, si + 1);
                            if (si + 1 == temp.length()) {
                                addKey(arrays.get(n), factor);
                            } else {
                                int nums = Integer.valueOf(temp.substring(si + 1, ci + 1));
                                addKey(temp.substring(0, si + 1), nums * factor);
                            }
                        }
                    }
                    return num;
                }
                i = num + 1;
            } else {
                int next = findAtom(formula, i);
                int endOfNum = findNum(formula, next + 1);
                arrays.add(formula.substring(i, endOfNum + 1));
                i = endOfNum + 1;
            }

        }
        return formula.length() - 1;
    }

    private void addKey(String key, int count) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + count);
        } else {
            map.put(key, count);
        }
    }

    private int findNum(String formula, int start) {
        for (int i = start; i < formula.length(); i++) {
            if (formula.charAt(i) >= '0' && formula.charAt(i) <= '9') {

            } else {
                return i - 1;
            }
        }
        return formula.length()-1;
    }

    private int findAtom(String formula, int start) {
        for (int i = start + 1; i < formula.length(); i++) {
            if (formula.charAt(i) >= 'a' && formula.charAt(i) <= 'z') {

            } else {
                return i - 1;
            }
        }
        return formula.length() - 1;
    }
}
