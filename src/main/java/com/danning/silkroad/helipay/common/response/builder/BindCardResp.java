package com.danning.silkroad.helipay.common.response.builder;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 类名称：BindCardResp<br>
 * 类描述：<br>
 * 创建时间：2019年01月26日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class BindCardResp {

    /**
     * 姓名
     */
    private String payerName;

    /**
     * 证件类型
     */
    private String idCardType;

    /**
     * 证件号
     */
    private String idCardNo;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 银行编码
     */
    private String bankId;

    /**
     * 借贷类型
     * DEBIT:借记卡
     * CREDIT:贷记卡
     */
    private String cardType;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 绑定状态
     * INIT:初始化
     * SUCCESS:成功
     * FAIL:失败
     * DOING:处理中
     * UNBIND:解绑
     */
    private String bindStatus;

    /**
     * 绑定号
     * 合利宝生成的唯一绑卡ID
     */
    private String bindId;
}
