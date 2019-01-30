package com.danning.silkroad.helipay.common.response.builder;

import com.danning.silkroad.helipay.annotation.SignExclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 类名称：BindCardPaymentResp<br>
 * 类描述：绑卡支付返回<br>
 * 创建时间：2019年01月26日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class BindCardPaymentResp {
    /**
     * 交易类型
     */
    private String rt1_bizType;

    /**
     * 返回码
     */
    private String rt2_retCode;

    /**
     * 返回信息
     */
    private String rt3_retMsg;

    /**
     * 商户编号
     */
    private String rt4_customerNumber;

    /**
     * 商户订单号
     */
    private String rt5_orderId;

    /**
     * 合利宝交易流水号
     */
    private String rt6_serialNumber;

    /**
     * 订单完成时间
     */
    private String rt7_completeDate;

    /**
     * 订单金额
     */
    private String rt8_orderAmount;

    /**
     * 订单状态
     */
    private String rt9_orderStatus;

    /**
     * 合利宝绑定号
     */
    private String rt10_bindId;

    /**
     * 银行编码
     */
    private String rt11_bankId;

    /**
     * 银行卡类型
     */
    private String rt12_onlineCardType;

    /**
     * 银行卡后四位
     */
    private String rt13_cardAfterFour;

    /**
     * 用户标识
     */
    private String rt14_userId;

    /**
     * 签名
     */
    @SignExclude
    private String sign;
}
