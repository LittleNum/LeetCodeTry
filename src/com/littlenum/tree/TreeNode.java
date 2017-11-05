package com.littlenum.tree;

/**
 * Created by hero on 2017/9/22.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return val + " " + (left != null ? left.toString() : "^") + " " + (right != null ? right.toString() : "^");
    }
}
