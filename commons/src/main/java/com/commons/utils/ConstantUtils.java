package com.commons.utils;

import okhttp3.OkHttpClient;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import sun.misc.BASE64Encoder;

import java.util.concurrent.TimeUnit;

/**
 * @author pengqingsong
 * @date 11/09/2017
 * @desc 常量工具类
 */
public class ConstantUtils {

    public static final int SECOND_IN_ONE_MINUTE = 60;

    public static final int SECOND_IN_ONE_HOUR = 3600;

    public static final int SECOND_IN_SIX_HOUR = 3600 * 6;


    public static final ThreadLocal<BASE64Encoder> BASE_64_ENCODER_THREAD_LOCAL = ThreadLocal.withInitial(() -> new BASE64Encoder());

    public static final ThreadLocal<OkHttpClient> OK_HTTP_CLIENT_THREAD_LOCAL = ThreadLocal.withInitial(() -> {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS).build();
        return okHttpClient;
    });

    public static final ThreadLocal<SecureRandomNumberGenerator> SECURE_RANDOM_NUMBER_GENERATOR_THREAD_LOCAL = ThreadLocal.withInitial(() -> new SecureRandomNumberGenerator());

}
