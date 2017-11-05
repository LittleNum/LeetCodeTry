package com.littlenum.tree;

import com.littlenum.AbsTest;

/**
 * Created by hero on 2017/9/23.
 */
public class ConvertSortBst extends AbsTest<TreeNode> {
    @Override
    public TreeNode doTest() {
        return sortedArrayToBST(new int[]{1, 2, 3, 5, 6});
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int mid = nums.length / 2;
        TreeNode node = new TreeNode(nums[mid]);
        if (mid > 0) {
            int[] left = new int[mid];
            System.arraycopy(nums, 0, left, 0, mid);
            node.left = sortedArrayToBST(left);
        }
        if (mid < nums.length - 1) {
            int[] right = new int[nums.length - mid - 1];
            System.arraycopy(nums, mid + 1, right, 0, nums.length - mid - 1);
            node.right = sortedArrayToBST(right);
        }
        return node;
    }
}
