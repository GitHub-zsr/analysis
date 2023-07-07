package com.zsr.analysis.file;

import com.zsr.analysis.util.ConstantClass;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

public class GetFilePath {

    public String filePath;

    public String fileKeyWord;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileKeyWord() {
        return fileKeyWord;
    }

    public void setFileKeyWord(String fileKeyWord) {
        this.fileKeyWord = fileKeyWord;
    }

    public void input() throws Exception {

        String string = ResourceBundle.getBundle("application").getString("filePath");
        String string1 = ResourceBundle.getBundle("application").getString("keyword");

        this.filePath = string;
        this.fileKeyWord = string1;

        File file = new File(this.filePath);
        searchFile(file, this.fileKeyWord);
    }

    public static void searchFile(File f, String keyword) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        String name = f.getName();
        if (name.endsWith(ConstantClass.INPUT_TYPE_TXT) || name.endsWith(ConstantClass.INPUT_TYPE_DOC) ||
                name.endsWith(ConstantClass.INPUT_TYPE_EXCEL)) {
            String typeName = f.getName().substring(f.getName().lastIndexOf(".") + 1);

            //拼接自定义解析文件类的类名
            String className = "com.zsr.analysis." + typeName + "." + typeName.substring(0,1).toUpperCase() + typeName.substring(1).toLowerCase() + "Analysis";

            Class<?> aClass = Class.forName(className);
            Object o = aClass.newInstance();
            Method analysisMethod = aClass.getMethod("analysis", String.class, String.class);
            analysisMethod.invoke(o, f.getPath(), keyword);
        }else {
            File[] files = f.listFiles();
            if (files != null)
            for (File file: files)
            {
                searchFile(file, keyword);
            }
        }
    }
}
