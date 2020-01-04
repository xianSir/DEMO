package com.demo.io.zip;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author xks
 * @date 2019-11-14
 */
public class ZIPDemo {
    public static void main(String[] args) throws Exception {
        String zipFileName = "ziptest.zip";
        String[] entries = new String[2];
        entries[0] = "test1.txt";
        entries[1] = "notes" + File.separator + "test2.txt";
        zip(zipFileName, entries);
    }

    /**
     * 压缩文件
     * @param zipFileName
     * @param zipEntries
     * @throws Exception
     */
    public static void zip(String zipFileName, String[] zipEntries) throws Exception {


        ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(zipFileName)));

        // Set the compression level to best compression
        zos.setLevel(Deflater.BEST_COMPRESSION);

        for (int i = 0; i < zipEntries.length; i++) {
            File entryFile = new File(zipEntries[i]);
            if(!entryFile.exists()) {
                System.out.println("The entry file  " + entryFile.getAbsolutePath()
                        + "  does  not  exist");
                System.out.println("Aborted   processing.");
                return;
            }
            ZipEntry ze = new ZipEntry(zipEntries[i]);
            zos.putNextEntry(ze);
            addEntryContent(zos, zipEntries[i]);
            zos.closeEntry();
        }
    }


    public static void addEntryContent(ZipOutputStream zos, String entryFileName)
            throws IOException, FileNotFoundException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                entryFileName));

        byte[] buffer = new byte[1024];
        int count = -1;
        while ((count = bis.read(buffer)) != -1) {
            zos.write(buffer, 0, count);
        }
        bis.close();
    }

    /**
     * 解压zip文件
     * @param zipFileName
     * @param unzipdir
     */
    public static void unzip(String zipFileName, String unzipdir) {
        try (ZipInputStream zis = new ZipInputStream(new BufferedInputStream(
                new FileInputStream(zipFileName)))) {

            ZipEntry entry = null;
            while ((entry = zis.getNextEntry()) != null) {
                // Extract teh entry"s contents
                extractEntryContent(zis, entry, unzipdir);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void extractEntryContent(ZipInputStream zis, ZipEntry entry,
                                           String unzipdir) throws IOException, FileNotFoundException {

        String entryFileName = entry.getName();
        String entryPath = unzipdir + File.separator + entryFileName;

        createFile(entryPath);

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(
                entryPath));

        byte[] buffer = new byte[1024];
        int count = -1;
        while ((count = zis.read(buffer)) != -1) {
            bos.write(buffer, 0, count);
        }

        bos.close();
    }

    public static void createFile(String filePath) throws IOException {
        File file = new File(filePath);
        File parent = file.getParentFile();

        if (!parent.exists()) {
            parent.mkdirs();
        }
        file.createNewFile();
    }

    /**
     * 解压rar文件
     * @param srcRarPath
     * @param dstDirectoryPath
     * @return
     */
    public static List<String> RarFiles(String srcRarPath, String dstDirectoryPath) {
        List<String> list = new ArrayList<String>();
        if(!srcRarPath.toLowerCase().endsWith(".rar")) {
            System.out.println("非rar文件！");
            return list;
        }
        File dstDiretory = new File(dstDirectoryPath);
        if(!dstDiretory.exists()) {// 目标目录不存在时，创建该文件夹
            dstDiretory.mkdirs();
        }
        Archive a = null;
        try {
            a = new Archive(new FileInputStream(new File(srcRarPath)));
            if(a != null) {
                // a.getMainHeader().print(); // 打印文件信息.
                FileHeader fh = a.nextFileHeader();
                while (fh != null) {
                    // 防止文件名中文乱码问题的处理
                    String fileName = fh.getFileNameW().isEmpty() ? fh
                            .getFileNameString() : fh.getFileNameW();
                    list.add(fileName);
                    if(fh.isDirectory()) { // 文件夹
                        File fol = new File(dstDirectoryPath + File.separator
                                + fileName);
                        fol.mkdirs();
                    } else { // 文件
                        File out = new File(dstDirectoryPath + File.separator
                                + fileName.trim());
                        try {
                            if(!out.exists()) {
                                if(!out.getParentFile().exists()) {// 相对路径可能多级，可能需要创建父目录.
                                    out.getParentFile().mkdirs();
                                }
                                out.createNewFile();
                            }
                            FileOutputStream os = new FileOutputStream(out);
                            a.extractFile(fh, os);
                            os.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    fh = a.nextFileHeader();
                }
                a.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
}
