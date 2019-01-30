package com.danning.silkroad.helipay.service;

import com.danning.silkroad.helipay.BaseTestCase;
import com.danning.silkroad.helipay.common.constents.AuthConstants;
import com.danning.silkroad.helipay.common.enums.HelipayBizType;
import com.danning.silkroad.helipay.common.response.SilkroadResponse;
import com.danning.silkroad.helipay.common.response.builder.SinglePaymentBizResponse;
import com.danning.silkroad.helipay.common.response.builder.SinglePaymentQueryBizResponse;
import com.danning.silkroad.helipay.utils.JsonUtils;
import com.danning.silkroad.helipay.vo.SinglePaymentQueryVO;
import com.danning.silkroad.helipay.vo.SinglePaymentVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类名称：TransferServiceTest<br>
 * 类描述：<br>
 * 创建时间：2019年01月26日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
public class TransferServiceTest extends BaseTestCase {

    @Autowired
    private TransferService transferService;

    @Test
    public void singlePayment(){
        SinglePaymentVO vo = new SinglePaymentVO();
        vo.setP1_bizType(HelipayBizType._TRANSFER.value);
        vo.setP2_orderId("120012312300034");
        vo.setP3_customerNumber(AuthConstants.CUSTOMERNUMBER);
        vo.setP4_amount("0.01");
        vo.setP5_bankCode("BOCO");
        vo.setP6_bankAccountNo("6222620910022100139");
        vo.setP7_bankAccountName("陈超");
        vo.setP8_biz("B2C");
        vo.setP9_bankUnionCode("");
        vo.setP10_feeType("PAYER");
        vo.setP11_urgency("true");
        vo.setP12_summary("结算款");
        vo.setNotifyUrl("http://150hy91322.51mypc.cn:57396/trade/df/paymentResultNotice");
        SilkroadResponse<SinglePaymentBizResponse> result = transferService.singlePayment(vo);
        System.out.println("---"+JsonUtils.getInstance().toJson(result));
    }

    @Test
    public void singlePaymentQuery(){
        SinglePaymentQueryVO vo = new SinglePaymentQueryVO();
        vo.setP1_bizType(HelipayBizType._TRANSFERQUERY.value);
        vo.setP2_orderId("120012312300034");
        vo.setP3_customerNumber(AuthConstants.CUSTOMERNUMBER);
        SilkroadResponse<SinglePaymentQueryBizResponse> result = transferService.singlePaymentQuery(vo);
        System.out.println("---"+JsonUtils.getInstance().toJson(result));
    }
}
