package com.danning.silkroad.helipay.service;

import com.danning.silkroad.helipay.common.response.SilkroadResponse;
import com.danning.silkroad.helipay.common.response.builder.SinglePaymentBizResponse;
import com.danning.silkroad.helipay.common.response.builder.SinglePaymentQueryBizResponse;
import com.danning.silkroad.helipay.vo.PaymentResultNoticeVO;
import com.danning.silkroad.helipay.vo.SinglePaymentQueryVO;
import com.danning.silkroad.helipay.vo.SinglePaymentVO;

/**
 * 类名称：TransferService<br>
 * 类描述：合利宝-代付相关接口<br>
 * 创建时间：2019年01月26日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
public interface TransferService {

    /**
     * 单笔代付
     * @param request
     * @return
     */
    SilkroadResponse<SinglePaymentBizResponse> singlePayment(SinglePaymentVO request);

    /**
     * 单笔代付查询接口
     * @param request
     * @return
     */
    SilkroadResponse<SinglePaymentQueryBizResponse> singlePaymentQuery(SinglePaymentQueryVO request);

    /**
     *合利宝支付回调接口
     * @param vo
     * @return
     */
    String paymentResultNotice(PaymentResultNoticeVO vo);
}
