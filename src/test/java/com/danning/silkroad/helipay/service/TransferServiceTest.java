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
        SinglePaymentVO vo = SinglePaymentVO.builder()
            .P1_bizType(HelipayBizType._TRANSFER.value)
            .P2_orderId("120012312300034")
            .P3_customerNumber(AuthConstants.CUSTOMERNUMBER)
            .P4_amount("0.01")
            .P5_bankCode("BOCO")
            .P6_bankAccountNo("6222620910022100139")
            .P7_bankAccountName("陈超")
            .P8_biz("B2C")
            .P9_bankUnionCode("")
            .P10_feeType("PAYER")
            .P11_urgency("true")
            .P12_summary("结算款")
            .notifyUrl("http://150hy91322.51mypc.cn:57396/trade/df/paymentResultNotice").build();
        SilkroadResponse<SinglePaymentBizResponse> result = transferService.singlePayment(vo);
        System.out.println("---"+JsonUtils.getInstance().toJson(result));
    }

    @Test
    public void singlePaymentQuery(){
        SinglePaymentQueryVO vo = SinglePaymentQueryVO.builder()
                .P1_bizType(HelipayBizType._TRANSFERQUERY.value)
                .P2_orderId("120012312300034")
                .P3_customerNumber(AuthConstants.CUSTOMERNUMBER).build();
        SilkroadResponse<SinglePaymentQueryBizResponse> result = transferService.singlePaymentQuery(vo);
        System.out.println("---"+JsonUtils.getInstance().toJson(result));
    }
}
