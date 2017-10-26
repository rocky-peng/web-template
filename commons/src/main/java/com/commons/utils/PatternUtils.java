package com.commons.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @author pengqingsong
 * @date 09/09/2017
 * @desc 一些公共的正则表达式
 */
public class PatternUtils {


    public static final Pattern PHONE_PATTERN = Pattern.compile("^1[0-9]{10}$");
    public static final Pattern CAPTCHA_4_PATTERN = Pattern.compile("^[0-9]{4}$");
    public static final Pattern CAPTCHA_6_PATTERN = Pattern.compile("^[0-9]{6}$");


    public static boolean isValidPhone(String phone) {
        if (StringUtils.isBlank(phone)) {
            return false;
        }
        return PHONE_PATTERN.matcher(phone).find();
    }

    public static boolean isValid4Captcha(String captcha) {
        if (StringUtils.isBlank(captcha)) {
            return false;
        }
        return CAPTCHA_4_PATTERN.matcher(captcha).find();
    }

    public static boolean isValid6Captcha(String captcha) {
        if (StringUtils.isBlank(captcha)) {
            return false;
        }
        return CAPTCHA_6_PATTERN.matcher(captcha).find();
    }

}
