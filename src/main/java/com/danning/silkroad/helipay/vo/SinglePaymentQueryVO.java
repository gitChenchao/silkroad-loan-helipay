package com.danning.silkroad.helipay.vo;

import com.danning.silkroad.helipay.annotation.SignExclude;
import com.danning.silkroad.helipay.common.enums.HelipayBizType;
import com.danning.silkroad.helipay.common.request.SinglePaymentQueryReq;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 类名称：SinglePaymentQuery<br>
 * 类描述：单笔代付查询<br>
 * 创建时间：2019年01月25日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
@Builder(toBuilder = true)
public class SinglePaymentQueryVO {

    /**
     * 交易类型
     */
    @NotNull(message = "交易类型不能为空")
    private String P1_bizType;

    /**
     * 商户订单号
     */
    @NotNull(message = "商户订单号不能为空")
    private String P2_orderId;

    /**
     * 商户编号
     */
    @NotNull(message = "商户编号不能为空")
    private String P3_customerNumber;

    /**
     * 签名
     */
    @SignExclude
    private String sign;
}
