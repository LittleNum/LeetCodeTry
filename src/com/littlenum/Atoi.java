package com.littlenum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hero on 2017/7/25.
 */
public class Atoi<T> extends AbsTest {
    @Override
    public Integer doTest() {
        return myAtoi("    -10522545459");
    }

    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        str = str.trim();
        String p = "^([+-]?[\\d]+[.]?[\\d]*)";
        Matcher matcher = Pattern.compile(p).matcher(str);
        if (!matcher.find()) {
            return 0;
        }
        str = matcher.group(0);
        int index = 0;
        int sum = 1;
        if (str.startsWith("+") || str.startsWith("-")) {
            index = 1;
            sum = str.startsWith("-") ? -1 : 1;
        }
        String remain = str.substring(index);
        String[] array = remain.split("\\.");
        int answer;
        if (array.length == 2) {
            answer = sum * getInt(array[0],sum==1);
        }else {
            answer = sum * getInt(remain,sum==1);
        }
        if (answer * sum >= 0) {
            return answer;
        } else {
            return sum == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
    }

    public int getInt(String str ,boolean flag) {
        int sum = 0;
        if(str.length()>10){
            return flag?Integer.MAX_VALUE:Integer.MIN_VALUE;
        }
        for (int i = 0; i < str.length(); i++) {
            sum = sum * 10 + Integer.valueOf(str.substring(i, i + 1));
            if(i==8 ){
                if(sum * 10 - Integer.MIN_VALUE /10* 10 > 7){
                    return flag?Integer.MAX_VALUE:Integer.MIN_VALUE;
                }
            }
        }
        return sum;
    }
}
