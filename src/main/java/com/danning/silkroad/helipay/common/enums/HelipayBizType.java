package com.danning.silkroad.helipay.common.enums;

/**
 * 类名称：HelipayBizType<br>
 * 类描述：<br>
 * 创建时间：2019年01月27日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
public enum HelipayBizType {
    _TRANSFER("Transfer","单笔代付"),
    _TRANSFERQUERY("TransferQuery","单笔代付查询"),
    _AGREEMENTPAYBINDCARDVALIDATECODE("AgreementPayBindCardValidateCode","鉴权绑卡短信"),
    _QUICKPAYBINDCARD("QuickPayBindCard","鉴权绑卡"),
    _QUICKPAYBINDPAYVALIDATECODE("QuickPayBindPayValidateCode","绑卡支付短信"),
    _QUICKPAYBINDPAY("QuickPayBindPay","绑卡支付"),
    _QUICKPAYQUERY("QuickPayQuery","订单查询"),
    _BANKCARDUNBIND("BankCardUnbind","银行卡解绑"),
    _BANKCARDBINDLIST("BankCardbindList","用户绑定银行卡信息查询"),
    _QUICKPAYCONFIRMPAY("QuickPayConfirmPay","快捷支付异步通知");

    public final String value;
    public final String name;

    HelipayBizType(String value,String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.value;
    }

    /**
     * 根据枚举类型的字符串值 获取 相应的枚举变量
     */
    public static HelipayBizType get(String str) {
        HelipayBizType[] cs = HelipayBizType.class.getEnumConstants();
        for (HelipayBizType c : cs) {
            if (c.toString().equals(str)) {
                return c;
            }
        }
        return null;
    }
}
