package com.littlenum;

import java.util.regex.Pattern;

/**
 * Created by hero on 2017/8/16.
 */
public class Matcher extends AbsTest<Boolean> {
    @Override
    public Boolean doTest() {
        return isMatch2("ab", ".*c");
    }

    public boolean isMatch(String s, String p) {
        if(s== null || s.length()==0){
            return true;
        }
        String ps = "\\*\\*+";
        java.util.regex.Matcher matcher = Pattern.compile(ps).matcher(p);
        if (matcher.find()) {
            String ss = matcher.group();
            String haha = "";
            for (int n = 0; n < ss.length(); n++) {
                haha = haha + "\\*";
            }
            p = p.replaceAll(haha, "\\*");
        }
        while (true) {
            String temp = p;
            int start = temp.indexOf(s.charAt(0));
            if (start < 0) {
                start = temp.indexOf(".");
                if(start >=0){
                    if(temp.indexOf(".*") == start){
                        temp = temp.replaceFirst("\\.", String.valueOf(s.charAt(0))+".");
                    }else{
                        temp = temp.replaceFirst("\\.", String.valueOf(s.charAt(0)));
                    }
                }
            }
            if (start < 0) {
                return false;
            }
            char first = temp.charAt(start);
            temp = temp.substring(++start);
            int last = 0;
            int i = 1;
            for (i = 1; i < s.length(); i++) {
                int index = temp.indexOf(s.charAt(i));
                if (index >= 0) {
                    if (index == last) {

                    } else {
                        for (int d = last ; d < index; d++) {
                            if (temp.charAt(d) != '*') {
                                break;
                            }
                        }
                    }
                } else {
                    if (temp.indexOf(".") == last ) {
                        if(temp.indexOf(".*") == last){
                            temp = temp.replaceFirst("\\.", String.valueOf(s.charAt(i))+".");
                        }else{
                            temp = temp.replaceFirst("\\.", String.valueOf(s.charAt(i)));
                        }
                    } else if (temp.indexOf("*") == last) {
                        if (first != s.charAt(i)) {
                            break;
                        }
                    }else {
                        break;
                    }
                }
                first = temp.charAt(0);
                temp = temp.substring(1);
            }
            if (i == s.length()) {
                return true;
            }
            if (start < p.length()) {
                p = p.substring(start);
            } else {
                return false;
            }
        }
    }

    public boolean isMatch2(String s, String p) {

        if(p.length() == 0)
            return s.length() == 0;

        //p's length 1 is special case
        if(p.length() == 1 || p.charAt(1) != '*'){
            if(s.length() < 1 || (p.charAt(0) != '.' && s.charAt(0) != p.charAt(0)))
                return false;
            return isMatch2(s.substring(1), p.substring(1));

        }else{
            int len = s.length();

            int i = -1;
            while(i<len && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))){
                if(isMatch2(s.substring(i+1), p.substring(2)))
                    return true;
                i++;
            }
            return false;
        }
    }
}
