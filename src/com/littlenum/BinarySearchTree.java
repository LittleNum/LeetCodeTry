package com.littlenum;

/**
 * Created by hero on 2017/9/20.
 */
public class BinarySearchTree extends AbsTest<BinarySearchTree.TreeNode> {

    @Override
    public TreeNode doTest() {
        TreeNode root = new TreeNode(3);
        TreeNode n2 = new TreeNode(2);
        n2.left = new TreeNode(1);
        TreeNode n0 = new TreeNode(0);
        n0.right = n2;
        root.left = n0;
        root.right = new TreeNode(4);
        TreeNode node = trimBST(root, 1, 3);
        return node;
    }

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }
        if (root.val == L) {
            root.left = null;
            root.right = trimBST(root.right, L, R);
        } else if (root.val == R) {
            root.right = null;
            root.left = trimBST(root.left, L, R);
        } else if (root.val < L) {
            return trimBST(root.right, L, R);
        } else if (root.val > R) {
            return trimBST(root.left, L, R);
        } else {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
        }
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
