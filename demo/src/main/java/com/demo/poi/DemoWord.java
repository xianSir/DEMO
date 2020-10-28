package com.demo.poi;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xks
 * @date 2020-10-27
 */
public class DemoWord {
//    A、word文档正文段落概述：
//
//    一个word文档包含多个段落，一个段落包含多个Runs，一个Runs包含多个Run，Run是文档的最小单位
//
//    获取所有段落：List<XWPFParagraph> paragraphs = word.getParagraphs();
//
//    获取一个段落中的所有Runs：List<XWPFRun> xwpfRuns = xwpfParagraph.getRuns();
//
//    获取一个Runs中的一个Run：XWPFRun run = xwpfRuns.get(index);
//
//    B、word文档正文表格概述：
//
//    一个文档可包含多个表格，每个表格可以包含多行，每行可以包含多列（格），每一格的内容相当于一个完整的文档
//
//    获取所有表格：List<XWPFTable> xwpfTables = doc.getTables();
//
//    获取一个表格中的所有行：List<XWPFTableRow> xwpfTableRows = xwpfTable.getRows();
//
//    获取一行中的所有列：List<XWPFTableCell> xwpfTableCells = xwpfTableRow.getTableCells();
//
//    获取一格里的内容：List<XWPFParagraph> paragraphs = xwpfTableCell.getParagraphs();

    /**
     * 根据模板生成新word文档
     * 判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
     *
     * @param inputUrl  模板存放地址
     * @param outPutUrl 新文档存放地址
     * @param textMap   需要替换的信息集合
     * @param tableList 需要插入的表格信息集合
     */
    public static boolean changWord(String inputUrl, String outputUrl,
                                    Map<String, String> textMap) {
        //模板转换默认成功
        boolean changeFlag = true;
        try {
            //获取docx解析对象
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));

