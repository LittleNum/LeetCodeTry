package com.littlenum.tree;

import com.littlenum.AbsTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hero on 2017/9/26.
 */
public class PrintBinaryTree extends AbsTest<List<List<String>>> {
    private List<List<FullNode>> mList = new ArrayList<>();
    private int mLevel;

    @Override
    public List<List<String>> doTest() {
//        TreeNode node = new TreeNode(1);
//        node.right = new TreeNode(5);
//        TreeNode left = new TreeNode(2);
//        TreeNode lleft = new TreeNode(3);
//        lleft.left = new TreeNode(4);
//        left.left = lleft;
//        node.left = left;

        TreeNode node = new TreeNode(3);
        TreeNode n1 = new TreeNode(30);
        TreeNode n2 = new TreeNode(10);
        TreeNode n3 = new TreeNode(15);
        TreeNode n4 = new TreeNode(45);
        node.right = n1;
        n1.left = n2;
        n2.right = n3;
        n3.right = n4;
        return new Solution().printTree(node);
    }

    public class FullNode {
        TreeNode node;
        FullNode left;
        FullNode right;

        public FullNode(TreeNode s) {
            this.node = s;
        }
    }

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

}
