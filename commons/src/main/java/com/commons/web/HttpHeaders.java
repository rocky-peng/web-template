package com.commons.web;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :  pengqingsong
 * Date : 01/03/2017 12:03
 * Description :
 * Test :
 */
public final class HttpHeaders {

    public static final String HEADER_UID = "uid";

    /**
     * 时间戳,13位的
     */
    public static final String HEADER_TIMESTAMP = "timestamp";

    /**
     * 系统类型（android,ios,weixin,...）
     */
    public static final String HEADER_OS_TYPE = "os_type";

    /**
     * 系统版本
     */
    public static final String HEADER_OS_VERSION = "os_version";

    /**
     * 前端版本，格式（x.y.z）,x,y,z均为数字
     */
    public static final String HEADER_CLIENT_VERSION = "client_version";

    /**
     * 终端唯一码，（ios貌似是idfa,andriod好像是imei）
     */
    public static final String HEADER_TERMINAL_UUID = "terminal_uuid";

    public static final String HEADER_TOKEN = "token";

    /**
     * 经度(登陆后必传)
     */
    public static final String HEADER_LONGITUDE = "longitude";

    /**
     * 纬度(登陆后必传)
     */
    public static final String HEADER_LATITUDE = "latitude";


    public static final List<String> HEADER_KEYS = new ArrayList<>();

    static {
        HEADER_KEYS.add(HEADER_UID);
        HEADER_KEYS.add(HEADER_TIMESTAMP);
        HEADER_KEYS.add(HEADER_CLIENT_VERSION);
        HEADER_KEYS.add(HEADER_OS_TYPE);
        HEADER_KEYS.add(HEADER_OS_VERSION);
        HEADER_KEYS.add(HEADER_TERMINAL_UUID);
        HEADER_KEYS.add(HEADER_TOKEN);
    }

    public static long getHeaderUid(HttpServletRequest request) {
        return Long.valueOf(request.getHeader(HEADER_UID));
    }

    public static long getHeaderTimestamp(HttpServletRequest request) {
        return Long.valueOf(request.getHeader(HEADER_TIMESTAMP));
    }

    public static String getHeaderOsType(HttpServletRequest request) {
        return request.getHeader(HEADER_OS_TYPE);
    }

    public static String getHeaderOsVersion(HttpServletRequest request) {
        return request.getHeader(HEADER_OS_VERSION);
    }

    public static String getHeaderClientVersion(HttpServletRequest request) {
        return request.getHeader(HEADER_CLIENT_VERSION);
    }

    public static String getHeaderTerminalUuid(HttpServletRequest request) {
        return request.getHeader(HEADER_TERMINAL_UUID);
    }

    public static String getHeaderToken(HttpServletRequest request) {
        return request.getHeader(HEADER_TOKEN);
    }

    public static double getHeaderLongitude(HttpServletRequest request) {
        return Double.valueOf(request.getHeader(HEADER_LONGITUDE));
    }

    public static double getHeaderLatitude(HttpServletRequest request) {
        return Double.valueOf(request.getHeader(HEADER_LATITUDE));
    }


}
