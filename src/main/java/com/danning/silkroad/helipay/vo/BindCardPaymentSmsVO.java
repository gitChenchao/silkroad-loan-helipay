package com.danning.silkroad.helipay.vo;

import com.danning.silkroad.helipay.annotation.FieldEncrypt;
import com.danning.silkroad.helipay.annotation.SignExclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 类名称：BindCardPaymentSmsVO<br>
 * 类描述：绑卡支付短信VO<br>
 * 创建时间：2019年01月26日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class BindCardPaymentSmsVO {
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
    @NotNull(message = "交易类型不能为空")
    private String P8_orderAmount;

    /**
     * 手机号
     */
    @NotNull(message = "交易类型不能为空")
    @FieldEncrypt
    private String P9_phone;

    /**
     * 签名方式
     */
    @NotNull(message = "交易类型不能为空")
    @SignExclude
    private String signatureType;
}
