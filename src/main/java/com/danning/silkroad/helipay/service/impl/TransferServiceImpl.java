package com.danning.silkroad.helipay.service.impl;

import com.danning.silkroad.helipay.common.constents.AuthConstants;
import com.danning.silkroad.helipay.common.response.SilkroadResponse;
import com.danning.silkroad.helipay.common.response.builder.SinglePaymentBizResponse;
import com.danning.silkroad.helipay.common.response.builder.SinglePaymentQueryBizResponse;
import com.danning.silkroad.helipay.service.TransferService;
import com.danning.silkroad.helipay.utils.*;
import com.danning.silkroad.helipay.vo.PaymentResultNoticeVO;
import com.danning.silkroad.helipay.vo.SinglePaymentQueryVO;
import com.danning.silkroad.helipay.vo.SinglePaymentVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 类名称：TransferServiceImpl<br>
 * 类描述：<br>
 * 创建时间：2019年01月26日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Service
@Slf4j
public class TransferServiceImpl implements TransferService {

    private final RestTemplate restTemplate;

    public TransferServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public SilkroadResponse<SinglePaymentBizResponse> singlePayment(SinglePaymentVO request) {
        LOGGER.info("【单笔代付】合利宝请求参数:"+JsonUtils.getInstance().toJson(request));
        SinglePaymentBizResponse response = null;
        try {
            //1、参数校验
            Map<String, String> errorMap = ValidatorUtil.validate(request);
            if (errorMap.size() > 0) {
                LOGGER.error("请求参数【{}】异常", JsonUtils.getInstance().toJson(errorMap));
                return SilkroadResponse.fail("【单笔代付】请求参数异常");
            }
            //2、组装请求报文
            Map reqestMap = MessageHandle.getReqestMap(request);
            //3、发送请求
            Map<String, Object> resultMap = HttpClientService.getHttpResp(reqestMap, AuthConstants.urlTransfer);
            LOGGER.info("【单笔代付】合利宝返回结果:"+JsonUtils.getInstance().toJson(resultMap));
            //4、返回报文拼装
            String resultJson = StringEscapeUtils.unescapeJava(resultMap.get("response").toString());
            response = JsonUtils.getInstance().toBean(resultJson,SinglePaymentBizResponse.class);
            //5、验签
            boolean checkSign = MessageHandle.checkSignMd5(response);
            LOGGER.info("【单笔代付】验签结果:"+checkSign);
            //6、返回
            if(checkSign){
                return SilkroadResponse.ok("【单笔代付】请求成功",response);
            }else{
                return SilkroadResponse.fail("【单笔代付】验签失败",null);
            }
        } catch (Exception e) {
            LOGGER.error("【单笔代付】发生异常",e);
            return SilkroadResponse.fail("【单笔代付】发生异常");
        }
    }

    @Override
    public SilkroadResponse<SinglePaymentQueryBizResponse> singlePaymentQuery(SinglePaymentQueryVO request) {
        LOGGER.info("【单笔代付查询】请求合利宝参数:"+JsonUtils.getInstance().toJson(request));
        SinglePaymentQueryBizResponse response = null;
        try {
            //1、参数校验
            Map<String, String> errorMap = ValidatorUtil.validate(request);
            if (errorMap.size() > 0) {
                LOGGER.error("请求参数【{}】异常", JsonUtils.getInstance().toJson(errorMap));
                return SilkroadResponse.fail("【单笔代付查询】请求参数异常");
            }
            //2、组装请求报文
            Map reqestMap = MessageHandle.getReqestMap(request);
            //3、发送请求
            Map<String, Object> resultMap = HttpClientService.getHttpResp(reqestMap, AuthConstants.urlTransfer);
            LOGGER.info("【单笔代付查询】合利宝返回结果:"+JsonUtils.getInstance().toJson(resultMap));
            //4、返回报文拼装
            String resultJson = StringEscapeUtils.unescapeJava(resultMap.get("response").toString());
            response = JsonUtils.getInstance().toBean(resultJson,SinglePaymentQueryBizResponse.class);
            //5、验签
            boolean checkSign = MessageHandle.checkSignMd5(response);
            LOGGER.info("【单笔代付查询】验签结果:"+checkSign);
            //6、返回
            if(checkSign){
                return SilkroadResponse.ok("【单笔代付查询】请求成功",response);
            }else{
                return SilkroadResponse.fail("【单笔代付查询】验签失败",null);
            }
        } catch (Exception e) {
            LOGGER.error("【单笔代付查询】发生异常",e);
            return SilkroadResponse.fail("【单笔代付查询】发生异常");
        }
    }

    @Override
    public String paymentResultNotice(PaymentResultNoticeVO vo) {
        try {
            //1、参数校验
            Map<String, String> errorMap = ValidatorUtil.validate(vo);
            if (errorMap.size() > 0) {
                LOGGER.error("请求参数【{}】异常", JsonUtils.getInstance().toJson(errorMap));
                return "fail";
            }
            //2、验签
            boolean checkSign = MessageHandle.checkSignMd5(vo);
            if(!checkSign){
                LOGGER.info("【代付回调通知】验签失败");
                return "fail";
            }
            LOGGER.info("【代付回调通知】验签成功");
            //3、todo 业务逻辑处理
            return "success";
        } catch (Exception e) {
            LOGGER.error("【代付回调通知】操作异常",e);
            return "fail";
        }
    }
}
