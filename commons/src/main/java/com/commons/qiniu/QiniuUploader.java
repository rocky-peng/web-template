package com.commons.qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.File;

/**
 * @author pengqingsong
 * @date 12/09/2017
 * @desc
 */
public class QiniuUploader {

    private static final UploadManager UPLOAD_MANAGER = new UploadManager(new Configuration(Zone.zone2()));
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String cndDomain;

    public QiniuUploader(String accessKey, String secretKey, String bucket, String cndDomain) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.bucket = bucket;
        this.cndDomain = cndDomain;
    }

    public static String upToken(String accessKey, String secretKey, String bucket) {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }

    public String upToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }

    public String upload(File file, String fileName) {
        String token = upToken();
        try {
            Response response = UPLOAD_MANAGER.put(file, fileName, token);
            if (response.isOK()) {
                return cndDomain + fileName;
            } else {
                throw new RuntimeException("七牛图片上传失败[" + response.bodyString() + "]");
            }
        } catch (QiniuException e) {
            throw new RuntimeException("七牛图片上传失败", e);
        }
    }
}
