/*
 * File: JsonUtils.java
 * CreateTime: 2015年10月17日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package com.danning.silkroad.helipay.utils;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * TypeName: JsonUtils
 * <p>
 * 实现或继承{@link FilterMixIn}接口可针对不同的场景定制化JSON,
 * 使用本工具类的{@link JsonUtils#toJsonExcludeProperty(Object, Class, String...)}
 * 或{@link JsonUtils#toJsonIncludeProperty(Object, Class, String...)}方法可动态过滤对象属性.<br/>
 * 例如:
 * <blockquote>
 * <code>
 *
 * <tt>@JsonFilter("testDTO")</tt><br/> public interface MyFilterMixIn extends FilterMixIn {<br/>
 * <code>&nbsp;&nbsp;&nbsp;&nbsp;@JsonIgnore Long getId();<code><br/>
 * <code>&nbsp;&nbsp;&nbsp;&nbsp;@JsonProperty(value="name") String getUserName();<code><br/>
 * }<br/>
 * 然后调用toJson(sub, new SimpleDateFormat("yyyy-MM-dd"), true, false, MyFilterMixIn.class, new String[]{"xxx","xxx"})方法动态过滤属性
 * </code>
 * </blockquote>
 * <p>
 * CreateTime: 2019年1月24日
 * <p>
 * ---------------------------------------------------<br/>
 * UpdateTime: 2019年1月24日<br/>
 * 增加2个方法以方便过滤属性和使用MixIn模板接口:<br/>
 * 1.toJsonExcludeProperty<br/>
 * 2.toJsonIncludeProperty<br/>
 * ---------------------------------------------------<br/>
 * @author chenchao
 */
@Slf4j
public class JsonUtils {
    private final static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final  DateFormat DEFAULT_DATEFORMAT = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
    private final static String DEFAULT_TIMEZONE = "GMT+8:00";
    private static JsonUtils obj;
    private ObjectMapper mapper;
    private String timeZone = DEFAULT_TIMEZONE;

