package com.demo.poi;

import com.sun.org.apache.bcel.internal.generic.DCONST;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author xks
 * @date 2019-03-22
 */
public class demoExcel {
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
        //sheet.setDefaultColumnStyle(0, style);
        //sheet.setDefaultColumnStyle(1, style);

        //创建当前行
        HSSFRow row = sheet.createRow(0);
        //第一行
        HSSFCell c1 = row.createCell(0);
        HSSFCell c2 = row.createCell(1);
        HSSFCell c3 = row.createCell(3);
        //为单元格赋值
        c1.setCellValue("查询条件：区域");
        c2.setCellValue("行业");
        c3.setCellValue("月份（默认当前月）");
        //第二行
        HSSFRow r2 = sheet.createRow(1);
        //合并单元格
        CellRangeAddress region = new CellRangeAddress(1, 1, 0, 22);
        sheet.addMergedRegion(region);

        HSSFCell r2cell = r2.createCell(0);
        r2cell.setCellValue("保障农民工工资支付工作情况汇总表");


        CellRangeAddress region3_1 = new CellRangeAddress(3, 3, 0, 3);
        CellRangeAddress region3_2 = new CellRangeAddress(3, 3, 4, 11);
        CellRangeAddress region3_3 = new CellRangeAddress(3, 3, 12, 17);
        sheet.addMergedRegion(region3_1);
        sheet.addMergedRegion(region3_2);
        sheet.addMergedRegion(region3_3);

        HSSFRow r3 = sheet.createRow(2);
        HSSFCell r3Cell = r3.createCell(0);
        r3Cell.setCellValue("  填报单位：");


        HSSFRow r4 = sheet.createRow(3);
        HSSFCell r3ce1 = r4.createCell(0);
        r3ce1.setCellValue("工程项目情况");
        HSSFCell r3ce2 = r4.createCell(4);
        r3ce2.setCellValue("制度执行情况");
        HSSFCell r3ce3 = r4.createCell(8);
        r3ce3.setCellValue("欠薪案件办理情况");

        HSSFRow r5 = sheet.createRow(4);
        String[] arr={"在建工程项目数（个）","其中政府投资工程项目数（个）","在建工程项目农民工总人数（万人）","其中劳动合同签订人数（万人）",
                "按月足额发放工资的在建工程项目数（个）","其中政府投资工程项目数(个）","实行实名制管理的在建工程项目数（个）","实行工资保证金制度的在建工程项目数（个）",
                "实行分账管理的在建工程项目数（个）","实行用工单位通过银行卡向农民工发放工资的在建工程项目数（个）","实行分包的在建项目数（个）","分包企业委托总承包企业通过农民工工资专用账户直接向农民工发放工资的在建工程项目数（个）",
                "当期办结案件（个）","涉及农民工人数（人）","涉及     金额   （万元）","存量案件（件）",};
        for (int i=0;i<arr.length;i++){
            HSSFCell cell = r5.createCell(i);
            cell.setCellValue(arr[i]);
        }
        //输出文件
        FileOutputStream out = new FileOutputStream("poi.xls");
        book.write(out);
        out.close();
        System.out.println("创建成功");
    }
}
