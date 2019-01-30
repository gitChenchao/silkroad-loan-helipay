package com.danning.silkroad.helipay.common.response.builder;

import com.danning.silkroad.helipay.annotation.SignExclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 类名称：BankCardUnbindResp<br>
 * 类描述：<br>
 * 创建时间：2019年01月26日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class BankCardUnbindResp {
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
     * 签名
     */
    @SignExclude
    private String sign;
}
