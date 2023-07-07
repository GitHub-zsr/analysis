package com.zsr.analysis.util;

import java.util.ArrayList;
import java.util.List;

public class Search {
    public static String result(String text, String keyWord)
    {

        StringBuilder sb = new StringBuilder();
        String cf = null;
        List<String> strings = new ArrayList<>();
        if (text.indexOf(keyWord) >= 0)
        {
            int indexOf = 0;
            for (int j = 0; (indexOf = text.indexOf(keyWord, j)) >= 0; )
            {
                sb.append("第" + (indexOf+ 1) + "个字/");
                j = indexOf + 1;
            }
            cf = sb.toString();
            if (cf.length() > 0 && cf.endsWith("/"));
            {
                cf =  cf.substring(0, cf.length()-1);
            }
            strings.add(cf);

            sb.delete(0, sb.length());
            return (strings.toString());
        }
        return null;
    }
}
