package com.littlenum.tree;

import com.littlenum.AbsTest;

/**
 * Created by hero on 2017/9/22.
 */
public class BstSum extends AbsTest<Boolean> {
    @Override
    public Boolean doTest() {
        return null;
    }

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (findValue(root.left, k - root.val) || findValue(root.right, k - root.val)) {
            return true;
        } else {
            if (root.left == null) {
                return findTarget(root.right, k);
            } else if (root.right == null) {
                return findTarget(root.left, k);
            } else {
                return findTargetValue(root.left, root.right, k) || findTarget(root.right, k) || findTarget(root.left, k);
            }
        }
    }

    private boolean findTargetValue(TreeNode node1, TreeNode node2, int k) {
        if (node1 == null || node2 == null) {
            return false;
        }
        if (findValue(node2, k - node1.val)) {
            return true;
        }
        return findTargetValue(node1.left, node2, k) || findTargetValue(node1.right, node2, k);
    }

    private boolean findValue(TreeNode node, int val) {
        if (node == null) {
            return false;
        }
        if (val == node.val) {
            return true;
        } else if (val > node.val) {
            return findValue(node.right, val);
        } else {
            return findValue(node.left, val);
        }
    }
}
