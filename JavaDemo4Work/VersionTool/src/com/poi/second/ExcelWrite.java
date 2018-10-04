package com.poi.second;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;


/**
 * 写入excel
 * @author cwray
 */
public class ExcelWrite {
    public static void main(String[] args) throws Exception{
        //创建一个工作簿，在该文件中创建一个sheet
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("第一个sheet");

        //在sheet中创建一行
        HSSFRow row = sheet.createRow(0);

        //在该行写入各种类型的数据
        row.createCell(0).setCellValue(true);
        row.createCell(1).setCellValue("雷经纬");
        row.createCell(2).setCellValue(23);

        //设置保留两位小数
        //第三列，数字格式化
        HSSFCell cell = row.createCell(3);
        cell.setCellValue(6000);
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        cell.setCellStyle(cellStyle);

        //在写入日期格式的数据需要进行格式化-简单处理方式
        CreationHelper createHelper = wb.getCreationHelper();
        HSSFCellStyle style = wb.createCellStyle();
        style.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

        cell = row.createCell(4);
        cell.setCellValue(new Date());
        cell.setCellStyle(style);   //应用格式

        //最后写回磁盘

            FileOutputStream out = new FileOutputStream("/Users/cwray/Documents/GitHub/BigDataLearning/JavaDemo4Work/VersionTool/resource/excelWrite.xls");
            wb.write(out);
            out.close();

    }

}
