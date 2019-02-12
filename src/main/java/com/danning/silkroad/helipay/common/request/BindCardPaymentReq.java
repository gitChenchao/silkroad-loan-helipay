package com.danning.silkroad.helipay.common.request;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 类名称：BindCardPaymentReq<br>
 * 类描述：绑卡支付<br>
 * 创建时间：2019年02月12日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class BindCardPaymentReq {
    /**
     * 绑卡id
     */
    @NotNull(message = "绑卡id不能为空")
    private String bindId;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private String userId;

    /**
     * 商户订单号
     */
    @NotNull(message = "商户订单号不能为空")
    private String orderId;

    /**
     * 时间戳
     */
    @NotNull(message = "时间戳不能为空")
    private String timestamp;

    /**
     * 交易币种
     */
    @NotNull(message = "交易币种不能为空")
    private String currency;

    /**
     * 交易金额
     */
    @NotNull(message = "交易金额不能为空")
    private String orderAmount;

    /**
     * 商品名称
     */
    @NotNull(message = "商品名称不能为空")
    private String goodsName;

    /**
     * 商品描述
     */
    private String goodsDesc;

    /**
     * 终端类型
     * IMEI
     * MAC
     * UUID（针对 IOS 系统）
     * OTHER
     */
    @NotNull(message = "终端类型不能为空")
    private String terminalType;

    /**
     * 终端标志
     * 终端唯一标识，如手机序列号
     */
    @NotNull(message = "终端标志不能为空")
    private String terminalId;

    /**
     * 下单ip
     */
    @NotNull(message = "下单ip不能为空")
    private String orderIp;

    /**
     * 下单有效时间
     * 过了订单有效时间的订单会被设置为取消状态不能再重新进行支付
     */
    private String period;

    /**
     * 订单有效时间单位
     * Day：天
     * Hour：时
     * Minute：分
     */
    private String periodUnit;

    /**
     * 服务器通知回调地址
     */
    @NotNull(message = "服务器通知回调地址不能为空")
    private String serverCallbackUrl;

    /**
     * 短信验证码
     */
    private String validateCode;

    /**
     * 签名方式
     */
    @NotNull(message = "签名方式不能为空")
    private String signatureType;
}
