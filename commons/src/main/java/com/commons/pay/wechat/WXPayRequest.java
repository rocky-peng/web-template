package com.commons.pay.wechat;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.util.concurrent.TimeUnit;

public class WXPayRequest {

    private WXPayConfig config;
    private OkHttpClient certOkHttpClient;
    private OkHttpClient noCertOkHttpClient;


    public WXPayRequest(WXPayConfig config) throws Exception {
        this.config = config;
        this.certOkHttpClient = buildCertOkHttpClient(config);
        this.noCertOkHttpClient = buildNoCertOkHttpClient(config);
    }

    private OkHttpClient buildNoCertOkHttpClient(WXPayConfig config) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(config.getHttpReadTimeoutMs(), TimeUnit.MILLISECONDS)
                .connectTimeout(config.getConnectTimeoutMs(), TimeUnit.MILLISECONDS)
                .writeTimeout(10, TimeUnit.SECONDS).build();
        return okHttpClient;
    }

    private OkHttpClient buildCertOkHttpClient(WXPayConfig config) {
        InputStream ins = WeChat.class.getResourceAsStream(config.getPkcs12FilePath());
        SSLSocketFactory sslSocketFactory = buildSSLSocketFactory(ins, config.getPkcs12FilePasswd());
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(config.getHttpReadTimeoutMs(), TimeUnit.MILLISECONDS)
                .connectTimeout(config.getConnectTimeoutMs(), TimeUnit.MILLISECONDS)
                .writeTimeout(60, TimeUnit.SECONDS).sslSocketFactory(sslSocketFactory).build();
        return okHttpClient;
    }

    private SSLSocketFactory buildSSLSocketFactory(InputStream ins, String pkcs12FilePasswd) {
        try {
            char[] passwd = pkcs12FilePasswd.toCharArray();
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(ins, passwd);
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, passwd).build();
            SSLSocketFactory socketFactory = sslcontext.getSocketFactory();
            return socketFactory;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 请求，只请求一次，不做重试
     *
     * @param useCert 是否使用证书，针对退款、撤销等操作
     * @return
     * @throws Exception
     */
    private String requestOnce(final String domain, String urlSuffix, String data, boolean useCert) throws Exception {
        OkHttpClient okHttpClient;
        if (useCert) {
            okHttpClient = this.certOkHttpClient;
        } else {
            okHttpClient = this.noCertOkHttpClient;
        }
        String url = "https://" + domain + urlSuffix;
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, data);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "text/xml")
                .addHeader("User-Agent", "wxpay sdk java v1.0 " + config.getMchID())
                .post(body)
                .url(url)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }


    private String request(String urlSuffix, String data, boolean useCert) throws Exception {
        Exception exception = null;
        long elapsedTimeMillis;
        long startTimestampMs = WXPayUtil.getCurrentTimestampMs();

        IWXPayDomain.DomainInfo domainInfo = config.getWxPayDomain().getDomain(config);
        if (domainInfo == null) {
            throw new Exception("WXPayConfig.getWXPayDomain().getDomain() is empty or null");
        }

        String result = null;
        try {
            result = requestOnce(domainInfo.domain, urlSuffix, data, useCert);
        } catch (UnknownHostException ex) {  // dns 解析错误，或域名不存在
            exception = ex;
            WXPayConstants.log.warn("UnknownHostException for domainInfo {}", domainInfo);
        } catch (ConnectTimeoutException ex) {
            exception = ex;
            WXPayConstants.log.warn("connect timeout happened for domainInfo {}", domainInfo);
        } catch (SocketTimeoutException ex) {
            exception = ex;
            WXPayConstants.log.warn("timeout happened for domainInfo {}", domainInfo);
        } catch (Exception ex) {
            exception = ex;
        }
        elapsedTimeMillis = WXPayUtil.getCurrentTimestampMs() - startTimestampMs;
        config.getWxPayDomain().report(domainInfo.domain, elapsedTimeMillis, exception);

        if (exception != null) {
            throw exception;
        }
        return result;
    }


    /**
     * 可重试的，非双向认证的请求
     */
    public String requestWithoutCert(String urlSuffix, String data) throws Exception {
        return this.request(urlSuffix, data, false);
    }

    /**
     * 可重试的，双向认证的请求
     */
    public String requestWithCert(String urlSuffix, String data) throws Exception {
        return this.request(urlSuffix, data, true);
    }
}
