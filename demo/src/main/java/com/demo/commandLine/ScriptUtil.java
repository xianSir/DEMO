package com.demo.commandLine;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author xks
 * @date 2020-01-16
 * 1、内嵌编译器如"PythonInterpreter"无法引用扩展包，因此推荐使用java调用控制台进程方式"Runtime.getRuntime().execCmd()"来运行脚本(shell或python)；
 * 2、因为通过java调用控制台进程方式实现，需要保证目标机器PATH路径正确配置对应编译器；
 * 3、暂时脚本执行日志只能在脚本执行结束后一次性获取，无法保证实时性；因此为确保日志实时性，可改为将脚本打印的日志存储在指定的日志文件上；
 * 4、python 异常输出优先级高于标准输出，体现在Log文件中，因此推荐通过logging方式打日志保持和异常信息一致；否则用prinf日志顺序会错乱
 */
public class ScriptUtil {
    /**
     * make script
     * file
     *
     * @param scriptFileName
     * @param content
     * @throws IOException
     */

    public static void markScriptFile(String scriptFileName, String content) throws Exception {
        // make file,   filePath/gluesource/666-123456789.py
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(scriptFileName);
            fileOutputStream.write(content.getBytes("UTF-8"));
            fileOutputStream.close();
        } catch (Exception e) {
            throw e;
        } finally {
            if(fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }

    /**
     * 日志文件输出方式
     * <p>
     * 优点：支持将目标数据实时输出到指定日志文件中去
     * 缺点：
     * 标准输出和错误输出优先级固定，可能和脚本中顺序不一致
     * Java无法实时获取
     *
     * @param command
     * @param scriptFile
     * @param logFile
     * @param params
     * @return
     * @throws IOException
     */
    public static int execToFile(String command, String scriptFile, String logFile, String... params) throws IOException {
        // 标准输出：print （null if watchdog timeout）
        // 错误输出：logging + 异常 （still exists if watchdog timeout）
        // 标准输入
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(logFile, true);
            PumpStreamHandler streamHandler = new PumpStreamHandler(fileOutputStream, fileOutputStream, null);
            int exitValue = execCmd(command, scriptFile, params, streamHandler);
            return exitValue;
        } catch (Exception e) {
            return -1;
        } finally {
            if(fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static int execCmd(String command, String scriptFile, String... params) throws IOException {
        PumpStreamHandler streamHandler = new PumpStreamHandler(new CollectingLogOutputStream());
        // command
        return execCmd(command, scriptFile, params, streamHandler);
    }

    public static int execCmd(String command, String scriptFile, String[] params, PumpStreamHandler streamHandler) throws IOException {
        CommandLine commandline = new CommandLine(command);
        if(!StringUtils.isEmpty(scriptFile)) {
            commandline.addArgument(scriptFile);
        }
        if(params != null && params.length > 0) {
            commandline.addArguments(params);
        }
        // execCmd
        DefaultExecutor exec = new DefaultExecutor();
        exec.setExitValues(null);
        exec.setStreamHandler(streamHandler);
        int exitValue = exec.execute(commandline);// exit code: 0=success, 1=error
        return exitValue;
    }

    public static String execToString(String command, String scriptFile, String logFile, String... params) throws IOException {
        // 标准输出：print （null if watchdog timeout）
        // 错误输出：logging + 异常 （still exists if watchdog timeout）
        // 标准输入

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream, outputStream);
            execCmd(command, scriptFile, params, streamHandler);
        } catch (Exception e) {
            return null;
        } finally {
            if(outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                }

            }
        }
        return outputStream.toString("gbk");
    }


}
