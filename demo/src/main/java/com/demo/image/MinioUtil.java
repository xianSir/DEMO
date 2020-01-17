package com.demo.image;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Random;

/**
 * @author xks
 * @date 2020-01-06
 */
public class MinioUtil {
    private static final Logger log = LoggerFactory.getLogger(MinioUtil.class);
    //Mino服务器的AccessKey
    private final transient static String ACCESS_KEY = "填写你的Mino服务器AccessKey";
    //Mino服务器的SecretKey
    private final transient static String SECRET_KEY = "填写你的Mino服务器SecretKey";
    //桶名称
    private final transient static String BUCKET_NAME = "delivery";
    //读写分离-上传服务器
    private final transient static String OSS_URL_WRITE = "http://你的服务器上传地址";
    //读写分离-下载服务器
    private final transient static String OOS_URL_READ = "http://你的服务器下载地址";
    //minio服务端口，默认是9000
    private final transient static int OSS_PORT = 9000;
    private transient static boolean BUCKET_EXISTS = false;

    //单例模式-内部类实现
    private static class MinioClientHolder {
        private static MinioClient minioClient;

        static {
            try {
                minioClient = new MinioClient(OSS_URL_WRITE, OSS_PORT, ACCESS_KEY, SECRET_KEY);
            } catch (InvalidEndpointException e) {
                e.printStackTrace();
            } catch (InvalidPortException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取minio客户端实例
     *
     * @return
     */
    private static MinioClient getMinioClient() {
        return MinioClientHolder.minioClient;
    }

    /**
     * 上传文件
     * 支持单文件，多文件
     * 返回文件访问路径，多文件以分号‘；’分隔
     *
     * @param muFiles
     * @return
     */
    public static String uploadFiles(MultipartFile... muFiles) {
        if(muFiles.length < 1) {
            throw new RuntimeException("上传文件为空！");
        }
        StringBuilder str = new StringBuilder();
        for (MultipartFile muFile : muFiles) {
            str.append(uploadFile(muFile));
            str.append(";");
        }
        return str.deleteCharAt(str.length() - 1).toString();

    }

    /**
     * 内部方法
     * 上传文件
     *
     * @param muFile
     * @return
     */
    private static String uploadFile(MultipartFile muFile) {
        String fileName = getFilePathName(muFile, false);
        try {
            MinioClient minioClient = getMinioClient();
            if(!BUCKET_EXISTS && !minioClient.bucketExists(BUCKET_NAME)) {
                minioClient.makeBucket(BUCKET_NAME);
                minioClient.setBucketPolicy(BUCKET_NAME, "");
                BUCKET_EXISTS = true;
            }
            InputStream inputStream = muFile.getInputStream();
            //如果是图片文件就进行压缩
            if(com.demo.image.ImageUtil.isImage(muFile.getOriginalFilename())) {
                inputStream = ImageUtil.getInputStream(
                        ImageUtil.setWatermark(
                                ImageUtil.compress(
                                        ImageIO.read(inputStream))),
                        ImageUtil.getFileExtention(muFile.getOriginalFilename()));
            }
            minioClient.putObject(BUCKET_NAME, fileName, inputStream, muFile.getContentType());
        } catch (Exception e) {
            log.error("文件上传失败", e);
            throw new RuntimeException("文件上传失败");
        }
        return OOS_URL_READ + BUCKET_NAME + fileName;
    }

    /**
     * 获取文件名
     *
     * @param muFile   文件
     * @param isRetain 是否保留源文件名
     * @return 返回文件名，以当前年月日作为前缀路径
     */
    private static String getFilePathName(MultipartFile muFile, boolean isRetain) {
        String fileName = muFile.getOriginalFilename();
        String name = fileName;
        String prefix = "";
        if(fileName.indexOf('.') != -1) {
            name = fileName.substring(0, fileName.indexOf('.'));
            prefix = fileName.substring(fileName.lastIndexOf("."));
        }

        LocalDate date = LocalDate.now();
        StringBuilder filePathName = new StringBuilder("/upload/");
        filePathName.append(date.getYear());
        filePathName.append("/");
        filePathName.append(date.getMonthValue());
        filePathName.append("/");
        filePathName.append(date.getDayOfMonth());
        filePathName.append("/");
        //添加随机后缀
        Random r = new Random();
        int pix = r.ints(1, (100 + 1)).findFirst().getAsInt();
        filePathName.append(System.currentTimeMillis());
        filePathName.append("" + pix);
        //文件名超过32字符则截取
        if(isRetain) {
            filePathName.append("_");
            if(name.length() >= 32) {
                name = name.substring(0, 32);
            }
            filePathName.append(name);
        }
        filePathName.append(prefix);
        return filePathName.toString();
    }
}