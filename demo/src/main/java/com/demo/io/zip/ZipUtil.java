package com.demo.io.zip;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author xks
 * @date 2020-03-20
 */
public class ZipUtil {
    private static final String ALGORITHM = "PBEWithMD5AndDES";
    public static void zip(String zipFileName, String inputFile,String pwd) throws Exception {
        zip(zipFileName, new File(inputFile), pwd);
    }
    /**
     * 功能描述：压缩指定路径下的所有文件
     * @param zipFileName 压缩文件名(带有路径)
     * @param inputFile 指定压缩文件夹
     * @return
     * @throws Exception
     */
    public static void zip(String zipFileName, String inputFile) throws Exception {
        zip(zipFileName, new File(inputFile), null);
    }
    /**
     * 功能描述：压缩文件对象
     * @param zipFileName 压缩文件名(带有路径)
     * @param inputFile 文件对象
     * @return
     * @throws Exception
     */
    public static void zip(String zipFileName, File inputFile,String pwd) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        zip(out, inputFile, "",pwd);
        out.close();
    }
    /**
     *
     * @param out 压缩输出流对象
     * @param file
     * @param base
     * @throws Exception
     */
    public static void zip(ZipOutputStream outputStream, File file, String base,String pwd) throws Exception {
        if (file.isDirectory()) {
            File[] fl = file.listFiles();
            outputStream.putNextEntry(new ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";
            for (int i = 0; i < fl.length; i++) {
                zip(outputStream, fl[i], base + fl[i].getName(), pwd);
            }
        }
        else {
            outputStream.putNextEntry(new ZipEntry(base));
            FileInputStream inputStream = new FileInputStream(file);
//普通压缩文件
            if(pwd == null || pwd.trim().equals("")){
                int b;
                while ((b = inputStream.read()) != -1){
                    outputStream.write(b);
                }
                inputStream.close();
            } else{
                //给压缩文件加密
                PBEKeySpec keySpec = new PBEKeySpec(pwd.toCharArray());
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
                SecretKey passwordKey = keyFactory.generateSecret(keySpec);
                byte[] salt = new byte[8];
                Random rnd = new Random();
                rnd.nextBytes(salt);
                int iterations = 100;
                PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, iterations);
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.ENCRYPT_MODE, passwordKey, parameterSpec);
                outputStream.write(salt);
                byte[] input = new byte[64];
                int bytesRead;
                while ((bytesRead = inputStream.read(input)) != -1) {
                    byte[] output = cipher.update(input, 0, bytesRead);
                    if (output != null){
                        outputStream.write(output);
                    }
                }
                byte[] output = cipher.doFinal();
                if (output != null){
                    outputStream.write(output);
                }
                inputStream.close();
                outputStream.flush();
                outputStream.close();
            }
        }
        file.delete();
    }
    public static void unzip(String zipFileName, String outputDirectory)throws Exception {
        ZipInputStream inputStream = new ZipInputStream(new FileInputStream(zipFileName));
        unzip(inputStream, outputDirectory, null);
    }
    /**
     * 功能描述：将压缩文件解压到指定的文件目录下
     * @param zipFileName 压缩文件名称(带路径)
     * @param outputDirectory 指定解压目录
     * @return
     * @throws Exception
     */
    public static void unzip(String zipFileName, String outputDirectory, String pwd)throws Exception {
        ZipInputStream inputStream = new ZipInputStream(new FileInputStream(zipFileName));
        unzip(inputStream, outputDirectory, pwd);
    }
    public static void unzip(File zipFile, String outputDirectory, String pwd)throws Exception {
        ZipInputStream inputStream = new ZipInputStream(new FileInputStream(zipFile));
        unzip(inputStream, outputDirectory,pwd);
    }
    public static void unzip(ZipInputStream inputStream, String outputDirectory, String pwd) throws Exception{
        ZipEntry zipEntry = null;
        FileOutputStream outputStream = null;
        try{
            while ((zipEntry = inputStream.getNextEntry()) != null) {
                if (zipEntry.isDirectory()) {
                    String name = zipEntry.getName();
                    name = name.substring(0, name.length() - 1);
                    File file = new File(outputDirectory + File.separator + name);
                    if(! file.exists()){
                        file.mkdirs();
                    }
                }else {
                    File file = new File(outputDirectory + File.separator + zipEntry.getName());
                    File parentFile = file.getParentFile();
                    if(! parentFile.exists()){
                        parentFile.mkdirs();
                    }
                    file.createNewFile();
                    outputStream = new FileOutputStream(file);
                    if(pwd == null || pwd.trim().equals("")){
                        int b;
                        byte[] buffer = new byte[1024];
                        while ((b = inputStream.read(buffer)) != -1){
                            outputStream.write(buffer);
                        }
                        outputStream.flush();
                    }else{
                        PBEKeySpec keySpec = new PBEKeySpec(pwd.toCharArray());
                        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
                        SecretKey passwordKey = keyFactory.generateSecret(keySpec);
                        byte[] salt = new byte[8];
                        inputStream.read(salt);
                        int iterations = 100;
                        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, iterations);
                        Cipher cipher = Cipher.getInstance(ALGORITHM);
                        cipher.init(Cipher.DECRYPT_MODE, passwordKey, parameterSpec);
                        byte[] input = new byte[64];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(input)) != -1) {
                            byte[] output = cipher.update(input, 0, bytesRead);
                            if (output != null){
                                outputStream.write(output);
                            }
                        }
                        byte[] output = cipher.doFinal();
                        if (output != null){
                            outputStream.write(output);
                        }
                        outputStream.flush();
                    }
                }
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(inputStream != null){
                inputStream.close();
            }
            if(outputStream != null){
                outputStream.close();
            }
        }
    }
    public static byte[] getByteArrayFromFile(String fileName) throws Exception{
        FileInputStream fi = null;
        try{
            File file = new File(fileName);
            long size = file.length();
            if (size > Integer.MAX_VALUE) {
                throw new Exception("文件太大");
            }
            fi = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            byte[] all = new byte[(int) size];
            int offset = 0;
            int len = 0;
            while ((len = fi.read(buffer)) > -1) {
                System.arraycopy(buffer, 0, all, offset, len);
                offset += len;
            }
            return all;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(fi != null){
                fi.close();
            }
        }

        return null;
    }
    public static void createFileFromByteArray(byte[] b,String destName) throws Exception{
        FileOutputStream os = null;
        try{
            File destFile = new File(destName);
            File parentFile = destFile.getParentFile();
            if(! parentFile.exists()){
                parentFile.mkdirs();
            }
            os = new FileOutputStream(destName);
            os.write(b);
            os.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(os != null){
                os.close();
            }
        }
    }
    public static void main(String[] args) throws Exception{
        String fileName = "E:\\sunjinfu\\test\\test.zip";
        String outputDir = "E:\\sunjinfu\\test\\csv\\123";
        unzip(fileName, outputDir);

        File file = new File("E:\\1584668219588_2");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        FileInputStream in = new FileInputStream(file);
        int i = -1;
        byte[] arr = new byte[1024];
        while ((i = in.read(arr)) != -1) {
            out.write(arr, 0, arr.length);
        }
        in.close();
        out.close();
        byte[] bytes = out.toByteArray();

        System.out.println(bytes.length);

        ZipOutputStream zipOutputStream = new ZipOutputStream( new FileOutputStream(new File("E:\\ww.zip")));
        ZipEntry ze=new ZipEntry("1.zip");
        zipOutputStream.putNextEntry(ze);
        zipOutputStream.write(bytes, 0, bytes.length);
        zipOutputStream.flush();
        zipOutputStream.close();
    }

    /**
     * 功能描述：创建zip文件
     * @param filesList
     * @param zipPath
     * @param zipName
     * @throws IOException
     * 处理说明：
     * 接口说明：
     */
    /*public static void createZip(List<String> filesList, String zipPath, String zipName) throws IOException{
        if(filesList!=null&&filesList.size()>0){
            org.apache.tools.zip.ZipOutputStream zip=new org.apache.tools.zip.ZipOutputStream(new FileOutputStream(zipPath+zipName));
            byte[] b = new byte[1024];
            for(int i=0;i<filesList.size();i++){
                String name=filesList.get(i);
                FileInputStream in=new FileInputStream(zipPath+name);
                org.apache.tools.zip.ZipEntry ze=new org.apache.tools.zip.ZipEntry(name);
                zip.putNextEntry(ze);
                int len;
                while((len = in.read(b))!=-1){
                    zip.write(b,0,len);
                }
                zip.closeEntry();
                in.close();
                File f = new File(zipPath+name);
                if(f!=null){
                    f.delete();
                }
            }
            zip.close();
        }
    }
*/


    /**
     * 功能描述：创建zip文件
     * @param filesList
     * @param zipPath
     * @param zipName
     * @throws IOException
     * 处理说明：
     * 接口说明：
     */
    public static void createZip(List<String> filesList, String zipPath, String zipName) throws IOException{
        if(filesList!=null&&filesList.size()>0){
            org.apache.tools.zip.ZipOutputStream zip=new org.apache.tools.zip.ZipOutputStream(new FileOutputStream(zipPath+zipName));
            byte[] b = new byte[1024];
            for(int i=0;i<filesList.size();i++){
                String name=filesList.get(i);
                FileInputStream in=new FileInputStream(zipPath+name);
                org.apache.tools.zip.ZipEntry ze=new org.apache.tools.zip.ZipEntry(name);
                zip.putNextEntry(ze);
                int len;
                while((len = in.read(b))!=-1){
                    zip.write(b,0,len);
                }
                zip.closeEntry();
                in.close();
                File f = new File(zipPath+name);
                if(f!=null){
                    f.delete();
                }
            }
            zip.close();
        }
    }
}