    private JsonUtils() {
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static JsonUtils getInstance() {
        if (obj == null) {
            synchronized (JsonUtils.class) {
                if (obj == null) {
                    return new JsonUtils();
                }
            }
        }
        return obj;
    }

    private static void exceptionHandler(Exception e) {
        IllegalStateException ex = new IllegalStateException(e.getMessage(), e);
        LOGGER.error(ex.getMessage(), ex);
        throw ex;
    }


    public JavaType getListType(Class<?> elementClasses) {
        return mapper.getTypeFactory().constructParametricType(List.class, elementClasses);
    }


    /**
     * 将JAVA对象序列化为JSON字符串.此为原始方法
     * <P>本方法可设置时间格式,<code>null</code>值过滤开关,包含/排除开关,包含/排除的属性数组
     *
     * @param value       被序列化的对象
     * @param dateFormat  时间格式,默认格式为<tt>yyyy-MM-dd HH:mm:ss,SSS</tt>,默认时区为GMT+8
     * @param nullskip    是否跳过<code>null</code>值属性,默认为不跳过
     * @param isInclude   包含/排除开关,默认为排除
     * @param mixInClass  MixIn接口(子接口,实现类)Class对象
     * @param filteFields 被包含/排除的属性名称数组
     * @return JSON字符串
     */
    public String toJson(Object value, DateFormat dateFormat, boolean nullskip, boolean isInclude, Class<? extends FilterMixIn> mixInClass, String... filteFields) {
        if (nullskip) {
            mapper.setSerializationInclusion(Include.NON_NULL);
            LOGGER.debug("JSON - 序列化时跳过空值属性");
        }
        if (dateFormat != null) {
            if (timeZone != null) {
                dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
                LOGGER.debug("JSON-设置时区为:{}", timeZone);
            }
            mapper.setDateFormat(dateFormat);
            if (dateFormat instanceof SimpleDateFormat) {
                SimpleDateFormat f = (SimpleDateFormat) dateFormat;
                LOGGER.debug("JSON-设置时间格式为:{}", f.toPattern());
            } else if (dateFormat instanceof StdDateFormat) {
                LOGGER.debug("JSON-设置时间格式为:{}", "yyyy-MM-ddThh:mm:ssZ");
            }
        }
        if (filteFields != null && filteFields.length > 0) {
            String filterId = mixInClass.getAnnotation(JsonFilter.class).value();
            FilterProvider filters;
            if (isInclude) {
                filters = new SimpleFilterProvider().addFilter(filterId,
                        SimpleBeanPropertyFilter.filterOutAllExcept(filteFields));
                LOGGER.debug("JSON-设置属性过滤规则:仅包含![{}]", Arrays.toString(filteFields));
            } else {
                filters = new SimpleFilterProvider().addFilter(filterId,
                        SimpleBeanPropertyFilter.serializeAllExcept(filteFields));
                LOGGER.debug("JSON-设置属性过滤规则:仅排除![{}]", Arrays.toString(filteFields));
            }
            mapper.setFilterProvider(filters);
        }
        if (mixInClass != null) {
            mapper.addMixIn(value.getClass(), mixInClass);
        }
        String result = null;
        try {
            result = mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }


    public String toJsonExcludeProperty(Object value, Class<? extends FilterMixIn> mixInClass, String... filteFields) {
        return toJson(value, DEFAULT_DATEFORMAT, false, false, mixInClass, filteFields);
    }


    public String toJsonIncludeProperty(Object value, Class<? extends FilterMixIn> mixInClass, String... filteFields) {
        return toJson(value, DEFAULT_DATEFORMAT, false, true, mixInClass, filteFields);
    }


    public String toJson(Object value, Class<? extends FilterMixIn> mixInClass) {
        return toJson(value, DEFAULT_DATEFORMAT, false, false, mixInClass);
    }

    /**
     * 将JAVA对象序列化为JSON字符串,重载方法1
     *
     * @param value      被序列化的对象
     * @param dateFormat 时间格式,默认格式为<tt>yyyy-MM-dd HH:mm:ss,SSS</tt>,默认时区为GMT+8
     * @param nullskip   是否跳过<code>null</code>值属性,默认为不跳过
     * @return JSON字符串
     */
    public String toJson(Object value, DateFormat dateFormat, boolean nullskip) {
        return toJson(value, dateFormat, nullskip, false, null);
    }

    /**
     * 将JAVA对象序列化为JSON字符串,重载方法2
     *
     * @param value      被序列化的对象
     * @param dateFormat 时间格式,默认格式为<tt>yyyy-MM-dd HH:mm:ss,SSS</tt>,默认时区为GMT+8
     * @return JSON字符串
     */
    public String toJson(Object value, DateFormat dateFormat) {
        return toJson(value, dateFormat, false);
    }

    /**
     * 将JAVA对象序列化为JSON字符串,重载方法3
     *
     * @param value 被序列化的对象
     * @return JSON字符串
     */
    public String toJson(Object value) {
        return toJson(value, DEFAULT_DATEFORMAT);
    }

    /**
     * 将JSON字符串反序列化为JAVA对象
     *
     * @param json       JSON字符串
     * @param javaType   JAVA对象类型
     * @param dateFormat 时间格式,默认格式为<tt>yyyy-MM-dd HH:mm:ss,SSS</tt>,默认时区为GMT+8
     * @return 指定类型的JAVA对象
     */
    public <T> T toBean(String json, Class<T> javaType, DateFormat dateFormat) {
        if (dateFormat != null) {
            if (timeZone != null) {
                dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
            }
            mapper.setDateFormat(dateFormat);
        }
        T t = null;
        try {
            t = mapper.readValue(json, javaType);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return t;
    }

    /**
     * 将JSON字符串反序列化为JAVA对象
     *
     * @param json     JSON字符串
     * @param javaType JAVA对象类型
     * @return 指定类型的JAVA对象
     */
    public <T> T toBean(String json, Class<T> javaType) {
        return toBean(json, javaType, DEFAULT_DATEFORMAT);
    }

    /**
     * 将JSON字符串反序列化为集合
     *
     * @param <E>     实现了序列化接口的Java类
     * @param json    JSON字符串
     * @param eleType 集合的元素类型
     * @return {@link List}集合
     */
    public <E> List<E> toList(String json, Class<E> eleType) {
        List<E> list = null;
        try {
            list = mapper.readValue(json, getListType(eleType));
        } catch (Exception e) {
            exceptionHandler(e);
        }
        return list;
    }

    /**
     * 获取JSON字符串中指定key对应的value
     * @param json json字符串
     * @param key 想查找的关键字.可能存在多个重复的关键字
     * @return 指定关键字对应的值.如果该关键字在json串中有多个,将返回多个值
     */
    public List<String> getValue(String json, String key) {
        List<String> values = null;
        try {
            JsonNode root = mapper.readTree(json);
            values = root.findValuesAsText(key);
        } catch (IOException e) {
            exceptionHandler(e);
        }
        return values;
    }

    /**
     * 将JSON字符串反序列化为Map
     *
     * @param json json字符串
     * @return JSON字符串对应的映射.在出现异常时,将返回null
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> toMap(String json) {
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(json, Map.class);
        } catch (Exception e) {
            exceptionHandler(e);
        }
        return map;
    }

    public <T> T toBean(String json, TypeReference<T> tTypeReference) {
        if (timeZone != null) {
            DEFAULT_DATEFORMAT.setTimeZone(TimeZone.getTimeZone(timeZone));
        }
        mapper.setDateFormat(DEFAULT_DATEFORMAT);
        T t = null;
        try {
            t = mapper.readValue(json, tTypeReference);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);

        }
        return t;
    }

}
