package com.danning.silkroad.helipay.common.response.builder;

import com.danning.silkroad.helipay.annotation.SignExclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 类名称：AuthBindCardResp<br>
 * 类描述：<br>
 * 创建时间：2019年01月26日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class AuthBindCardResp {
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
    @SignExclude
    private String rt3_retMsg;

    /**
     * 商户编号
     */
    private String rt4_customerNumber;

    /**
     * 用户ID
     */
    private String rt5_userId;

    /**
     * 商户订单号
     */
    private String rt6_orderId;

    /**
     * 绑定状态
     */
    private String rt7_bindStatus;

    /**
     * 银行编码
     */
    private String rt8_bankId;

    /**
     * 银行卡后四位
     */
    private String rt9_cardAfterFour;

    /**
     * 绑定号
     */
    private String rt10_bindId;

    /**
     * 平台流水号
     */
    private String rt11_serialNumber;

    /**
     * 签名
     */
    @SignExclude
    private String sign;
}
