package com.danning.silkroad.helipay.common.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 类名称：PaymentResultNoticeReq<br>
 * 类描述：代付回调VO<br>
 * 创建时间：2019年02月12日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class PaymentResultNoticeReq {
    /**
     * 返回码
     */
    @NotNull(message = "返回码不能为空")
    private String retCode;

    /**
     * 返回信息
     */
    @NotNull(message = "返回信息不能为空")
    private String retMsg;

    /**
     * 商户编号
     */
    @NotNull(message = "商户编号不能为空")
    private String customerNumber;

    /**
     * 商户订单号
     */
    @NotNull(message = "商户订单号不能为空")
    private String orderId;

    /**
     * 平台流水号
     */
    @NotNull(message = "平台流水号不能为空")
    private String serialNumber;

    /**
     * 打款状态
     * RECEIVE 已接收
     * INIT初始化
     * DOING处理中
     * SUCCESS成功
     * FAIL失败
     * REFUND退款
     */
    @NotNull(message = "打款状态不能为空")
    private String orderStatus;

    /**
     * 通知类型
     * ORDER_STATUS:普通通知
     *
     *
     * RETURN_REMITTANCE
     * :退汇通知
     */
    @NotNull(message = "通知类型不能为空")
    private String notifyType;

    /**
     * 返回信息
     */
    @NotNull(message = "返回信息不能为空")
    private String reason;

    /**
     * 订单开始时间
     * 2018-09-10 19:58:00
     */
    @NotNull(message = "订单开始时间不能为空")
    private String createDate;

    /**
     * 订单完成时间
     */
    @NotNull(message = "订单完成时间不能为空")
    private String completeDate;

    /**
     * 签名
     */
    @NotNull(message = "签名不能为空")
    private String sign;
}
