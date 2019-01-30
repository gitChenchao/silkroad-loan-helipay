package com.danning.silkroad.helipay.vo;

import com.danning.silkroad.helipay.annotation.FieldEncrypt;
import com.danning.silkroad.helipay.annotation.SignExclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 类名称：BindCardPaymentVO<br>
 * 类描述：绑卡支付<br>
 * 创建时间：2019年01月26日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class BindCardPaymentVO {
    /**
     * 交易类型
     */
    @NotNull(message = "交易类型不能为空")
    private String P1_bizType;

    /**
     * 商户编号
     */
    @NotNull(message = "商户编号不能为空")
    private String P2_customerNumber;

    /**
     * 绑卡id
     */
    @NotNull(message = "绑卡id不能为空")
    private String P3_bindId;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private String P4_userId;

    /**
     * 商户订单号
     */
    @NotNull(message = "商户订单号不能为空")
    private String P5_orderId;

    /**
     * 时间戳
     */
    @NotNull(message = "时间戳不能为空")
    private String P6_timestamp;

    /**
     * 交易币种
     */
    @NotNull(message = "交易币种不能为空")
    private String P7_currency;

    /**
     * 交易金额
     */
    @NotNull(message = "交易金额不能为空")
    private String P8_orderAmount;

    /**
     * 商品名称
     */
    @NotNull(message = "商品名称不能为空")
    private String P9_goodsName;

    /**
     * 商品描述
     */
    private String P10_goodsDesc;

    /**
     * 终端类型
     * IMEI
     * MAC
     * UUID（针对 IOS 系统）
     * OTHER
     */
    @NotNull(message = "终端类型不能为空")
    private String P11_terminalType;

    /**
     * 终端标志
     * 终端唯一标识，如手机序列号
     */
    @NotNull(message = "终端标志不能为空")
    private String P12_terminalId;

    /**
     * 下单ip
     */
    @NotNull(message = "下单ip不能为空")
    private String P13_orderIp;

    /**
     * 下单有效时间
     * 过了订单有效时间的订单会被设置为取消状态不能再重新进行支付
     */
    private String P14_period;

    /**
     * 订单有效时间单位
     * Day：天
     * Hour：时
     * Minute：分
     */
    private String P15_periodUnit;

    /**
     * 服务器通知回调地址
     */
    @NotNull(message = "服务器通知回调地址不能为空")
    private String P16_serverCallbackUrl;

    /**
     * 短信验证码
     */
    @FieldEncrypt
    @SignExclude
    private String P17_validateCode;

    /**
     * 签名方式
     */
    @NotNull(message = "签名方式不能为空")
    @SignExclude
    private String signatureType;
}
