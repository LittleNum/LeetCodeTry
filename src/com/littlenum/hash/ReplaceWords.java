package com.littlenum.hash;

import com.littlenum.AbsTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hero on 2017/11/5.
 * 648. Replace Words
 */
public class ReplaceWords extends AbsTest<String> {
    @Override
    public String doTest() {
        List<String> dict = new ArrayList<>();
        dict.add("cat");
        dict.add("bat");
        dict.add("rat");
        String sentence = "the cattle was rattled by the battery";
        return replaceWords(dict, sentence);
    }

    public String replaceWords(List<String> dict, String sentence) {
        if (dict == null || dict.size() == 0 || sentence == null || sentence == "") {
            return sentence;
        }
        Set<String> set = new HashSet<>();
        int min = 0;
        int max = 0;
        for (int i = 0; i < dict.size(); i++) {
            String s = dict.get(i);
            if (i == 0) {
                min = s.length();
                max = min;
            }
            if (s.length() < min) {
                min = s.length();
            }
            if (s.length() > max) {
                max = s.length();
            }
            set.add(s);
        }
        String[] sentences = sentence.split(" ");
        for (int i = 0; i < sentences.length; i++) {
            String s = sentences[i];
            for (int j = min; j <= max && j <= s.length(); j++) {
                String key = s.substring(0, j);
                if (set.contains(key)) {
                    sentences[i] = key;
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentences.length - 1; i++) {
            sb.append(sentences[i]);
            sb.append(" ");
        }
        sb.append(sentences[sentences.length - 1]);
        return sb.toString();
    }
}
