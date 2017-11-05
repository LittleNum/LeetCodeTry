package com.littlenum.tree;

import com.littlenum.AbsTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hero on 2017/10/12.
 */
public class RecoverBst extends AbsTest{
    private TreeNode node1,node2;
    @Override
    public TreeNode doTest() {
        TreeNode node = new TreeNode(3);
        TreeNode node1 = new TreeNode(2);
        node.right = node1;
        node1.right = new TreeNode(1);
        recoverTree(node);
        return node;
    }

    public void recoverTree(TreeNode root) {
        find(root);
        int val = node1.val;
        node1.val = node2.val;
        node2.val = val;
    }

    private boolean find(TreeNode node){
        if(node == null){
            return false;
        }
        List<TreeNode> big = new ArrayList<>();
        List<TreeNode> small = new ArrayList<>();
        bigNode(node.left,node.val,big);
        smallNode(node.right,node.val,small);
        if(big.size() >1){
            node1 = node;
            int s = 0;
            for(int i=1;i<big.size();i++){
                if(big.get(i).val< big.get(0).val){
                    s = i;
                }
            }
            node2 = big.get(s);
            return true;
        }
        if(small.size() > 1){
            node1 = node;
            int s = 0;
            for(int i=1;i<big.size();i++){
                if(big.get(i).val > big.get(0).val){
                    s = i;
                }
            }
            node2 = small.get(s);
            return true;
        }
        TreeNode left = big.size() >0 ?big.get(0):null;
        TreeNode right = small.size()>0?small.get(0):null;
        if(left != null && right != null){
            node1 = left;
            node2 = right;
            return true;
        }else if(left != null){
            node1 = node;
            node2 = left;
            return true;
        }else if(right != null){
            node1 = node;
            node2 = right;
            return true;
        }
        return find(node.left) || find(node.right);
    }

    private TreeNode bigNode(TreeNode node, int val, List<TreeNode> list){
        if(node== null){
            return  null;
        }
        if(node.val > val){
            list.add(node);
        }
        TreeNode left = bigNode(node.left,val,list);
        if(left != null){
            list.add(left);
        }
        return bigNode(node.right,val,list);
    }

    private TreeNode smallNode(TreeNode node,int val, List<TreeNode> list){
        if(node== null){
            return null;
        }
        if(node.val < val){
            list.add(node);
        }
        TreeNode left = smallNode(node.left,val,list);
        if(left != null){
            list.add(left);
        }
        return smallNode(node.right,val,list);
    }
}
