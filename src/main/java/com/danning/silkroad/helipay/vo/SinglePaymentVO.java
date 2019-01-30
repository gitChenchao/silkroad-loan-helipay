package com.danning.silkroad.helipay.vo;

import com.danning.silkroad.helipay.annotation.SignExclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 类名称：SinglePaymentVO<br>
 * 类描述：单笔支付vo<br>
 * 创建时间：2019年01月25日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class SinglePaymentVO {
    /**
     * 交易类型
     * 单笔代付接口
     */
    @NotNull(message = "交易类型不能为空")
    private String P1_bizType;
    /**
     * 商户订单号
     * 商户系统内部订单号，要求50字符以内，同一商户号下订单号唯一
     */
    @NotNull(message = "商户订单号不能为空")
    private String P2_orderId;
    /**
     * 商户编号
     * 合利宝分配商户号
     */
    @NotNull(message = "商户编号不能为空")
    private String P3_customerNumber;
    /**
     *  金额
     *  金额单位为元，最少值0.01
     */
    private String P4_amount;
    /**
     * 银行编码
     * 见附录银行编码表
     */
    @NotNull(message = "银行编码不能为空")
    private String P5_bankCode;
    /**
     * 银行账户号
     */
    @NotNull(message = "银行账户号不能为空")
    private String P6_bankAccountNo;
    /**
     * 银行账户名
     */
    @NotNull(message = "银行账户名不能为空")
    private String P7_bankAccountName;
    /**
     * 业务类型
     * B2B:对公
     * B2C:对私
     */
    @NotNull(message = "业务类型不能为空")
    private String P8_biz;
    /**
     * 银行联行号
     * 银行联行号 对公联行号必填
     */
    private String P9_bankUnionCode;
    /**
     * 手续费收取方式
     * PAYER:付款方收取手续费
     * RECEIVER:收款方收取手续费
     */
    @NotNull(message = "手续费收取方式不能为空")
    private String P10_feeType;
    /**
     * 是否加急
     * 固定值
     * true：加急
     */
    @NotNull(message = "是否加急不能为空")
    private String P11_urgency;
    /**
     * 打款备注
     */
    private String P12_summary;
    /**
     * 订单状态通知地址
     * 商户用于接收合利宝通知代付结果的地址
     */
    @NotNull(message = "订单状态通知地址不能为空")
    @SignExclude
    private String notifyUrl;

    /**
     * 签名
     */
    @SignExclude
    private String sign;
}
