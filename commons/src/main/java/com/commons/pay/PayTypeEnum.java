package com.commons.pay;

import lombok.Getter;

/**
 * @author pengqingsong
 * @date 12/09/2017
 * @desc
 */
public enum PayTypeEnum {

    /**
     * 支付宝支付
     */
    ALIPAY(1),

    /**
     * 微信支付
     */
    WEIXIN(2),

    /**
     * 苹果支付
     */
    APPLE(3);

    @Getter
    private int value;

    PayTypeEnum(int value) {
        this.value = value;
    }

}
