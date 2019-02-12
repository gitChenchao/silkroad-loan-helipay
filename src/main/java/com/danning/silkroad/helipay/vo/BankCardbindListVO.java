package com.danning.silkroad.helipay.vo;

import com.danning.silkroad.helipay.annotation.SignExclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 类名称：BankCardbindListVO<br>
 * 类描述：用户绑定银行卡信息查询（仅限于交易卡）<br>
 * 创建时间：2019年01月26日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
@Builder(toBuilder = true)
public class BankCardbindListVO {
    /**
     * 交易类型
     */
    @NotNull(message = "交易类型不能为空")
    private String P1_bizType;

    /**
     * 商户编号
     */
    @NotNull(message = "商户编号不能为空")
    private String P2_customerNumber;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private String P3_userId;

    /**
     * 绑定id
     */
    @NotNull(message = "绑定id不能为空")
    private String P4_bindId;

    /**
     * 时间戳
     */
    @NotNull(message = "时间戳不能为空")
    private String P5_timestamp;

    /**
     * 签名方式
     */
    @NotNull(message = "签名方式不能为空")
    @SignExclude
    private String signatureType;
}
