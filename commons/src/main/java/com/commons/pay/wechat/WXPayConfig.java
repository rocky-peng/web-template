package com.commons.pay.wechat;

import lombok.Data;

@Data
public class WXPayConfig {

    /**
     * App ID
     */
    private String appId;

    /**
     * Mch ID
     */
    private String mchID;


    /**
     * API 密钥
     */
    private String key;


    /**
     * 商户证书路径（相对于classpath根路径，所以必须以 / 开头）
     */
    private String pkcs12FilePath;

    /**
     * 商户证书路径密码
     */
    private String pkcs12FilePasswd;

    /**
     * HTTP(S) 连接超时时间，单位毫秒
     */
    private int connectTimeoutMs;

    /**
     * HTTP(S) 读数据超时时间，单位毫秒
     */
    private int httpReadTimeoutMs;

    /**
     * WXPayDomain, 用于多域名容灾自动切换
     */
    private IWXPayDomain wxPayDomain;

    /**
     * 是否自动上报。
     * 若要关闭自动上报，子类中实现该函数返回 false 即可。
     */
    private boolean shouldAutoReport;

    /**
     * 进行健康上报的线程的数量
     */
    public int reportWorkerNum;


    /**
     * 健康上报缓存消息的最大数量。会有线程去独立上报
     * 粗略计算：加入一条消息200B，10000消息占用空间 2000 KB，约为2MB，可以接受
     */
    public int reportQueueMaxSize;

    /**
     * 批量上报，一次最多上报多个数据
     */
    public int reportBatchSize;

}
