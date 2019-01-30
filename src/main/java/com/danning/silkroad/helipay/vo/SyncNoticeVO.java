package com.danning.silkroad.helipay.vo;

import com.danning.silkroad.helipay.annotation.SignExclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 类名称：SyncNoticeVO<br>
 * 类描述：<br>
 * 创建时间：2019年01月26日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
@Builder(toBuilder = true)
public class SyncNoticeVO {

    /**
     * 交易类型
     */
    @NotNull(message = "交易类型不能为空")
    private String rt1_bizType;

    /**
     * 返回码
     */
    @NotNull(message = "返回码不能为空")
    private String rt2_retCode;

    /**
     * 返回信息
     */
    private String rt3_retMsg;

    /**
     * 商户编号
     */
    @NotNull(message = "商户编号不能为空")
    private String rt4_customerNumber;

    /**
     * 商户订单号
     */
    @NotNull(message = "商户订单号不能为空")
    private String rt5_orderId;

    /**
     * 合利宝交易流水号
     */
    @NotNull(message = "合利宝交易流水号不能为空")
    private String rt6_serialNumber;

    /**
     * 订单完成时间
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    private String rt7_completeDate;

    /**
     * 订单金额
     */
    @NotNull(message = "订单金额不能为空")
    private String rt8_orderAmount;

    /**
     * 订单状态
     * INIT:未支付
     * SUCCESS：成功
     * CANCELLED：已取消
     * REFUNDED：已退款
     * FAILED：失败
     * DOING：处理中
     */
    @NotNull(message = "订单状态不能为空")
    private String rt9_orderStatus;

    /**
     * 绑定id
     */
    @NotNull(message = "绑定id不能为空")
    private String rt10_bindId;

    /**
     * 银行编码
     */
    @NotNull(message = "银行编码不能为空")
    private String rt11_bankId;

    /**
     * 银行卡类型
     */
    @NotNull(message = "银行卡类型不能为空")
    private String rt12_onlineCardType;

    /**
     * 银行卡后四位
     */
    @NotNull(message = "银行卡后四位不能为空")
    private String rt13_cardAfterFour;

    /**
     * 用户标识
     */
    @NotNull(message = "用户标识不能为空")
    private String rt14_userId;

    /**
     * 签名
     */
    @NotNull(message = "签名不能为空")
    @SignExclude
    private String sign;
}
