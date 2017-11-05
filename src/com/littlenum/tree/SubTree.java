package com.littlenum.tree;

import com.littlenum.AbsTest;

/**
 * Created by hero on 2017/9/23.
 */
public class SubTree extends AbsTest<Boolean> {
    @Override
    public Boolean doTest() {
        return false;
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.val == t.val) {
            if (isTreeSame(s, t)) {
                return true;
            }
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isTreeSame(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return s == null && t == null;
        }
        return s.val == t.val && isTreeSame(s.left, t.left) && isTreeSame(s.right, t.right);
    }
}
