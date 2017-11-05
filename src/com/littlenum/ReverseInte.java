package com.littlenum;

/**
 * Created by hero on 2017/7/16.
 */
public class ReverseInte<T> extends AbsTest {

    public Integer doTest() {
        int s = reverse(-2147483412);
        java.lang.Integer i = new java.lang.Integer(s);
        return i;
    }

    public static int reverse(int x) {
        int[] result = getIntSize(x);
        int size = result.length;
        int mid = size % 2 == 0 ? size / 2 : size / 2 + 1;
        for (int i = 0; i < mid; i++) {
            int temp = result[i];
            result[i] = result[size - 1 - i];
            result[size - 1 - i] = temp;
        }
        int[] max = getIntSize((int) Math.pow(2, 31) - 1);
        int[] min = getIntSize((int) -Math.pow(2, 31));
        if (result.length > max.length) {
            return 0;
        }
        if (x > 0 && result.length >= max.length) {
            for (int i = 0; i < max.length; i++) {
                if (result[i] > max[i]) {
                    return 0;
                } else if (result[i] == max[i]) {

                } else {
                    break;
                }
            }
        }
        if (x < 0 && result.length == min.length) {
            for (int i = 0; i < min.length; i++) {
                if (result[i] < min[i]) {
                    return 0;
                } else if (result[i] == min[i]) {

                } else {
                    break;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < result.length; i++) {
            sum *= 10;
            sum += result[i];
        }
        return sum;
    }

    private static int[] getIntSize(int x) {
        int y = x;
        int size = 1;
        while (x / 10 != 0) {
            size++;
            x /= 10;
        }

        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[size - i - 1] = y % 10;
            y /= 10;
        }
        return result;
    }
}
