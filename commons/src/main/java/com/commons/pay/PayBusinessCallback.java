package com.commons.pay;

/**
 * @author pengqingsong
 * @date 12/09/2017
 * @desc
 */
public interface PayBusinessCallback {
    /**
     * 根据订单编号获取订单id
     *
     * @param orderCode 订单编号
     * @return 订单的id
     */
    long getOrderIdByOrderCode(String orderCode);

    /**
     * 幂等操作
     * 交易关闭时的业务处理，建议同步处理，在新事务中完成
     *
     * @param orderId
     * @param payTypeEnum
     * @return 业务处理成功，返回true；否则返回false
     */
    boolean doWhenTradeClosed(long orderId, PayTypeEnum payTypeEnum);

    /**
     * 幂等操作
     * 终端用户成功支付订单时的业务处理(更新数据库信息，发送消息等)，建议同步处理，在新事务中完成
     *
     * @param orderId
     * @param payTypeEnum
     * @return 业务处理成功，返回true；否则返回false
     */
    boolean doWhenTradeSuccess(long orderId, PayTypeEnum payTypeEnum);
}