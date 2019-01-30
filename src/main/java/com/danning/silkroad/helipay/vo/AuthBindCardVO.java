package com.danning.silkroad.helipay.vo;

import com.danning.silkroad.helipay.annotation.FieldEncrypt;
import com.danning.silkroad.helipay.annotation.SignExclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 类名称：AuthBindCardVO<br>
 * 类描述：鉴权绑卡VO<br>
 * 创建时间：2019年01月26日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class AuthBindCardVO {
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
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private String P3_userId;

    /**
     * 商户订单号
     */
    @NotNull(message = "商户订单号不能为空")
    private String P4_orderId;

    /**
     * 时间戳
     * 格式：yyyyMMddHHmmss
     * 14 位数字，精确到秒
     */
    @NotNull(message = "时间戳不能为空")
    private String P5_timestamp;

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空")
    private String P6_payerName;

    /**
     * 证件类型
     */
    @NotNull(message = "证件类型不能为空")
    private String P7_idCardType;

    /**
     * 证件号码
     */
    @NotNull(message = "证件号码不能为空")
    @FieldEncrypt
    private String P8_idCardNo;

    /**
     * 银行卡号
     */
    @NotNull(message = "银行卡号不能为空")
    @FieldEncrypt
    private String P9_cardNo;

    /**
     * 信用卡有效期年份
     */
    @FieldEncrypt
    private String P10_year;

    /**
     * 信用卡有效期月份
     */
    @FieldEncrypt
    private String P11_month;

    /**
     * cvv2
     */
    @FieldEncrypt
    private String P12_cvv2;

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    @FieldEncrypt
    private String P13_phone;

    /**
     * 短信验证码
     */
    @FieldEncrypt
    private String P14_validateCode;

    /**
     * 银行卡信息参数是否加密
     */
    @SignExclude
    private String P15_isEncrypt;

    /**
     * 签名方式
     */
    @SignExclude
    private String signatureType;
}
