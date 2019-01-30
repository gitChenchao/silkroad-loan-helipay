package com.danning.silkroad.helipay.vo;

import com.danning.silkroad.helipay.annotation.SignExclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 类名称：PaymentResultNoticeVO<br>
 * 类描述：代付回调VO<br>
 * 创建时间：2019年01月25日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
@Builder(toBuilder = true)
public class PaymentResultNoticeVO {

    /**
     * 交易类型
     */
    @NotNull(message = "交易类型不能为空")
    private String rt1_bizType;

    /**
     * 返回码
     */
    @NotNull(message = "返回码不能为空")
    private String rt2_retCode;

    /**
     * 返回信息
     */
    @NotNull(message = "返回信息不能为空")
    private String rt3_retMsg;

    /**
     * 商户编号
     */
    @NotNull(message = "商户编号不能为空")
    private String rt4_customerNumber;

    /**
     * 商户订单号
     */
    @NotNull(message = "商户订单号不能为空")
    private String rt5_orderId;

    /**
     * 平台流水号
     */
    @NotNull(message = "平台流水号不能为空")
    private String rt6_serialNumber;

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
    private String rt7_orderStatus;

    /**
     * 通知类型
     * ORDER_STATUS:普通通知
     *
     *
     * RETURN_REMITTANCE
     * :退汇通知
     */
    @NotNull(message = "通知类型不能为空")
    private String rt8_notifyType;

    /**
     * 返回信息
     */
    @NotNull(message = "返回信息不能为空")
    private String rt9_reason;

    /**
     * 订单开始时间
     * 2018-09-10 19:58:00
     */
    @NotNull(message = "订单开始时间不能为空")
    private String rt10_createDate;

    /**
     * 订单完成时间
     */
    @NotNull(message = "订单完成时间不能为空")
    private String rt11_completeDate;

    /**
     * 签名
     */
    @SignExclude
    @NotNull(message = "签名不能为空")
    private String sign;
}
