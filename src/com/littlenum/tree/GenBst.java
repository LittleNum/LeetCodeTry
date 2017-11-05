package com.littlenum.tree;

import com.littlenum.AbsTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hero on 2017/10/9.
 */
public class GenBst extends AbsTest<List<TreeNode>> {
    @Override
    public List<TreeNode> doTest() {
        return generateTrees(3);
    }

    public List<TreeNode> generateTrees(int n) {
        return genTree(1, n);
    }

    private List<TreeNode> genTree(int start, int end) {
        if (start < 1 || start > end) {
            return null;
        }
        List<TreeNode> nodes = new ArrayList<>();
        if (start == end) {
            nodes.add(getNode(start, null, null));
            return nodes;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = genTree(start, i - 1);
            List<TreeNode> rights = genTree(i + 1, end);
            int l = lefts != null ? lefts.size() : 0;
            int r = rights != null ? rights.size() : 0;
            if (l > 0 && r > 0) {
                for (int a = 0; a < l; a++) {
                    for (int b = 0; b < r; b++) {
                        nodes.add(getNode(i, lefts.get(a), rights.get(b)));
                    }
                }
            } else {
                if (l > 0)
                    for (int a = 0; a < l; a++) {
                        nodes.add(getNode(i, lefts.get(a), null));
                    }
                if (r > 0)
                    for (int b = 0; b < r; b++) {
                        nodes.add(getNode(i, null, rights.get(b)));
                    }
            }
        }
        return nodes;
    }

    private TreeNode getNode(int v, TreeNode l, TreeNode r) {
        TreeNode node = new TreeNode(v);
        node.left = l;
        node.right = r;
        return node;
    }

}
