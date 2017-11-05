package com.littlenum.tree;

import com.littlenum.AbsTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hero on 2017/9/25.
 */
public class LargestRowValue extends AbsTest<List> {
    List<Integer> list = new ArrayList<>();

    @Override
    public List doTest() {
        return null;
    }

    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return list;
        }
        list.add(root.val);
        findLargest(root.left, 1);
        findLargest(root.right, 1);
        return list;
    }

    private void findLargest(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (list.size() == level) {
            list.add(node.val);
        } else {
            if (list.get(level) < node.val) {
                list.set(level, node.val);
            }
        }
        findLargest(node.left, level + 1);
        findLargest(node.right, level + 1);
    }
}
