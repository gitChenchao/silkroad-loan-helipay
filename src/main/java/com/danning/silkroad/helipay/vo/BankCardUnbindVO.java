package com.danning.silkroad.helipay.vo;

import com.danning.silkroad.helipay.annotation.SignExclude;
import lombok.Data;

/**
 * 类名称：BankCardUnbindVO<br>
 * 类描述：银行卡解绑<br>
 * 创建时间：2019年01月26日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class BankCardUnbindVO {

    /**
     * 交易类型
     */
    private String P1_bizType;

    /**
     * 商户编号
     */
    private String P2_customerNumber;

    /**
     * 用户id
     */
    private String P3_userId;

    /**
     * 绑定id
     */
    private String P4_bindId;

    /**
     * 订单号
     */
    private String P5_orderId;

    /**
     * 时间戳
     */
    private String P6_timestamp;

    /**
     * 签名方式
     */
    @SignExclude
    private String signatureType;
}
