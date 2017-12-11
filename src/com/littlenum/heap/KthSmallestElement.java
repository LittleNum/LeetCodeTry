package com.littlenum.heap;

import com.littlenum.AbsTest;

/**
 * Created by hero on 2017/12/6.
 */
public class KthSmallestElement extends AbsTest<Integer> {
    @Override
    public Integer doTest() {
        return kthSmallest(new int[][]{{1, 13, 15}, {10, 11, 13}, {12, 13, 15}}, 4);
    }

    int current;
    int[] items;

    public int kthSmallest(int[][] matrix, int k) {
        current = matrix.length * matrix.length;
        items = new int[current + 1];
        for (int i = 0; i < matrix.length; i++) {
            int[] tmp = matrix[i];
            for (int j = 0; j < tmp.length; j++) {
                items[1 + i * matrix.length + j] = matrix[i][j];
            }
        }
        for (int i = current / 2; i > 0; i--) {
            percolateDown(i);
        }
        for (int i = 0; i < k - 1 && current > 0; i++) {
            deleteMin();
        }
        return deleteMin();
    }

    private void percolateDown(int hole) {
        int tmp = items[hole];
        int child = hole;
        for (; hole * 2 <= current; hole = child) {
            child = hole * 2;
            if (child != current && items[child + 1] < items[child]) {
                child++;
            }
            if (items[child] < tmp) {
                items[hole] = items[child];
            } else {
                break;
            }
        }
        items[hole] = tmp;
    }

    private int deleteMin() {
        int min = items[1];
        items[1] = items[current--];
        percolateDown(1);
        return min;
    }
}
