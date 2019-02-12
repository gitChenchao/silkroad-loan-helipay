package com.danning.silkroad.helipay.common.request;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 类名称：SinglePaymentQueryReq<br>
 * 类描述：单笔代付查询<br>
 * 创建时间：2019年02月12日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class SinglePaymentQueryReq implements Serializable {
    private static final long serialVersionUID = 2397777466109549904L;
    /**
     * 商户订单号
     */
    @NotNull(message = "商户订单号不能为空")
    private String orderId;

    private String aaa;
}
