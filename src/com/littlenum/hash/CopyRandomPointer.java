package com.littlenum.hash;

import com.littlenum.AbsTest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hero on 2017/11/7.
 * 138.Copy List With Random Pointer
 */
public class CopyRandomPointer extends AbsTest<Object> {
    @Override
    public Object doTest() {
        RandomListNode node1 = new RandomListNode(1);
        RandomListNode node2 = new RandomListNode(2);
        RandomListNode node3 = new RandomListNode(3);
        RandomListNode node4 = new RandomListNode(4);
        node1.next = node2;
        node1.random = node2;
        node2.next = null;
        node2.random = node1;
//        node3.next = node4;
//        node3.random = node1;
//        node4.next = null;
//        node4.random = node2;
        return copyRandomList(node1);
    }

    Map<RandomListNode, RandomListNode> map = new HashMap<>();

    public RandomListNode copyRandomList(RandomListNode head) {
        copy(head);
        return map.get(head);
    }

    public void copy(RandomListNode head) {
        if (head == null) {
            return;
        }
        RandomListNode cursor = head;
        while (cursor != null) {
            RandomListNode temp = map.get(cursor);
            if (temp == null) {
                temp = new RandomListNode(cursor.label);
                map.put(cursor, temp);
            }
            if (cursor.next != null && temp.next == null) {
                RandomListNode next = map.get(cursor.next);
                if (next == null) {
                    temp.next = copyRandomList(cursor.next);
                } else {
                    temp.next = next;
                }
            }
            if (cursor.random != null && temp.random == null) {
                RandomListNode random = map.get(cursor.random);
                if (random == null) {
                    temp.random = copyRandomList(cursor.random);
                } else {
                    temp.random = random;
                }
            }
            cursor = cursor.next;
        }
    }
}
