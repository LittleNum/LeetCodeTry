package com.littlenum.tree;

import com.littlenum.AbsTest;

/**
 * Created by hero on 2017/9/23.
 */
public class MaxiumTree extends AbsTest<TreeNode> {
    @Override
    public TreeNode doTest() {
        return constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int mid = getMaxIndex(nums);
        TreeNode t = new TreeNode(nums[mid]);
        if (mid > 0) {
            int[] left = new int[mid];
            System.arraycopy(nums, 0, left, 0, mid);
            t.left = constructMaximumBinaryTree(left);
        }
        if (mid < nums.length - 1) {
            int[] right = new int[nums.length - mid - 1];
            System.arraycopy(nums, mid + 1, right, 0, nums.length - mid - 1);
            t.right = constructMaximumBinaryTree(right);
        }
        return t;
    }

    private int getMaxIndex(int[] nums) {
        int s = nums[0];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > s) {
                s = nums[i];
                index = i;
            }
        }
        return index;
    }
}