            //解析替换文本段落对象
            DemoWord.changeText(document, textMap);
            //解析替换表格对象
            DemoWord.changeTable(document, textMap);
            //生成新的word
            File file = new File(outputUrl);
            FileOutputStream stream = new FileOutputStream(file);
            document.write(stream);
            stream.close();
            System.out.println("-------------成功生成！------------------");
        } catch (IOException e) {
            e.printStackTrace();
            changeFlag = false;
        }

        return changeFlag;

    }

    /**
     * 替换段落文本
     *
     * @param document docx解析对象
     * @param textMap  需要替换的信息集合
     */
    public static void changeText(XWPFDocument document, Map<String, String> textMap) {
        //获取段落集合
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs) {
            //判断此段落时候需要进行替换
            String text = paragraph.getText();
            if(checkText(text)) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    //替换模板原来位置
                    // run.setText(changeValue(run.toString(), textMap),0);
                    String textValue = changeValue(run.toString(), textMap);
                    run.setText(textValue, 0);
                }
            }
        }
    }

    /**
     * 替换表格对象方法
     *
     * @param document  docx解析对象
     * @param textMap   需要替换的信息集合
     * @param tableList 需要插入的表格信息集合
     */
    public static void changeTable(XWPFDocument document, Map<String, String> textMap) {
        //获取表格对象集合
        List<XWPFTable> tables = document.getTables();
        for (int i = 0; i < tables.size(); i++) {
            //只处理行数大于等于2的表格，且不循环表头
            XWPFTable table = tables.get(i);
            if(table.getRows().size() > 1) {
                //判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
                if(checkText(table.getText())) {
                    List<XWPFTableRow> rows = table.getRows();
                    //遍历表格,并替换模板
                    eachTable(rows, textMap);
                } else {
//                    insertTable(table, tableList);
                }
            }
        }
    }


    /**
     * 遍历表格
     *
     * @param rows    表格行对象
     * @param textMap 需要替换的信息集合
     */
    public static void eachTable(List<XWPFTableRow> rows, Map<String, String> textMap) {
        for (XWPFTableRow row : rows) {
            List<XWPFTableCell> cells = row.getTableCells();
            for (XWPFTableCell cell : cells) {
                //判断单元格是否需要替换
                if(checkText(cell.getText())) {
                    List<XWPFParagraph> paragraphs = cell.getParagraphs();
                    for (XWPFParagraph paragraph : paragraphs) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            run.setFontSize(8);
                            String value = changeValue(run.toString(), textMap);
                            run.setText(value, 0);
                        }
                    }
                }
            }
        }
    }

    /**
     * 判断文本中时候包含$
     *
     * @param text 文本
     * @return 包含返回true, 不包含返回false
     */
    public static boolean checkText(String text) {
        boolean check = false;
        if(text.indexOf("$") != -1) {
            check = true;
        }
        return check;
    }

    /**
     * 匹配传入信息集合与模板
     *
     * @param value   模板需要替换的区域
     * @param textMap 传入信息集合
     * @return 模板需要替换区域信息集合对应值
     */
    public static String changeValue(String value, Map<String, String> textMap) {
        Set<Map.Entry<String, String>> textSets = textMap.entrySet();
        for (Map.Entry<String, String> textSet : textSets) {
            //匹配模板与替换值 格式${key}
            String key = "${" + textSet.getKey() + "}";
            if(value.indexOf(key) != -1) {
                value = value.replace(key, textSet.getValue());//仅替换参数
            }
        }
        //模板未匹配到区域替换为空
        if(checkText(value)) {
            value = "";
        }
        return value;
    }


    public static void main(String[] args) {

        String data800=
                "测试测试测测试测测试测测试测测试测测试测测试测测试测测试测测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测" +
                "试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测试测测试测测试测测试测测试测测试测测试测测试测测试测测测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测" +
                "测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测" +
                "测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测" +
                "测试测测试测测试测测试测测试测测试测测试测测试测测试测测测试测测试测测试测测试测测试测测试测测试测测试测测试测测测试测测试测测试测测试测测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测" +
                "试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测试测测试测测试测测试测测试测测试测测试测测试测测试测测测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测" +
                "测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测" +
                "测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测";
        String data500=
                "测试测试测测试测测试测测试测测试测测试测测试测测试测测试测测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测" +
                "试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测试测测试测测试测测试测测试测测试测测试测测试测测试测测测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测" +
                "测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测" +
                "测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测" +
                "测试测测试测测试测测试测测试测测试测测试测测试测测试测测测试测测试测测试测测试测测试测测试测测试测测试测测试测测测试测测试测测试测测试测测测试测测试测测试测测试测测试测测试测测试测测试测测试测测试测测" ;
        //模板文件地址
        String inputUrl = "E:\\IDEA_WORK\\GITHUB\\study\\demo\\src\\main\\Resources\\邯郸智慧工地平台施工日志模板.docx";
        //新生产的模板文件
        String outputUrl = "E:\\IDEA_WORK\\GITHUB\\study\\demo\\src\\main\\Resources\\new.docx";

        Map<String, String> testMap = new HashMap<String, String>();
        testMap.put("projectLabel", "测试项目");
        testMap.put("deptLabel", "测试单位");
        testMap.put("recordName", "小明");
        testMap.put("recordTime", "2020年10月28日 星期三");
        testMap.put("daytimeWeatherLabel", "多云转晴");
        testMap.put("nightWeatherLabel", "阴");
        testMap.put("daytimeWindLabel", "强风");
        testMap.put("nightWindLabel", "暴风");
        testMap.put("daytimeHighTemperatureLabel", "38.45");
        testMap.put("daytimeLowTemperature", "20.4");
        testMap.put("nightHighTemperature", "16.8");
        testMap.put("nightLowTemperature", "2.3");
        testMap.put("content",data500 );
        testMap.put("qualitySafe",data500);
        testMap.put("materialsOtherRecord", data500);
        DemoWord.changWord(inputUrl, outputUrl, testMap);
    }
}
