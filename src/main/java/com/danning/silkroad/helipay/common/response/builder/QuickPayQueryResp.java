package com.danning.silkroad.helipay.common.response.builder;

import com.danning.silkroad.helipay.annotation.SignExclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 类名称：QuickPayQueryResp<br>
 * 类描述：<br>
 * 创建时间：2019年01月26日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class QuickPayQueryResp {
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
     * 交易金额
     */
    private String rt6_orderAmount;

    /**
     * 订单创建时间
     */
    private String rt7_createDate;

    /**
     * 订单完成时间
     */
    private String rt8_completeDate;

    /**
     * 订单状态
     */
    private String rt9_orderStatus;

    /**
     * 合利宝交易流水号
     */
    private String rt10_serialNumber;

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
     * 绑定id
     */
    private String rt14_bindId;

    /**
     * 用户标志
     */
    private String rt15_userId;

    /**
     * 订单成功或失败的原因
     */
    @SignExclude
    private String retReason;

    /**
     * 签名
     */
    @SignExclude
    private String sign;
}
