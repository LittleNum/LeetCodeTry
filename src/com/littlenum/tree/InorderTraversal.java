package com.littlenum.tree;

import com.littlenum.AbsTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hero on 2017/9/28.
 */
public class InorderTraversal extends AbsTest<List<Integer> >{
    @Override
    public List<Integer>  doTest() {
        return new ArrayList<Integer>();
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if(root== null){
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        list.addAll(inorderTraversal(root.left));
        list.add(root.val);
        list.addAll(inorderTraversal(root.right));
        return list;
    }
}
