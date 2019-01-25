package com.yl.demo.poi;

import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Alex
 * @since 2019/1/24 17:51
 */
public class POIDemo {

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        SXSSFWorkbook sxssfWorkbook = null;
        FileOutputStream fis = null;
        try {
            sxssfWorkbook = new SXSSFWorkbook(100);
            SXSSFSheet sheet = sxssfWorkbook.createSheet("demo");
            for (int i = 0;i < 100000;i++){
                SXSSFRow row = sheet.createRow(i);
                SXSSFCell cell = row.createCell(1);
                cell.setCellValue("清风细语");
            }
            fis = new FileOutputStream("/Users/alex/Public/demo.xlsx");
            sxssfWorkbook.write(fis);
        }catch (Exception e){

        }finally {
            if(sxssfWorkbook != null){
                try {
                    sxssfWorkbook.close();
                } catch (IOException e) {
                }
            }
            if(fis != null){
                try {
                    fis.close();
                }catch (Exception e){

                }
            }
        }
        long end = System.currentTimeMillis();

        System.err.println(end - begin);
    }

}
