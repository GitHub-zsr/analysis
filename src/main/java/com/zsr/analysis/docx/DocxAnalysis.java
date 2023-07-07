package com.zsr.analysis.docx;

import com.zsr.analysis.util.Search;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DocxAnalysis {

    public void analysis(String name, String key) {
        try {
            File docx = new File(name);
            if (docx.length() == 0)
                return;
            FileInputStream fis = new FileInputStream(docx);
            XWPFDocument document = new XWPFDocument(fis);

            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("\n\t正文");

            // 遍历文档中的段落
            int i = 0;
            for (XWPFParagraph paragraph : document.getParagraphs()) {

                i++;
                String text = paragraph.getText();
                String result = Search.result(text, key);
                stringBuffer.append(result == null ? "" : "\n\t\t段落" + i + result);
            }


            stringBuffer.append("\t");
            // 遍历文档中的表格
            int biao = 0;
            for (XWPFTable table : document.getTables()) {
                biao++;

                int hang = 0;
                for (XWPFTableRow row : table.getRows()) {
                    hang++;
                    int lie = 0;
                    for (XWPFTableCell cell : row.getTableCells()) {
                        lie++;

                        String text = cell.getText();
                        String result = Search.result(text, key);
                        stringBuffer.append(result == null ? "" : "\n\t表格" + biao + ": 第" + hang + "行" + "第" + lie + "列" + result);
                    }
                }
            }
            System.out.println("文件名:" + name + "。\n重复位置：" + stringBuffer.toString());
            fis.close();
        } catch (IOException e) {
            System.out.println("操作Word文档时发生错误: " + e.getMessage());
        }
    }
}
