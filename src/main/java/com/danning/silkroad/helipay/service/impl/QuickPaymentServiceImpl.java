package com.danning.silkroad.helipay.service.impl;

import com.danning.silkroad.helipay.common.constents.AuthConstants;
import com.danning.silkroad.helipay.common.response.SilkroadResponse;
import com.danning.silkroad.helipay.common.response.builder.*;
import com.danning.silkroad.helipay.service.QuickPaymentService;
import com.danning.silkroad.helipay.utils.HttpClientService;
import com.danning.silkroad.helipay.utils.JsonUtils;
import com.danning.silkroad.helipay.utils.MessageHandle;
import com.danning.silkroad.helipay.utils.ValidatorUtil;
import com.danning.silkroad.helipay.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 类名称：QuickPaymentServiceImpl<br>
 * 类描述：<br>
 * 创建时间：2019年01月27日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Service
@Slf4j
public class QuickPaymentServiceImpl implements QuickPaymentService {

    private final RestTemplate restTemplate;

    public QuickPaymentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public SilkroadResponse<AuthBindCardSmsResp> authBindCardSms(AuthBindCardSmsVO request) {
        LOGGER.info("【鉴权绑卡短信】请求参数:"+JsonUtils.getInstance().toJson(request));
        try {
            //2、组装请求报文
            MultiValueMap<String,String> reqestMap = MessageHandle.getRequestMultiValueMap(request);
            //3、发送请求
            ResponseEntity<AuthBindCardSmsResp> result = restTemplate.postForEntity(AuthConstants.REQUEST_URL_QUICKPAY,reqestMap,AuthBindCardSmsResp.class);
            LOGGER.info("【鉴权绑卡短信】合利宝返回结果:"+JsonUtils.getInstance().toJson(result));
            //5、验签
            boolean checkSign = MessageHandle.checkSignCert(result.getBody());
            LOGGER.info("【鉴权绑卡短信】验签结果:"+checkSign);
            //5、返回
            if(checkSign){
                return SilkroadResponse.ok("【鉴权绑卡短信】请求成功",result.getBody());
            }else{
                return SilkroadResponse.fail("【鉴权绑卡短信】验签失败",null);
            }
        } catch (Exception e) {
            LOGGER.error("【鉴权绑卡短信】发生异常",e);
            return SilkroadResponse.fail("【鉴权绑卡短信】发生异常");
        }
    }

    @Override
    public SilkroadResponse<AuthBindCardResp> authBindCard(AuthBindCardVO request) {
        LOGGER.info("【鉴权绑卡】请求参数:"+JsonUtils.getInstance().toJson(request));
        try {
            //2、组装请求报文
            MultiValueMap<String,String> reqestMap = MessageHandle.getRequestMultiValueMap(request);
            //3、发送请求
            ResponseEntity<AuthBindCardResp> result = restTemplate.postForEntity(AuthConstants.REQUEST_URL_QUICKPAY,reqestMap,AuthBindCardResp.class);
            LOGGER.info("【鉴权绑卡】合利宝返回结果:"+JsonUtils.getInstance().toJson(result));
            //4、验签
            boolean checkSign = MessageHandle.checkSignCert(result.getBody());
            LOGGER.info("【鉴权绑卡】验签结果:"+checkSign);
            //5、返回
            if(checkSign){
                return SilkroadResponse.ok("【鉴权绑卡】请求成功",result.getBody());
            }else{
                return SilkroadResponse.fail("【鉴权绑卡】验签失败",null);
            }
        } catch (Exception e) {
            LOGGER.error("【鉴权绑卡】发生异常",e);
            return SilkroadResponse.fail("【鉴权绑卡】发生异常");
        }
    }

    @Override
    public SilkroadResponse<BindCardPaymentSmsResp> bindCardPaymentSms(BindCardPaymentSmsVO request) {
        LOGGER.info("【绑卡支付短信】请求参数:"+JsonUtils.getInstance().toJson(request));
        try {
            //2、组装请求报文
            MultiValueMap<String,String> reqestMap = MessageHandle.getRequestMultiValueMap(request);
            //3、发送请求
            ResponseEntity<BindCardPaymentSmsResp> result = restTemplate.postForEntity(AuthConstants.REQUEST_URL_QUICKPAY,reqestMap,BindCardPaymentSmsResp.class);
            LOGGER.info("【绑卡支付短信】合利宝返回结果:"+JsonUtils.getInstance().toJson(result));
            //4、验签
            boolean checkSign = MessageHandle.checkSignCert(result.getBody());
            LOGGER.info("【绑卡支付短信】验签结果:"+checkSign);
            //5、返回
            if(checkSign){
                return SilkroadResponse.ok("【绑卡支付短信】请求成功",result.getBody());
            }else{
                return SilkroadResponse.fail("【绑卡支付短信】验签失败",null);
            }
        } catch (Exception e) {
            LOGGER.error("【绑卡支付短信】发生异常",e);
            return SilkroadResponse.fail("【绑卡支付短信】发生异常");
        }
    }

