package com.danning.silkroad.helipay.common.request;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 类名称：QuickPayQueryReq<br>
 * 类描述：快捷支付订单查询<br>
 * 创建时间：2019年02月12日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class QuickPayQueryReq {
    /**
     * 订单号
     */
    @NotNull(message = "订单号不能为空")
    private String orderId;

    /**
     * 签名方式
     */
    @NotNull(message = "签名方式不能为空")
    private String signatureType;
}
