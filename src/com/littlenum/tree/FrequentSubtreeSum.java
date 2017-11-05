package com.littlenum.tree;

import com.littlenum.AbsTest;

import java.util.*;

/**
 * Created by hero on 2017/9/25.
 */
public class FrequentSubtreeSum extends AbsTest<int[]> {
    private Map<Integer, Integer> map = new HashMap<>();

    @Override
    public int[] doTest() {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(2);
        node.right = new TreeNode(-5);
        return findFrequentTreeSum(node);
    }

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        findFrequent(root);
        Set<Map.Entry<Integer, Integer>> set = map.entrySet();
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : set) {
            if (list.isEmpty() && entry.getValue() > 0) {
                list.add(entry.getKey());
            } else {
                if (entry.getValue() > map.get(list.get(list.size() - 1))) {
                    list.clear();
                    list.add(entry.getKey());
                } else if (entry.getValue().equals(map.get(list.get(list.size() - 1)))) {
                    list.add(entry.getKey());
                }
            }
        }
        int[] x = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            x[i] = list.get(i);
        }
        return x;
    }

    private void findFrequent(TreeNode node) {
        if (node == null) {
            return;
        }
        int sum = sum(node);
        if (map.containsKey(sum)) {
            map.put(sum, map.get(sum) + 1);
        } else {
            map.put(sum, 1);
        }
        findFrequent(node.left);
        findFrequent(node.right);
    }

    private int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = root.val;
        sum += sum(root.left);
        sum += sum(root.right);
        return sum;
    }
}
