package com.danning.silkroad.helipay.config;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名称：SilkroadJackson2HttpMessageConverter<br>
 * 类描述：<br>
 * 创建时间：2019年01月28日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
public class SilkroadJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter{

    public SilkroadJackson2HttpMessageConverter(){
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        mediaTypes.add(MediaType.TEXT_HTML);
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        setSupportedMediaTypes(mediaTypes);
    }
}
