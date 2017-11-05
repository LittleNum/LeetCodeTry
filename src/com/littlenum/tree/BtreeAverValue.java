package com.littlenum.tree;

import com.littlenum.AbsTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hero on 2017/9/22.
 */
public class BtreeAverValue extends AbsTest<List> {

    private Map<Integer, Value> map = new HashMap<>();

    @Override
    public List doTest() {
        TreeNode node = new TreeNode(2);
        TreeNode left = new TreeNode(3);
        left.left = new TreeNode(4);
        left.right = new TreeNode(5);
        node.left = left;

        TreeNode right = new TreeNode(11);
        right.left = new TreeNode(6);
        right.right = new TreeNode(2);

        node.right = right;
        return averageOfLevels(node);
    }

    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return null;
        }
        map.clear();
        getValue(root, 0);
        List<Double> list = new ArrayList<>();
        int start = 0;
        while (map.containsKey(start)) {
            Value v = map.get(start);
            list.add(v.value / v.count);
            start++;
        }
        return list;
    }

    private void getValue(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (map.containsKey(level)) {
            Value v = map.get(level);
            v.count = v.count + 1;
            v.value = v.value + root.val;
        } else {
            Value v = new Value();
            v.count = 1;
            v.value = root.val;
            map.put(level, v);
        }
        getValue(root.left, level + 1);
        getValue(root.right, level + 1);
    }

    private class Value {
        int count;
        double value;
    }
}
