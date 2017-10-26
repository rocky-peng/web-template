package com.commons.utils;

import com.commons.enums.CaptchaType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author pengqingsong
 * @date 11/09/2017
 * @desc 验证码生成器
 */
public class CaptchaGenerator {

    private static final int[] NUMS = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    private static final Map<CaptchaType, Integer> CAPTCHA_TYPE_INTEGER_MAP = new HashMap<>();

    static {
        CAPTCHA_TYPE_INTEGER_MAP.put(CaptchaType.FOR_BIND_PHONE, 6);
        CAPTCHA_TYPE_INTEGER_MAP.put(CaptchaType.FOR_UPDATE_PASSWD, 6);
    }


    public static String random4NumCaptcha() {
        Random random = new Random();

        StringBuilder result = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            result.append(NUMS[random.nextInt(NUMS.length)]);
        }

        return result.toString();
    }

    public static String randomNumCaptcha(int length) {
        Random random = new Random();
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            result.append(NUMS[random.nextInt(NUMS.length)]);
        }
        return result.toString();
    }

    public static String randomNumCaptcha(CaptchaType captchaType) {
        Integer length = CAPTCHA_TYPE_INTEGER_MAP.get(captchaType);
        if (length == null) {
            length = 6;
        }
        return randomNumCaptcha(length);
    }
}
