package com.zsr.analysis.xlsx;

import com.zsr.analysis.util.Search;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
public class XlsxAnalysis {

    public void analysis(String name, String key) throws Exception {
        File xlsx = new File(name);
        if (xlsx.length() == 0)
            return;

        InputStream fileInputStream = new FileInputStream(xlsx);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFRow row = null;
        XSSFCell cell = null;
        StringBuffer stringBuffer = new StringBuffer();

        for (int k = 0; k < workbook.getNumberOfSheets(); k++) {
            XSSFSheet sheetAt = workbook.getSheetAt(k);

            for (int i = 0; i < sheetAt.getLastRowNum() + 1; i++) {
                row = sheetAt.getRow(i);

                if (row != null) {
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        cell = row.getCell(j);
                        if (cell != null) {
                            String stringCellValue = cell.getStringCellValue();
                            String result = Search.result(stringCellValue, key);
                            stringBuffer.append(result == null ? "" : "\n\tsheet" + (k + 1) + ": 第" + (i + 1) + "行" + "第" + (j + 1) + "列" + result);
                        }

                    }
                }
            }
        }
        if (stringBuffer.toString() != "") {
            System.out.println("文件名：" + xlsx.getPath() + "\n重复位置：" + stringBuffer.toString());
        }
    }
}
