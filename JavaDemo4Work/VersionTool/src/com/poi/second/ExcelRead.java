package com.poi.second;

import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 读取Excel
 * @author cwray
 */
public class ExcelRead {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    /**
     * 判断Excel的版本，获取Workbook
     * @param in 输入流
     * @param file 文件
     * @return 返回workbook对象（根据excel版本区分）
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream in, File file) throws IOException{
        Workbook wb = null;
        if(file.getName().endsWith(EXCEL_XLS)){
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    /**
     * 判断文件是否是excel
     * @param file
     * @throws Exception
     */
    public static void checkExcelValid(File file) throws Exception{
        if(!file.exists()){
            throw new Exception("文件不存在");
        }
        if(!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))){
            throw new Exception("文件不是Excel");
        }
    }

    /**
     * 读取Excel测试，兼容多个版本
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try{
            //兼容多版本
            File excelFile = new File("/Users/cwray/Documents/GitHub/BigDataLearning/JavaDemo4Work/VersionTool/resource/excelWrite.xls");
            FileInputStream in = new FileInputStream(excelFile); //文件流
            checkExcelValid(excelFile);
            Workbook workbook = getWorkbook(in, excelFile);

            int sheetCount = workbook.getNumberOfSheets();

            /**
             * 设置当前excel中的sheet的下标： 0开始
             */

            Sheet sheet = workbook.getSheetAt(0); //遍历第1个Sheet

            //获取总行数
            System.out.println(sheet.getLastRowNum());

            //为挑过第一行目录设置count
            int count = 0;
            for (Row row : sheet){
                try{
                    //挑过第一和第二行目录
                    if(count < 0) {
                        count++;
                        continue;
                    }

                    //如果当前行没有数据，跳出循环
                    if (row.getCell(0).toString().equals("")) {
                        return;
                    }

                    //获取总列数（空格的不计算）
                    int columnTotalNum = row.getPhysicalNumberOfCells();
                    System.out.println("总列数： " + columnTotalNum);
                    System.out.println("最大列数： " + row.getLastCellNum());

                    int end = row.getLastCellNum();
                    for (int i = 0; i < end; i++){
                        Cell cell = row.getCell(i);
                        if(cell == null) {
                            System.out.println("null" + "\t");
                            continue;
                        }

                        Object obj = getValue(cell);

                        System.out.println(obj + "\t");
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static Object getValue(Cell cell){
        Object obj = null;
        switch (cell.getCellTypeEnum()){
            case BOOLEAN:
                obj = cell.getBooleanCellValue();
                break;
            case ERROR:
                obj = cell.getErrorCellValue();
                break;
            case NUMERIC:
                obj = cell.getNumericCellValue();
                break;
            case STRING:
                obj = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return obj;
    }
}


