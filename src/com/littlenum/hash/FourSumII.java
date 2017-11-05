package com.littlenum.hash;

import com.littlenum.AbsTest;

import java.util.Arrays;

/**
 * Created by hero on 2017/11/5.
 */
public class FourSumII extends AbsTest<Object> {
    @Override
    public Object doTest() {
        return null;
    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (empty(A) || empty(B) || empty(C) || empty(D)) {
            return 0;
        }
        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);
        Arrays.sort(D);
        int amax = -(B[0] + C[0] + D[0]);
        int amin = -(B[B.length - 1] + C[C.length - 1] + D[D.length - 1]);
        int bmax = -(C[0] + D[0]);
        int cmax = -(D[0]);
        int bmin = -(C[C.length - 1] + D[D.length - 1]);
        int cmin = -(D[D.length - 1]);
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] < amin) {
                continue;
            }
            if (A[i] > amax) {
                break;
            }
            for (int j = 0; j < B.length; j++) {
                int second = B[j] + A[i];
                if (second < bmin) {
                    continue;
                }
                if (second > bmax) {
                    break;
                }
                for (int k = 0; k < C.length; k++) {
                    int third = second + C[k];
                    if (third < cmin) {
                        continue;
                    }
                    if (third > cmax) {
                        break;
                    }
                    int index = find(D, third, 0, D.length - 1);
                    if (index >= 0) {
                        int num = 0;
                        for (int x = index; x >= 0; x--) {
                            if (D[x] + third < 0) {
                                break;
                            }
                            num++;
                        }
                        for (int x = index + 1; x < D.length; x++) {
                            if (D[x] + third > 0) {
                                break;
                            }
                            num++;
                        }
                        count += num;
                    }
                }
            }
        }
        return count;
    }

    private int find(int[] d, int sum, int left, int right) {
        if (left == right) {
            return d[left] + sum == 0 ? left : -1;
        }
        if (left + 1 == right) {
            if (d[left] + sum == 0) {
                return left;
            }
            if (d[right] + sum == 0) {
                return right;
            }
            return -1;
        }
        int mid = (left + right) / 2;
        if (d[mid] + sum == 0) {
            return mid;
        }
        if (d[mid] + sum < 0) {
            return find(d, sum, mid, right);
        } else {
            return find(d, sum, left, mid);
        }
    }

    private boolean empty(int[] array) {
        return array == null || array.length == 0;
    }
}
