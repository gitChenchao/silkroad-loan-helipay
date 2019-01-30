package com.danning.silkroad.helipay.utils;

import com.danning.silkroad.helipay.annotation.FieldEncrypt;
import com.danning.silkroad.helipay.annotation.SignExclude;
import com.danning.silkroad.helipay.common.constents.AuthConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MessageHandle {

	private static final String ENCRYPTION_KEY = "encryptionKey";
	private static final String SPLIT = "&";
	private static final String SIGN = "sign";


	/**
	 * 转换成map
	 */
	public static Map getReqestMap(Object bean) throws Exception {
		Map retMap = new HashMap();
		boolean isEncrypt = false;
		String aesKey = AES.generateString(16);
		StringBuilder sb = new StringBuilder();
		Class clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String key = field.toString().substring(field.toString().lastIndexOf(".") + 1);
			String value = (String) field.get(bean);
			if (value == null) {
				value = "";
			}
			//查看是否有需要加密字段的注解,有则加密
			//这部分是将需要加密的字段先进行加密
			if (field.isAnnotationPresent(FieldEncrypt.class) && StringUtils.isNotEmpty(value)) {
				isEncrypt = true;
				value = AES.encryptToBase64(value, aesKey);
			}
			//字段没有@SignExclude注解的拼签名串
			//这部分是把需要参与签名的字段拼成一个待签名的字符串
			if (!field.isAnnotationPresent(SignExclude.class)) {
				sb.append(SPLIT);
				sb.append(value);
			}

			retMap.put(key, value);
		}
		//如果有加密的，需要用合利宝的公钥将AES加密的KEY进行加密使用BASE64编码上送
		if (isEncrypt) {
			PublicKey publicKey = RSA.getPublicKey(AuthConstants.publickey);
			String encrytionKey = RSA.encodeToBase64(aesKey, publicKey, ConfigureEncryptAndDecrypt.KEY_ALGORITHM);
			retMap.put(ENCRYPTION_KEY, encrytionKey);
		}
		LOGGER.info("【加密】原签名串：" + sb.toString());
		//使用商户的私钥进行签名
		PrivateKey privateKey = RSA.getPrivateKey(AuthConstants.privatekey);
		String sign = RSA.sign(sb.toString(), privateKey);
		retMap.put(SIGN, sign);
		LOGGER.info("【加密】签名sign：" + sign);
		return retMap;
	}

	public static boolean checkSignMd5(Object bean) throws Exception {
		boolean flag = false;
		StringBuilder sb = new StringBuilder();
		Class clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		//合利宝返回的sign
		String sign = "";
		//生成的sign
		String localSign = "";
		for (Field field : fields) {
			field.setAccessible(true);
			String key = field.toString().substring(field.toString().lastIndexOf(".") + 1);
			String value = (String) field.get(bean);
			if (value == null) {
				value = "";
			}
			if (SIGN.equals(key)) {
				sign = value;
			}
			//字段没有@SignExclude注解的拼签名串
			//这部分是把需要参与签名的字段拼成一个待签名的字符串
			if (!field.isAnnotationPresent(SignExclude.class)) {
				sb.append(SPLIT);
				sb.append(value);
			}
		}
		sb.append(SPLIT);
		sb.append(AuthConstants.signKey);
		LOGGER.info("【验签】response验签原签名串：" + sb.toString());
		//使用合利宝秘钥进行验签
		localSign = Disguiser.disguiseMD5(sb.toString());
		if(!StringUtils.isEmpty(localSign) && localSign.equals(sign)){
			flag = true;
		}else{
			flag = false;
		}
		if (flag) {
			LOGGER.info("【验签】验签成功");
		} else {
			LOGGER.info("【验签】验签失败");
		}
		return flag;
	}

	/**
	 * 封装请求参数
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static MultiValueMap<String,String> getRequestMultiValueMap(Object bean) throws Exception {
		MultiValueMap<String,String> retMap = new LinkedMultiValueMap();
		boolean isEncrypt = false;
		String aesKey = AES.generateString(16);
		StringBuilder sb = new StringBuilder();
		Class clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String key = field.toString().substring(field.toString().lastIndexOf(".") + 1);
			String value = (String) field.get(bean);
			if (value == null) {
				value = "";
			}
			//查看是否有需要加密字段的注解,有则加密
			//这部分是将需要加密的字段先进行加密
			if (field.isAnnotationPresent(FieldEncrypt.class) && StringUtils.isNotEmpty(value)) {
				isEncrypt = true;
				value = AES.encryptToBase64(value, aesKey);
			}
			//字段没有@SignExclude注解的拼签名串
			//这部分是把需要参与签名的字段拼成一个待签名的字符串
			if (!field.isAnnotationPresent(SignExclude.class)) {
				sb.append(SPLIT);
				sb.append(value);
			}
			retMap.add(key, value);
		}
		//如果有加密的，需要用合利宝的公钥将AES加密的KEY进行加密使用BASE64编码上送
		if (isEncrypt) {
			PublicKey publicKey = RSA.getPublicKeyByCert(AuthConstants.CERT_PATH);
			String encrytionKey = RSA.encodeToBase64(aesKey, publicKey, ConfigureEncryptAndDecrypt.KEY_ALGORITHM);
			retMap.add(ENCRYPTION_KEY, encrytionKey);
		}
		LOGGER.info("【加密】原签名串：" + sb.toString());
		//使用商户的私钥进行签名
		PrivateKey privateKey = RSA.getPrivateKey(AuthConstants.PFX_PATH, AuthConstants.PFX_PWD);
		String sign = RSA.sign(sb.toString(), privateKey);
		retMap.add(SIGN, sign);
		LOGGER.info("【加密】签名sign：" + sign);
		return retMap;
	}

	public static Map<String,String> getReqestMapCert(Object bean) throws Exception {
		Map retMap = new HashMap();
		boolean isEncrypt = false;
		String aesKey = AES.generateString(16);
		StringBuilder sb = new StringBuilder();
		Class clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String key = field.toString().substring(field.toString().lastIndexOf(".") + 1);
			String value = (String) field.get(bean);
			if (value == null) {
				value = "";
			}
			//查看是否有需要加密字段的注解,有则加密
			//这部分是将需要加密的字段先进行加密
			if (field.isAnnotationPresent(FieldEncrypt.class) && StringUtils.isNotEmpty(value)) {
				isEncrypt = true;
				value = AES.encryptToBase64(value, aesKey);
			}
			//字段没有@SignExclude注解的拼签名串
			//这部分是把需要参与签名的字段拼成一个待签名的字符串
			if (!field.isAnnotationPresent(SignExclude.class)) {
				sb.append(SPLIT);
				sb.append(value);
			}
			retMap.put(key, value);
		}
		//如果有加密的，需要用合利宝的公钥将AES加密的KEY进行加密使用BASE64编码上送
		if (isEncrypt) {
			PublicKey publicKey = RSA.getPublicKeyByCert(AuthConstants.CERT_PATH);
			String encrytionKey = RSA.encodeToBase64(aesKey, publicKey, ConfigureEncryptAndDecrypt.KEY_ALGORITHM);
			retMap.put(ENCRYPTION_KEY, encrytionKey);
		}
		LOGGER.info("【加密】原签名串：" + sb.toString());
		//使用商户的私钥进行签名
		PrivateKey privateKey = RSA.getPrivateKey(AuthConstants.PFX_PATH, AuthConstants.PFX_PWD);
		String sign = RSA.sign(sb.toString(), privateKey);
		retMap.put(SIGN, sign);
		LOGGER.info("【加密】签名sign：" + sign);
		return retMap;
	}

	/**
	 * 验签-合利宝公钥验签
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static boolean checkSignCert(Object bean) throws Exception {
		boolean flag = false;
		StringBuilder sb = new StringBuilder();
		Class clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		String sign = "";
		for (Field field : fields) {
			field.setAccessible(true);
			String key = field.toString().substring(field.toString().lastIndexOf(".") + 1);
			String value = (String) field.get(bean);
			if (value == null) {
				value = "";
			}
			if (SIGN.equals(key)) {
				sign = value;
			}
			//字段没有@SignExclude注解的拼签名串
			//这部分是把需要参与签名的字段拼成一个待签名的字符串
			if (!field.isAnnotationPresent(SignExclude.class)) {
				sb.append(SPLIT);
				sb.append(value);
			}
		}
		LOGGER.info("【验签】response验签原签名串：" + sb.toString());
		//使用合利宝的公钥进行验签
		PublicKey publicKey = RSA.getPublicKeyByCert(AuthConstants.CERT_PATH);
		flag = RSA.verifySign(sb.toString(), sign, publicKey);
		if (flag) {
			LOGGER.info("【验签】验签成功");
		} else {
			LOGGER.info("【验签】验签失败");
		}
		return flag;
	}
}
