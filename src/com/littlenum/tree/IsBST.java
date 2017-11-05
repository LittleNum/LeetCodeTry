package com.littlenum.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hero on 2017/9/28.
 */
public class IsBST {

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidLeft(root.left, root.val) && isValidRight(root.right, root.val);
    }

    private boolean isValidLeft(TreeNode root, int val) {
        return root == null || isSmall(root, val) && isValidLeft(root.left, root.val) && isValidRight(root.right, root.val);
    }

    private boolean isSmall(TreeNode node, int val) {
        if (node == null) {
            return true;
        }
        return node.val < val && isSmall(node.left, val) && isSmall(node.right, val);
    }

    private boolean isBig(TreeNode node, int val) {
        if (node == null) {
            return true;
        }
        return node.val > val && isBig(node.left, val) && isBig(node.right, val);
    }

    private boolean isValidRight(TreeNode root, int val) {
        return root == null || isBig(root, val) && isValidLeft(root.left, root.val) && isValidRight(root.right, root.val);
    }
}
