package com.danning.silkroad.helipay.common.response.builder;

import com.danning.silkroad.helipay.annotation.SignExclude;
import lombok.Data;

/**
 * 类名称：BizResponse<br>
 * 类描述：合利宝-代付返回参数<br>
 * 创建时间：2019年01月25日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class SinglePaymentBizResponse {
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
     * 商户订单号
     */
    private String rt5_orderId;

    /**
     * 平台流水号
     */
    private String rt6_serialNumber;

    /**
     * 签名
     */
    @SignExclude
    private String sign;
}
