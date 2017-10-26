package com.commons.utils;

import com.commons.enums.CaptchaType;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pengqingsong
 * @date 28/09/2017
 * @desc 一些通用的短信验证码
 */
public class SmsContentManager {

    private static final Map<CaptchaType, String> CAPTCHA_TYPE_STRING_MAP = new HashMap<>();

    static {
        CAPTCHA_TYPE_STRING_MAP.put(CaptchaType.FOR_BIND_PHONE, "验证码[%s],您正在注册会员或绑定手机号,如非本人操作请忽略");
        CAPTCHA_TYPE_STRING_MAP.put(CaptchaType.FOR_UPDATE_PASSWD, "验证码[%s],您正在修改密码,如非本人操作请忽略");
    }

    public static String getSmsContent(String captcha, CaptchaType captchaType) {
        String pattern = CAPTCHA_TYPE_STRING_MAP.get(captchaType);
        return StringUtils.isBlank(pattern) ? null : String.format(pattern, captcha);
    }

}
