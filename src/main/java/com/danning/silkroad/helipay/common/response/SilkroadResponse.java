package com.danning.silkroad.helipay.common.response;

import com.danning.silkroad.helipay.common.constents.BizProcCode;
import com.danning.silkroad.helipay.utils.JsonUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 类名称：SilkroadResponse<br>
 * 类描述：<br>
 * 创建时间：2019年01月24日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Slf4j
@Getter
public class SilkroadResponse<T> implements Serializable {
    private static final long serialVersionUID = -1384637287031663629L;
    /**
     * 业务是否成功
     */
    @Setter
    private Boolean success = Boolean.TRUE;
    /**
     * 响应代码
     */
    private final String code;

    /**
     * 响应消息
     */
    @Setter
    private  String msg;
    /**
     * 响应数据
     */
    private final T data;
    public SilkroadResponse(String code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> SilkroadResponse<T> ok(T data) {

        LOGGER.info("业务成功 {}", data);
        return new SilkroadResponse<>(BizProcCode.SUCCESS, "成功", data);
    }

    public static <T> SilkroadResponse<T> ok(String resultMsg, T data) {
        LOGGER.info("业务成功 {} {}", resultMsg, JsonUtils.getInstance().toJson(data));
        return new SilkroadResponse<>(BizProcCode.SUCCESS, resultMsg, data);
    }

    public static <T> SilkroadResponse<T> ok(String resultCode, String resultMsg, T data) {
        LOGGER.info("业务成功 【{}】 {} {}", resultCode, resultMsg, JsonUtils.getInstance().toJson(data));
        return new SilkroadResponse<>(resultCode, resultMsg, data);
    }

    public static <T> SilkroadResponse<T> fail(String resultCode, String resultMsg, T data) {
        LOGGER.info("业务失败 【{}】 {} {}", resultCode, resultMsg, JsonUtils.getInstance().toJson(data));
        SilkroadResponse goldGoodsResponse = new SilkroadResponse<>(resultCode, resultMsg, data);
        goldGoodsResponse.setSuccess(Boolean.FALSE);
        return goldGoodsResponse;
    }

    public static <T> SilkroadResponse<T> fail(String resultMsg, T data) {
        SilkroadResponse goldGoodsResponse = new SilkroadResponse<>(BizProcCode.FAIL, resultMsg, data);
        goldGoodsResponse.setSuccess(Boolean.FALSE);
        return goldGoodsResponse;
    }
    public static <T> SilkroadResponse<T> fail(String resultMsg) {
        return fail(resultMsg,null);
    }
}
