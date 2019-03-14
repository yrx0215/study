package com.jnshu.dreamteam.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;

/**
 * 上传图片工具类
 * @author wzp
 * @date 2019-3-13
 */
@Component
@ConfigurationProperties(prefix = "pic")
public class UploadPic {

    private static String bucketName;
    private static String picAddress;
    private static String accessKey;
    private static String secretKey;
    private static String region_name;
    private static String studentPhoto;
    private static String missionPhoto;
    private static String coursePhoto;

    /**
     * 获取cosClient对象
     * @return
     */
    private static COSClient getCOSClient(){
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(accessKey,secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者接口文档 FAQ 中说明。
        ClientConfig clientConfig = new ClientConfig(new Region(region_name));
        // 3 生成 cos 客户端。
        return new COSClient(cred, clientConfig);
    }

    private static String upload(MultipartFile file,String pictureId,String folder) throws IOException{

        COSClient cosClient = getCOSClient();
        InputStream inputStream = file.getInputStream();
        String filename = file.getOriginalFilename();
        Integer index = filename.lastIndexOf(".");
        String suffix = index==-1?"":filename.substring(index);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,folder+"/"+pictureId+suffix,inputStream,new ObjectMetadata());
        cosClient.putObject(putObjectRequest);
        cosClient.shutdown();
        return picAddress+"/"+folder+"/"+pictureId+suffix;
    }

    public static String uploadFactory(MultipartFile file,String pictureId,String folder) throws IOException{
        switch (folder) {
            case "student":
                return upload(file, pictureId, studentPhoto);
            case "mission":
                return upload(file, pictureId, missionPhoto);
            case "course":
                return upload(file, pictureId, coursePhoto);
        }
        return "上传失败";
    }

    public void setBucketName(String bucketName) {
        UploadPic.bucketName = bucketName;
    }

    public void setPicAddress(String picAddress) {
        UploadPic.picAddress = picAddress;
    }

    public void setAccessKey(String accessKey) {
        UploadPic.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        UploadPic.secretKey = secretKey;
    }

    public void setRegion_name(String region_name) {
        UploadPic.region_name = region_name;
    }

    public void setStudentPhoto(String studentPhoto) {
        UploadPic.studentPhoto = studentPhoto;
    }

    public void setMissionPhoto(String missionPhoto) {
        UploadPic.missionPhoto = missionPhoto;
    }

    public void setCoursePhoto(String coursePhoto) {
        UploadPic.coursePhoto = coursePhoto;
    }
}
