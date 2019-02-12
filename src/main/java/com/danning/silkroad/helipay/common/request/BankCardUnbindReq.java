package com.danning.silkroad.helipay.common.request;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 类名称：BankCardUnbindReq<br>
 * 类描述：银行卡解绑<br>
 * 创建时间：2019年02月12日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class BankCardUnbindReq {
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private String userId;

    /**
     * 绑定id
     */
    @NotNull(message = "绑卡id不能为空")
    private String bindId;

    /**
     * 订单号
     */
    @NotNull(message = "订单号不能为空")
    private String orderId;

    /**
     * 时间戳
     */
    @NotNull(message = "时间戳不能为空")
    private String timestamp;

    /**
     * 签名方式
     */
    private String signatureType;
}
