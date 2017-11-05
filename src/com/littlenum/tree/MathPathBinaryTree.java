package com.littlenum.tree;

import com.littlenum.AbsTest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hero on 2017/10/9.
 */
public class MathPathBinaryTree extends AbsTest<Integer> {
    private int max = 0;
    private Map<TreeNode, Integer> map = new HashMap<>();

    @Override
    public Integer doTest() {
        TreeNode node = new TreeNode(2);
        TreeNode left = new TreeNode(2);
        TreeNode l1 = new TreeNode(-6);
        l1.left = new TreeNode(-6);
        left.left = l1;
        left.right = new TreeNode(-6);
        node.left = left;
        TreeNode root = new TreeNode(1);
        root.right = node;
        root.left = new TreeNode(-4);
        return maxPathSum(root);
    }

    public int maxPathSum(TreeNode root) {
        max = root.val;
        doexec(root);
        return max;
    }

    public void doexec(TreeNode root) {
        if (root == null) {
            return;
        }
        int val = getMax(root.left) + root.val + getMax(root.right);
        if (val > max) {
            max = val;
        }
        doexec(root.left);
        doexec(root.right);
    }

    private int getMax(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (map.containsKey(node)) {
            return map.get(node);
        }
        if (node.left == null && node.right == null) {
            return Math.max(node.val, 0);
        }
        int val = Math.max(getMax(node.left), getMax(node.right)) + node.val;
        map.put(node, Math.max(val, 0));
        return val;
    }
}
