package com.danning.silkroad.helipay.vo;

import com.danning.silkroad.helipay.annotation.SignExclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 类名称：QuickPayQueryVO<br>
 * 类描述：快捷支付订单查询<br>
 * 创建时间：2019年01月26日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
@Builder(toBuilder = true)
public class QuickPayQueryVO {
    /**
     * 交易类型
     */
    @NotNull(message = "交易类型不能为空")
    private String P1_bizType;

    /**
     * 订单号
     */
    @NotNull(message = "订单号不能为空")
    private String P2_orderId;

    /**
     * 商户号
     */
    @NotNull(message = "商户号不能为空")
    private String P3_customerNumber;

    /**
     * 签名方式
     */
    @NotNull(message = "签名方式不能为空")
    @SignExclude
    private String signatureType;
}
