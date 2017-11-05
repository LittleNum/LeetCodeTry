package com.littlenum.tree;

import com.littlenum.AbsTest;

/**
 * Created by hero on 2017/9/23.
 */
public class LeftMostTree extends AbsTest<Integer> {
    private int mLevel = 0;
    private int mLargest = Integer.MIN_VALUE;

    @Override
    public Integer doTest() {
        return null;
    }

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        mLevel = 0;
        mLargest = root.val;
        findmax(root, 0);
        return mLargest;
    }

    private void findmax(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            if (level + 1 >= mLevel) {
                mLevel = level + 1;
                mLargest = node.left.val;
            }
        } else if (node.right != null) {
            if (level + 1 >= mLevel) {
                mLevel = level + 1;
                mLargest = node.right.val;
            }
        }
        findmax(node.left, level + 1);
        int temp = mLevel;
        int target = mLargest;
        findmax(node.right, level + 1);
        if (mLevel <= temp) {
            mLevel = temp;
            mLargest = target;
        }
    }

}
