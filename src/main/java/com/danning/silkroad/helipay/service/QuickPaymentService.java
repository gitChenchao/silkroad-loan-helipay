package com.danning.silkroad.helipay.service;

import com.danning.silkroad.helipay.common.response.SilkroadResponse;
import com.danning.silkroad.helipay.common.response.builder.*;
import com.danning.silkroad.helipay.vo.*;

/**
 * 类名称：QuickPaymentService<br>
 * 类描述：<br>
 * 创建时间：2019年01月27日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
public interface QuickPaymentService {
    /**
     * 合利宝快捷支付鉴权绑卡短信接口
     * @param request
     * @return
     */
    SilkroadResponse<AuthBindCardSmsResp> authBindCardSms(AuthBindCardSmsVO request);

    /**
     * 合利宝快捷支付鉴权绑卡接口
     * @param request
     * @return
     */
    SilkroadResponse<AuthBindCardResp> authBindCard(AuthBindCardVO request);

    /**
     * 合利宝快捷支付绑卡支付短信接口
     * @param request
     * @return
     */
    SilkroadResponse<BindCardPaymentSmsResp> bindCardPaymentSms(BindCardPaymentSmsVO request);

    /**
     * 合利宝快捷支付绑卡支付接口
     * @param request
     * @return
     */
    SilkroadResponse<BindCardPaymentResp> bindCardPayment(BindCardPaymentVO request);

    /**
     * 合利宝快捷支付订单查询接口
     * @param request
     * @return
     */
    SilkroadResponse<QuickPayQueryResp> quickPayQuery(QuickPayQueryVO request);

    /**
     * 合利宝快捷支付银行卡解绑接口
     * @param request
     * @return
     */
    SilkroadResponse<BankCardUnbindResp> bankCardUnbind(BankCardUnbindVO request);

    /**
     * 合利宝快捷支付用户绑定银行卡信息查询接口
     * @param request
     * @return
     */
    SilkroadResponse<BankCardbindListResp> bankCardbindList(BankCardbindListVO request);

    /**
     * 通知回调
     * @param vo
     * @return
     */
    String syncNotice(SyncNoticeVO vo);
}
