package com.commons.utils;

/**
 * @author pengqingsong
 * @date 17/10/2017
 * @desc GPS相关的工具类
 */
public class GpsUtils {

    public static boolean isValidLongitude(double longitude) {
        return longitude > -180 && longitude < 180;
    }

    public static boolean isValidLatitude(double latitude) {
        return latitude > -85.05112878 && latitude < 85.05112878;
    }

}
