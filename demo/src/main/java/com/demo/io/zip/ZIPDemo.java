package com.demo.io.zip;

import java.io.*;
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
}
