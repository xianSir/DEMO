package com.demo.minio;

import io.minio.MinioClient;
import io.minio.errors.*;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author xks
 * @date 2020-01-06
 */
public class FileUploader {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, XmlPullParserException, InvalidKeyException {
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象  http://192.168.63.129:9000/
            MinioClient minioClient = new MinioClient("http://192.168.63.129:9000/", "minioadmin", "minioadmin");
            String filePath = "C:\\Users\\Administrator\\Desktop\\1.jpg";
            String fileName = "1.jpg";
            String bucket = "test";
            upLoadLocaltionFile(minioClient, filePath, fileName, bucket);

        } catch (MinioException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     * @param minioClient
     * @param filePath
     * @param fileName
     * @param bucket
     * @throws InvalidBucketNameException
     * @throws NoSuchAlgorithmException
     * @throws InsufficientDataException
     * @throws IOException
     * @throws InvalidKeyException
     * @throws NoResponseException
     * @throws XmlPullParserException
     * @throws ErrorResponseException
     * @throws InternalException
     * @throws RegionConflictException
     * @throws InvalidArgumentException
     */
    public static void upLoadLocaltionFile(MinioClient minioClient, String filePath, String fileName, String bucket) throws InvalidBucketNameException, NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, NoResponseException, XmlPullParserException, ErrorResponseException, InternalException, RegionConflictException, InvalidArgumentException, InvalidResponseException {
        // 检查存储桶是否已经存在
        boolean isExist = minioClient.bucketExists("test");
        if(isExist) {
            System.out.println("Bucket already exists.");
        } else {
            // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
            minioClient.makeBucket(bucket);
        }
        minioClient.putObject(bucket, fileName, filePath);
    }
}
