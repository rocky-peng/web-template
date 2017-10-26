package com.commons.pay.wechat;

import javax.net.ssl.SSLSocketFactory;
import java.io.InputStream;

/**
 * @author pengqingsong
 * @date 18/10/2017
 * @desc
 */
public class WeChat {

    public SSLSocketFactory sslSocketFactory;
    /**
     * 公众号appId
     */
    private String gongZhongAppId;
    /**
     * 商户号
     */
    private String mchId;

    /**
     * @param pkcs12FilePath   相对于classpath根路径，所以必须以 / 开头
     * @param pkcs12FilePasswd 证书密码
     */
    public WeChat(String pkcs12FilePath, String pkcs12FilePasswd) {
        InputStream ins = WeChat.class.getResourceAsStream(pkcs12FilePasswd);
        sslSocketFactory = buildSSLSocketFactory(ins, pkcs12FilePasswd);
    }

    /**
     * @param pkcs12FileInputStream 指向证书文件的inputstream
     * @param pkcs12FilePasswd      证书密码
     */
    public WeChat(InputStream pkcs12FileInputStream, String pkcs12FilePasswd) {
        sslSocketFactory = buildSSLSocketFactory(pkcs12FileInputStream, pkcs12FilePasswd);
    }


    private SSLSocketFactory buildSSLSocketFactory(InputStream ins, String pkcs12FilePasswd) {
        return null;
    }

}
