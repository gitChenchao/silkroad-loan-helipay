package com.danning.silkroad.helipay.common.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 类名称：BankCardbindListReq<br>
 * 类描述：用户绑定银行卡信息查询（仅限于交易卡）<br>
 * 创建时间：2019年02月12日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class BankCardbindListReq {
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private String userId;

    /**
     * 绑定id
     */
    @NotNull(message = "绑定id不能为空")
    private String bindId;

    /**
     * 时间戳
     */
    @NotNull(message = "时间戳不能为空")
    private String timestamp;

    /**
     * 签名方式
     */
    @NotNull(message = "签名方式不能为空")
    private String signatureType;
}
