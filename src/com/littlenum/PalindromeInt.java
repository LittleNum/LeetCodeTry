package com.littlenum;

/**
 * Created by hero on 2017/8/15.
 */
public class PalindromeInt extends AbsTest<Boolean> {

    @Override
    public Boolean doTest() {
        return isPalindrome(121);
    }

    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        int size = 1;
        int temp = x;
        while (x / 10 != 0) {
            size++;
            x = x / 10;
        }
        if (size <= 1) {
            return true;
        }
        int s;
        if (size % 2 == 0) {
            s = size / 2 - 1;

        } else {
            s = (size - 1) / 2 - 1;
        }
        for (int i = 0; i <= s; i++) {
            if (getValue(temp, i) != getValue(temp, size - i - 1)) {
                return false;
            }
        }
        return true;
    }

    private int getValue(int x, int n) {
        for (int i = 0; i < n; i++) {
            x = x / 10;
        }
        return x % 10;
    }
}
