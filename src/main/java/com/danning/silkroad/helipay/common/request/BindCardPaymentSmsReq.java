package com.danning.silkroad.helipay.common.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 类名称：BindCardPaymentSmsReq<br>
 * 类描述：绑卡支付短信VO<br>
 * 创建时间：2019年02月12日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class BindCardPaymentSmsReq {
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
    @NotNull(message = "交易类型不能为空")
    private String orderAmount;

    /**
     * 手机号
     */
    @NotNull(message = "交易类型不能为空")
    private String phone;

    /**
     * 签名方式
     */
    @NotNull(message = "交易类型不能为空")
    private String signatureType;
}
