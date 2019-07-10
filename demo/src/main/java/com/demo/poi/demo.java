package com.demo.poi;

import com.sun.org.apache.bcel.internal.generic.DCONST;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author xks
 * @date 2019-03-22
 */
public class demo {
    public static void main(String[] args) throws Exception {
//       readXLSDemo();
        createFileDemo();
    }

    /**
     * @return void
     * @Date 16:59 2019/3/22 0022
     * @author Administrator
     * @Description
     * @Param []
     */
    private static void readXLSDemo() throws IOException {
//        WorkbookFactory.create(new File("E:\\LEARN\\JAVA_SE\\poi.xls"));
        NPOIFSFileSystem fileSystem = new NPOIFSFileSystem(new File("E:\\LEARN\\JAVA_SE\\poi.xls"), true);
        HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
        HSSFSheet sheet = workbook.getSheet("hello poi");
        sheet.getRow(0).getFirstCellNum();
        //System.out.println("工作sheet名称"+sheet.getSheetName());
        // System.out.println("循环遍历当前文件的全部工作sheet");
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            System.out.println("当期索引为" + i + "----" + workbook.getSheetAt(i).getSheetName());
            HSSFSheet sheetAt = workbook.getSheetAt(i);
            Iterator<Row> iterator = sheetAt.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    System.out.println("列:" + cell.getColumnIndex() + "行:" + cell.getRowIndex() + "-----" + cell.toString());
                }
            }
        }
        fileSystem.close();
    }

    /**
     * @return void
     * @Date 17:04
     * @author Administrator
     * @Description 创建exc文件
     * @Param []
     */
    private static void createFileDemo() throws IOException {
            //创建文件
        HSSFWorkbook book = new HSSFWorkbook();
            //创建工作表sheet
        HSSFSheet sheet = book.createSheet("hello poi");
            //设置工作表风格
        HSSFCellStyle style = book.createCellStyle();
        //style.setBorderBottom(BorderStyle.THIN);
        //style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        //style.setBorderLeft(BorderStyle.THIN);
            /*设置单元格边界颜色*/
        //style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
        //style.setRightBorderColor(IndexedColors.BLUE.getIndex());
            /*设置单元格边界*/
        //style.setBorderRight(BorderStyle.THIN);
            /*设置单元格虚线*/
        //style.setBorderTop(BorderStyle.MEDIUM_DASHED);
        //style.setTopBorderColor(IndexedColors.BLUE.getIndex());
        sheet.setDefaultColumnStyle(0, style);
        sheet.setDefaultColumnStyle(1, style);
        //创建当前行
        HSSFRow row = sheet.createRow(0);
        //创建单元格
        HSSFCell c1 = row.createCell(0);
        // c1.setCellStyle(style);
        HSSFCell c2 = row.createCell(1);
        //为单元格赋值
        c1.setCellValue("第一行-第一列");
        c2.setCellValue("第一行-第二列");
        //输出文件
        FileOutputStream out = new FileOutputStream("poi.xls");
        book.write(out);
        out.close();
        System.out.println("创建成功");
    }
}
