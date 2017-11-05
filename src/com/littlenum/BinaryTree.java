package com.littlenum;

/**
 * Created by hero on 2017/9/19.
 */
public class BinaryTree extends AbsTest<Integer> {
    TreeNode TreeNode;

    public BinaryTree() {
        TreeNode = new TreeNode();
        TreeNode.data = 2;
        TreeNode.left = new TreeNode(2, null, null);
        TreeNode.right = new TreeNode(2, new TreeNode(2, null, null), new TreeNode(2, null, null));
    }

    @Override
    public Integer doTest() {
        if (TreeNode == null) {
            return -1;
        }
        int min = TreeNode.data;
        int second = findMin(TreeNode, TreeNode.data);
        if (second >= 0 && second > min) {
            return second;
        }
        return -1;
    }

    private int findMin(TreeNode TreeNode, int x) {
        if (TreeNode == null) {
            return -1;
        }
        if (TreeNode.data != x) {
            return TreeNode.data;
        }
        int l = findMin(TreeNode.left, x);
        int r = findMin(TreeNode.right, x);
        if (l <= x) {
            return r;
        }
        if (r <= x) {
            return l;
        }
        return Math.min(l, r);
    }

    public class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode() {

        }

        public TreeNode(int d, TreeNode l, TreeNode r) {
            this.data = d;
            this.left = l;
            this.right = r;
        }
    }
}
