package com.zsr.analysis.txt;

import com.zsr.analysis.util.Search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TxtAnalysis {

    public void analysis(String name, String key) throws Exception {
        File txt = new File(name);
        if (txt.length() == 0)
            return;

        FileInputStream fileInputStream = new FileInputStream(txt);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder sb = new StringBuilder();
        String read = null;

        List<String> strings = new ArrayList<>();

        for (int i = 1; (read = bufferedReader.readLine()) != null; i++) {
            String result = Search.result(read, key);
            sb.append(result == null ? "" : "\n\t\t行" + i + result);
        }
        System.out.println("文件名:" + txt.getName() + "。\n重复位置：" + sb.toString());
    }
}

