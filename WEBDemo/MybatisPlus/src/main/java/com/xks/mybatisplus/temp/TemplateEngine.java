package com.xks.mybatisplus.temp;

import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;

import java.util.Map;

/**
 * @author xks
 * @date 2019-10-31
 */
public class TemplateEngine extends AbstractTemplateEngine {
    /**
     * 将模板转化成为文件
     *
     * @param objectMap    渲染对象 MAP 信息
     * @param templatePath 模板文件
     * @param outputFile   文件生成的目录
     */
    @Override
    public void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception {

    }

    /**
     * 模板真实文件路径
     *
     * @param filePath 文件路径
     * @return ignore
     */
    @Override
    public String templateFilePath(String filePath) {
        return null;
    }
}
