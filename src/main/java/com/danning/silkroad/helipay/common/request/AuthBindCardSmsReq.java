package com.danning.silkroad.helipay.common.request;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 类名称：AuthBindCardSmsReq<br>
 * 类描述：鉴权绑卡短信VO<br>
 * 创建时间：2019年01月26日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class AuthBindCardSmsReq {
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
     * 格式：yyyyMMddHHmmss
     * 14 位数字，精确到秒
     */
    @NotNull(message = "时间戳不能为空")
    private String timestamp;

    /**
     * 银行卡号
     */
    @NotNull(message = "银行卡号不能为空")
    private String cardNo;

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    private String phone;

    /**
     * 证件号
     */
    @NotNull(message = "证件号不能为空")
    private String idCardNo;

    /**
     * 证件类型
     */
    @NotNull(message = "证件类型不能为空")
    private String idCardType;

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空")
    private String payerName;

    /**
     * 信用卡有效期年份
     * 当银行卡是信用卡时必输
     */
    private String year;

    /**
     * 信用卡有效期月份
     * 当银行卡是信用卡时必输
     */
    private String month;

    /**
     * cvv2
     * 当银行卡是信用卡时必输，AES加密
     * 不参与签名
     * 信用卡安全码
     */
    private String cvv2;

    /**
     * 签名方式
     */
    @NotNull(message = "签名方式不能为空")
    private String signatureType;
}
