package com.littlenum;

/**
 * Created by hero on 2017/7/13.
 */
public class ZigZag {

    public static void test() {
        long start = System.currentTimeMillis();
        String result = convert("PAYPALISHIRING", 3);
        long end = System.currentTimeMillis();
        System.out.println(end - start + "\r\n" + result);
    }

    public static String convert(String s, int numRows) {
        if (s == null || s == "" || s.length() ==1 || numRows == 1) {
            return s;
        }
        int col = s.length() * numRows / (2*numRows - 2) + 1;
        char[][] ss = new char[numRows][col];
        int r = 0, c = 0;
        for (int i = 0; i < s.length(); i++) {
            if (r < numRows && r >= 0 && c % (numRows - 1) == 0) {
                ss[r++][c] = s.charAt(i);
            } else {
                c++;
                if (c % (numRows - 1) > 0) {
                    r = r == numRows ? numRows - 2 : r--;
                    ss[r--][c] = s.charAt(i);
                } else {
                    r = 0;
                    ss[r++][c] = s.charAt(i);
                }
            }
        }
        String res = "";
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < col; j++) {
                char ch = ss[i][j];
                if (ch != '\0') {
                    res += ch;
                }
            }
        }
        return res;
    }
}
