package com.demo.commandLine;

import org.apache.commons.exec.LogOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xks
 * @date 2020-01-16
 */
public class CollectingLogOutputStream extends LogOutputStream {
    private static Logger logger = LoggerFactory.getLogger(CollectingLogOutputStream.class);
    private final List<String> lines = new LinkedList<String>();

    @Override
    protected void processLine(String line, int level) {

        lines.add(line);
        logger.info("日志级别{}：{}", level, line);
    }

    public List<String> getLines() {
        return lines;
    }
}
