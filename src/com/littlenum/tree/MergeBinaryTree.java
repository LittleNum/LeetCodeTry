package com.littlenum.tree;

import com.littlenum.AbsTest;

/**
 * Created by hero on 2017/9/22.
 */
public class MergeBinaryTree extends AbsTest<TreeNode> {
    @Override
    public TreeNode doTest() {
        TreeNode n1 = new TreeNode(1);
        n1.left = new TreeNode(2);
        n1.right = new TreeNode(3);

        TreeNode n2 = new TreeNode(2);
        n2.left = new TreeNode(3);
        n2.right = new TreeNode(9);
        return mergeTrees(n1, n2);
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 != null && t2 != null) {
            TreeNode temp = new TreeNode(t1.val + t2.val);
            temp.left = mergeTrees(t1.left, t2.left);
            temp.right = mergeTrees(t1.right, t2.right);
            return temp;
        } else {
            return t1 == null ? t2 : t1;
        }
    }
}
