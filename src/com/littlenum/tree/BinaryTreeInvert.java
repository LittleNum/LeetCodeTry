package com.littlenum.tree;

import com.littlenum.AbsTest;

/**
 * Created by hero on 2017/9/22.
 */
public class BinaryTreeInvert extends AbsTest<TreeNode>{
    @Override
    public TreeNode doTest() {

        return null;
    }

    public TreeNode invertTree(TreeNode root) {
        if(root!= null){
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }
}