    @Override
    public SilkroadResponse<BindCardPaymentResp> bindCardPayment(BindCardPaymentVO request) {
        LOGGER.info("【绑卡支付】请求参数:"+JsonUtils.getInstance().toJson(request));
        try {
            //2、组装请求报文
            MultiValueMap<String,String> reqestMap = MessageHandle.getRequestMultiValueMap(request);
            //3、发送请求
            ResponseEntity<BindCardPaymentResp> result = restTemplate.postForEntity(AuthConstants.REQUEST_URL_QUICKPAY,reqestMap,BindCardPaymentResp.class);
            LOGGER.info("【绑卡支付】合利宝返回结果:"+JsonUtils.getInstance().toJson(result));
            //4、验签
            boolean checkSign = MessageHandle.checkSignCert(result.getBody());
            LOGGER.info("【绑卡支付】验签结果:"+checkSign);
            //5、返回
            if(checkSign){
                return SilkroadResponse.ok("【绑卡支付】请求成功",result.getBody());
            }else{
                return SilkroadResponse.fail("【绑卡支付】验签失败",null);
            }
        } catch (Exception e) {
            LOGGER.error("【绑卡支付】发生异常",e);
            return SilkroadResponse.fail("【绑卡支付】发生异常");
        }
    }

    @Override
    public SilkroadResponse<QuickPayQueryResp> quickPayQuery(QuickPayQueryVO request) {
        LOGGER.info("【订单查询】请求参数:"+JsonUtils.getInstance().toJson(request));
        try {
            //2、组装请求报文
            MultiValueMap<String,String> reqestMap = MessageHandle.getRequestMultiValueMap(request);
            //3、发送请求
            ResponseEntity<QuickPayQueryResp> result = restTemplate.postForEntity(AuthConstants.REQUEST_URL_QUICKPAY,reqestMap,QuickPayQueryResp.class);
            LOGGER.info("【订单查询】合利宝返回结果:"+JsonUtils.getInstance().toJson(result));
            //4、验签
            boolean checkSign = MessageHandle.checkSignCert(result.getBody());
            LOGGER.info("【订单查询】验签结果:"+checkSign);
            //5、返回
            if(checkSign){
                return SilkroadResponse.ok("【订单查询】请求成功",result.getBody());
            }else{
                return SilkroadResponse.fail("【订单查询】验签失败",null);
            }
        } catch (Exception e) {
            LOGGER.error("【订单查询】发生异常",e);
            return SilkroadResponse.fail("【订单查询】发生异常");
        }
    }

    @Override
    public SilkroadResponse<BankCardUnbindResp> bankCardUnbind(BankCardUnbindVO request) {
        LOGGER.info("【银行卡解绑】请求参数:"+JsonUtils.getInstance().toJson(request));
        try {
            //2、组装请求报文
            MultiValueMap<String,String> reqestMap = MessageHandle.getRequestMultiValueMap(request);
            //3、发送请求
            ResponseEntity<BankCardUnbindResp> result = restTemplate.postForEntity(AuthConstants.REQUEST_URL_QUICKPAY,reqestMap,BankCardUnbindResp.class);
            LOGGER.info("【银行卡解绑】合利宝返回结果:"+JsonUtils.getInstance().toJson(result));
            //4、验签
            boolean checkSign = MessageHandle.checkSignCert(result.getBody());
            LOGGER.info("【银行卡解绑】验签结果:"+checkSign);
            //5、返回
            if(checkSign){
                return SilkroadResponse.ok("【银行卡解绑】请求成功",result.getBody());
            }else{
                return SilkroadResponse.fail("【银行卡解绑】验签失败",null);
            }
        } catch (Exception e) {
            LOGGER.error("【银行卡解绑】发生异常",e);
            return SilkroadResponse.fail("【银行卡解绑】发生异常");
        }
    }

    @Override
    public SilkroadResponse<BankCardbindListResp> bankCardbindList(BankCardbindListVO request) {
        LOGGER.info("【用户绑定银行卡信息查询】请求参数:"+JsonUtils.getInstance().toJson(request));
        try {
            //2、组装请求报文
            MultiValueMap<String,String> reqestMap = MessageHandle.getRequestMultiValueMap(request);
            //3、发送请求
            ResponseEntity<BankCardbindListResp> httpResp = restTemplate.postForEntity(AuthConstants.REQUEST_URL_QUICKPAY,reqestMap,BankCardbindListResp.class);
            LOGGER.info("【用户绑定银行卡信息查询】合利宝返回结果:"+JsonUtils.getInstance().toJson(httpResp));
            //4、验签
            boolean checkSign = MessageHandle.checkSignCert(httpResp.getBody());
            LOGGER.info("【用户绑定银行卡信息查询】验签结果:"+checkSign);
            //5、返回
            if(checkSign){
                return SilkroadResponse.ok("【用户绑定银行卡信息查询】请求成功",httpResp.getBody());
            }else{
                return SilkroadResponse.fail("【用户绑定银行卡信息查询】验签失败",null);
            }
        } catch (Exception e) {
            LOGGER.error("【用户绑定银行卡信息查询】发生异常",e);
            return SilkroadResponse.fail("【用户绑定银行卡信息查询】发生异常");
        }
    }

    @Override
    public String syncNotice(SyncNoticeVO vo) {
        try {
            //1、参数校验
            Map<String, String> errorMap = ValidatorUtil.validate(vo);
            if (errorMap.size() > 0) {
                LOGGER.error("请求参数【{}】异常", JsonUtils.getInstance().toJson(errorMap));
                return "fail";
            }
            //2、验签
            boolean checkSign = MessageHandle.checkSignCert(vo);
            if(!checkSign){
                LOGGER.info("【绑卡支付回调】验签失败");
                return "fail";
            }
            LOGGER.info("【绑卡支付回调】验签成功");
            //3、todo 处理业务逻辑
            return "success";
        } catch (Exception e) {
            LOGGER.error("【绑卡支付回调】操作异常",e);
            return "fail";
        }
    }
}
