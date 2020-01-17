package com.demo.minio;

import com.eshore.framework.DataService;
import com.eshore.minio.MinioDataService;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.xmlpull.v1.XmlPullParserException;

import javax.naming.ConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author xks
 * @date 2020-01-06
 */
public class MinioClientUtils {


    private static MinioClientUtils minioClientUtils;

    private MinioClient minioClient;

    private static int RETRY_NUM = 3;

    private static final String bucketPublicPolicy = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\",\"s3:ListBucketMultipartUploads\"],\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Resource\":[\"arn:aws:s3:::test\"],\"Sid\":\"\"},{\"Action\":[\"s3:AbortMultipartUpload\",\"s3:DeleteObject\",\"s3:GetObject\",\"s3:ListMultipartUploadParts\",\"s3:PutObject\"],\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Resource\":[\"arn:aws:s3:::test/*\"],\"Sid\":\"\"}]}";

    public static MinioClientUtils getInstance() {
        if(null != minioClientUtils) {
            return minioClientUtils;
        }
        synchronized (MinioClientUtils.class) {
            if(null == minioClientUtils) {
                minioClientUtils = new MinioClientUtils();
            }
        }
        return minioClientUtils;
    }


    private MinioClientUtils() {
        init();
    }

    private void init() {
        initConfiguration();
        String url = "minio.url";
        String username = "minio.name";
        String password = "minio.password";
        String region = "minio.region";
        try {
            if(StringUtils.isNotEmpty(url) && StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
                minioClient = new MinioClient(url, username, password, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean createBucketPublic(String bucketName) {
        boolean isCreated;
        try {
//            if (minioClient.bucketExists(bucketName)) {
//                isCreated = false;
//            }
            minioClient.makeBucket("buzi");
            //minioClient.setBucketPolicy(bucketName, bucketPublicPolicy);
            isCreated = true;
        } catch (Exception e) {
            isCreated = false;
            e.printStackTrace();
        }
        return isCreated;
    }

    public String uploadJpegFile(String bucketName, String minioPath, String jpgFilePath) {
        return uploadFile(bucketName, minioPath, jpgFilePath, MediaType.IMAGE_JPEG_VALUE);
    }

    public String uploadJpegStream(String bucketName, String minioPath, InputStream inputStream) {
        return uploadStream(bucketName, minioPath, inputStream, MediaType.IMAGE_JPEG_VALUE);
    }

    public String uploadStream(String bucketName, String minioFilePath, InputStream inputStream, String mediaType) {
        if(StringUtils.isBlank(mediaType)) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        try {
            putObjectWithRetry(bucketName, minioFilePath, inputStream, mediaType);
            return cleanUrlByRemoveIp(minioClient.getObjectUrl(bucketName, minioFilePath));
        } catch (Exception e) {
           e.printStackTrace();
           return null;
        }
    }

    public String uploadFile(String bucketName, String minioFilePath, String localFile, String mediaType) {
        if(StringUtils.isBlank(mediaType)) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        try {
            putObjectWithRetry(bucketName, minioFilePath, localFile, mediaType);
            return cleanUrlByRemoveIp(minioClient.getObjectUrl(bucketName, minioFilePath));
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

    public List<MinioEntity> listFilesSwap(String bucketName, String prefix, boolean recursive) {
        return swapResultToEntityList(minioClient.listObjects(bucketName, prefix, recursive));
    }

    public Iterable<Result<Item>> listFiles(String bucketName, String prefix, boolean recursive) {
        return minioClient.listObjects(bucketName, prefix, recursive);
    }


    public List<MinioEntity> listFilesByBucketNameSwap(String bucketName) {
        return swapResultToEntityList(minioClient.listObjects(bucketName, null, true));
    }

    public Iterable<Result<Item>> listFilesByBucketName(String bucketName) {
        return minioClient.listObjects(bucketName, null, true);
    }

    public Iterable<Result<Item>> listFilesByBucketAndPrefix(String bucketName, String prefix) {
        return minioClient.listObjects(bucketName, prefix, true);
    }

    public List<MinioEntity> listFilesByBucketAndPrefixSwap(String bucketName, String prefix) {
        return swapResultToEntityList(minioClient.listObjects(bucketName, prefix, true));
    }

//    private Configuration initConfiguration() {
    private void initConfiguration() {
        ClassLoader classLoader = MinioClientUtils.class.getClassLoader();
        if(null == classLoader) {
            classLoader = Thread.currentThread().getContextClassLoader();
        }

        //Configuration configuration = null;
        URL resource = classLoader.getResource("minio.properties");
        if(null == resource) {
            throw new RuntimeException("can not find minio.properties");
        }
//        try {
//            configuration = new Properties(resource);
//        } catch (ConfigurationException e) {
//            throw new RuntimeException("load properties from url=" + resource.toString() + " occur error", e);
//        }
//        return configuration;
    }

    private MinioEntity swapResultToEntity(Result<Item> result) {
        MinioEntity minioEntity = new MinioEntity();
        try {
            if(result.get() != null) {
                Item item = result.get();
                minioEntity.setObjectName(cleanUrlByRemoveIp(item.objectName()));
                minioEntity.setDir(item.isDir());
                minioEntity.setEtag(item.etag());
                minioEntity.setLastModified(item.lastModified());
                minioEntity.setSize(item.size());
                minioEntity.setStorageClass(item.storageClass());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return minioEntity;
    }

    private String cleanUrlByRemoveIp(String objectName) {
        int i = objectName.lastIndexOf("//");
        return objectName.substring(i+1);
    }

    private List<MinioEntity> swapResultToEntityList(Iterable<Result<Item>> results) {
        List<MinioEntity> minioEntities = new ArrayList<>();
        for (Result<Item> result : results) {
            minioEntities.add(swapResultToEntity(result));
        }
        return minioEntities;
    }

    public void putObjectWithRetry(String bucketName, String objectName, InputStream stream, String contentType) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidArgumentException, NoResponseException, InvalidBucketNameException, XmlPullParserException, InternalException {
        int current = 0;
        boolean isSuccess = false;
        while (!isSuccess && current < RETRY_NUM) {
            try {
                minioClient.putObject(bucketName, objectName, stream, contentType);
                isSuccess = true;
            } catch (ErrorResponseException | InvalidResponseException e) {
                e.printStackTrace();
                current++;
            }
        }
        if(current == RETRY_NUM) {
           // LOGGER.error("[minio] putObject, backetName={}, objectName={}, failed finally!");
        }
    }

    public void putObjectWithRetry(String bucketName, String objectName, String fileName, String contentType) throws Exception {
        int current = 0;
        boolean isSuccess = false;
        while (!isSuccess && current < RETRY_NUM) {
            try {
                minioClient.putObject(bucketName, objectName, fileName, contentType);
                isSuccess = true;
            } catch (ErrorResponseException e) {
                current++;
                //LOGGER.debug("[minio] putObject file, ErrorResponseException occur!");
            }
        }
        if(current == RETRY_NUM) {
            //LOGGER.error("[minio] putObject, backetName={}, objectName={}, failed finally!");
        }
    }

    public static void main(String[] args) throws InvalidPortException, InvalidEndpointException {
        MinioDataService minioDataService = new MinioDataService("", "", "", "");
        DataService.DataEntry a=minioDataService.getDataEntry("");
    }
}