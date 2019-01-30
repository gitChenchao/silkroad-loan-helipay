package com.danning.silkroad.helipay.common.response.builder;

import com.danning.silkroad.helipay.annotation.SignExclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 类名称：BankCardbindListResp<br>
 * 类描述：用户绑定银行卡信息查询（仅限于交易卡）<br>
 * 创建时间：2019年01月26日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class BankCardbindListResp {

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
     * 交易卡信息列表
     */
    private Object rt5_bindCardList;

    /**
     * 签名
     */
    @SignExclude
    private String sign;
}
