package com.littlenum.tree;

import com.littlenum.AbsTest;

/**
 * Created by hero on 2017/9/23.
 */
public class ConstructBinaryTree extends AbsTest<String> {
    @Override
    public String doTest() {
        TreeNode t = new TreeNode(2);
        TreeNode t1 = new TreeNode(4);
        t1.right = new TreeNode(5);
        t.left = t1;
        t.right = new TreeNode(3);
        return tree2str(t);
    }

    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        String s = String.valueOf(t.val);
        if (t.left != null || t.right != null) {
            s += "(" + tree2str(t.left) + ")";
        }

        if (t.right != null) {
            s += "(" + tree2str(t.right) + ")";
        }
        return s;
    }
}
