package com.littlenum.tree;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<List<FullNode>> mList = new ArrayList<>();
    private int mLevel;

    private FullNode fullTree(TreeNode node, int level) {
        if (level >= mLevel) {
            return null;
        }
        FullNode fl = new FullNode(node);
        fl.left = fullTree(node != null ? node.left : null, level + 1);
        fl.right = fullTree(node != null ? node.right : null, level + 1);
        return fl;
    }

    private int getdepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getdepth(node.left), getdepth(node.right));
    }


    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> result = new ArrayList<>();
        mLevel = getdepth(root);
        FullNode node = fullTree(root, 0);
        getAllTree(node, 0);
        int size = mList.size() - 1;
        int count = (int) Math.pow(2, size) - 1;
        for (int i = 0; i < size; i++) {
            List<FullNode> nodes = mList.get(i);
            List<String> list = new ArrayList<>();
            if (i == 0) {
                list.addAll(printEmpty(count / 2));
                list.add(getNodeVal(nodes.get(0)));
                list.addAll(printEmpty(count / 2));
            } else {
                int n = (int) Math.pow(2, i);
                int space = count / (n * 2);
                for (int j = 0; j < n; j++) {
                    list.addAll(printEmpty(space));
                    list.add(getNodeVal(j < nodes.size() ? nodes.get(j) : null));
                    list.addAll(printEmpty(space));
                    if (j < n - 1) {
                        list.add("");
                    }
                }
            }
            result.add(list);
        }
        return result;
    }

    private String getNodeVal(FullNode node) {
        if (node == null) {
            return "";
        }
        return node.node != null ? String.valueOf(node.node.val) : "";
    }

    private List<String> printEmpty(int size) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add("");
        }
        return list;
    }

    private void getAllTree(FullNode root, int level) {
        if (mList.size() > level && mList.get(level) != null) {
            mList.get(level).add(root);
        } else {
            List<FullNode> temp = new ArrayList<>();
            temp.add(root);
            if (mList.size() <= level) {
                for (int i = mList.size() - 1; i < level; i++) {
                    mList.add(new ArrayList<>());
                }
            }
            mList.set(level, temp);
        }
        if (root != null) {
            getAllTree(root.left, level + 1);
            getAllTree(root.right, level + 1);
        }
    }

    public class FullNode {
        TreeNode node;
        FullNode left;
        FullNode right;

        public FullNode(TreeNode s) {
            this.node = s;
        }
    }
}