package com.littlenum.tree;

import com.littlenum.AbsTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hero on 2017/9/29.
 */
public class DuplicateSubtree extends AbsTest<List<TreeNode>> {

    @Override
    public List<TreeNode> doTest() {
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(2);
        n1.left = n2;
        n1.right = n3;
        n2.left = new TreeNode(3);
        n2.right = new TreeNode(4);
        n3.left = new TreeNode(3);
        n3.right = new TreeNode(4);
        return findDuplicateSubtrees(n1);
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preorder(root, new HashMap<>(), list);
        return list;
    }

    private String preorder(TreeNode node, Map<String, Integer> map, List<TreeNode> nodes) {
        if (node == null) {
            return "#";
        }
        String ss = node.val + "," + preorder(node.left, map, nodes) + "," + preorder(node.right, map, nodes);
        if (map.getOrDefault(ss, 0) == 1) {
            nodes.add(node);
        }
        map.put(ss, map.getOrDefault(ss, 0) + 1);
        return ss;
    }

    private List<TreeNode> finddupicate(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return null;
        }
        List<TreeNode> temp;
        List<TreeNode> list = new ArrayList<>();
        if (issame(s, t) && !list.contains(s)) {
            list.add(s);
        }
        temp = finddupicate(s, t.left);
        contains(list, temp);
        if (temp != null) {
            list.addAll(temp);
        }
        temp = finddupicate(s, t.right);
        contains(list, temp);
        if (temp != null) {
            list.addAll(temp);
        }
        temp = finddupicate(s.left, t);
        contains(list, temp);
        if (temp != null)
            list.addAll(temp);
        temp = finddupicate(s.right, t);
        contains(list, temp);
        if (temp != null)
            list.addAll(temp);
        temp = finddupicate(s.left, s.right);
        contains(list, temp);
        if (temp != null)
            list.addAll(temp);
        temp = finddupicate(t.left, t.right);
        contains(list, temp);
        if (temp != null)
            list.addAll(temp);
        return list;
    }

    private void contains(List<TreeNode> contain, List<TreeNode> extra) {
        if (contain == null || extra == null) {
            return;
        }
        for (int i = extra.size() - 1; i >= 0; i--) {
            for (int j = 0; j < contain.size(); j++) {
                if (issame(extra.get(i), contain.get(j))) {
                    extra.remove(i);
                    break;
                }
            }
        }
    }

    private boolean issame(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return s == null && t == null;
        }
        return s.val == t.val && issame(s.left, t.left) && issame(s.right, t.right);
    }
}
