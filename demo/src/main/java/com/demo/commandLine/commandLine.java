package com.demo.commandLine;

import org.apache.commons.exec.*;

import java.io.ByteArrayOutputStream;

/**
 * @author xks
 * @date 2020-01-16
 * 执行本地命令行 (cmd shell)
 *
 * WinRAR X IM2.rar G:/  解压文件 到指定目录下
 */
public class commandLine {

    public static void main(String[] args) {
        String s = execCmdWithResult();
        System.out.println(s);
    }

    /**
     * 执行不需要返回结果的命令
     * @throws Exception
     */
    public static void execCmdWithoutResult() throws Exception{
        //开启windows telnet: net start telnet
        //注意：第一个空格之后的所有参数都为参数
        CommandLine cmdLine = new CommandLine("net");
        cmdLine.addArgument("start");
        cmdLine.addArgument("telnet");
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        //设置60秒超时，执行超过60秒后会直接终止
        ExecuteWatchdog watchdog = new ExecuteWatchdog(60 * 1000);
        executor.setWatchdog(watchdog);
        DefaultExecuteResultHandler handler = new DefaultExecuteResultHandler();
        executor.execute(cmdLine, handler);
        //命令执行返回前一直阻塞
        handler.waitFor();
    }

    /**
     * 带返回结果的命令执行
     * @return
     */
    private static String execCmdWithResult() {
        try {
//            String command = "ping 192.168.1.10";
            String command = "C:\\Program Files (x86)\\WinRAR\\WinRAR X C:\\Program Files (x86)\\WinRAR\\IM2.rar G:/ ";
            //接收正常结果流
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            //接收异常结果流
            ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
            CommandLine commandline = CommandLine.parse(command);
            DefaultExecutor exec = new DefaultExecutor();
            exec.setExitValues(null);
            //设置一分钟超时
            ExecuteWatchdog watchdog = new ExecuteWatchdog(60*1000);
            exec.setWatchdog(watchdog);
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream,errorStream);
            exec.setStreamHandler(streamHandler);
            exec.execute(commandline);
            //不同操作系统注意编码，否则结果乱码
            String out = outputStream.toString("GBK");
            String error = errorStream.toString("GBK");
            return out+error;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
