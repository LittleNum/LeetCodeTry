package com.littlenum.tree;

import com.littlenum.AbsTest;

/**
 * Created by hero on 2017/9/22.
 */
public class BinaryDepth extends AbsTest<Integer> {
    @Override
    public Integer doTest() {
        return null;
    }

    public int maxDepth(TreeNode root) {
        return value(root, 0);
    }

    private int value(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        depth++;
        return Math.max(value(root.left, depth), value(root.right, depth));
    }
}

