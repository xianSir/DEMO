package com.xks.freemark;


import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xks
 * @date 2019-11-03
 */
public class template {
    public static void main(String[] args) {
        try {
            // 第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
            Configuration configuration = new Configuration(Configuration.getVersion());
            // 第二步：设置模板文件所在的路径。

            configuration.setDirectoryForTemplateLoading(new File("F:\\A_IDEA_WORK\\xks\\parent\\MybatisPlus\\src\\main\\resources\\templates"));

            // 第三步：设置模板文件使用的字符集。一般就是utf-8.
            configuration.setDefaultEncoding("utf-8");
            // 第四步：加载一个模板，创建一个模板对象。
            Template template = configuration.getTemplate("controller..ftl");
            // 第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
            Map dataModel = new HashMap<>();
            Map c = new HashMap<>();
            Map table = new HashMap<>();
            table.put("comment", "测试");
            table.put("controllerName", "测试");
            table.put("entityPath", "测试");
            c.put("Controller", "测试");
            c.put("ModuleName", "测试");
            //向数据集中添加数据
            dataModel.put("superControllerClassPackage", "测试");
            dataModel.put("superControllerClass", "true");
            dataModel.put("author", "测试");
            dataModel.put("restControllerStyle", true);
            dataModel.put("kotlin", false);
            dataModel.put("date", "测试");
            dataModel.put("package", c);
            dataModel.put("table", table);
            // 第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
            Writer out = new FileWriter(new File("F:\\A_IDEA_WORK\\xks\\parent\\MybatisPlus\\src\\main\\resources\\templates\\hello"));
            // 第七步：调用模板对象的process方法输出文件。
            template.process(dataModel, out);
            // 第八步：关闭流。
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
