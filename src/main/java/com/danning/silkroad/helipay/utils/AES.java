package com.danning.silkroad.helipay.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Random;


/**
 * AES加解密工具类
 * @version:
 */
public class AES {

	public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static void main(String[] args) {

		String key = generateString(16);
		System.out.println(key);
		String s = encryptToBase64("6228481143765162711", key);
		System.out.println(s);
		String e = decryptFromBase64(s, key);
		System.out.println(e);
	}
	/**
	 * 加密
	 * @return
	 */
	public static byte[] encrypt(byte[] data, byte[] key) {
		if(key.length!=16){
			throw new RuntimeException("Invalid AES key length (must be 16 bytes)");
		}
		try {
			SecretKeySpec secretKey = new SecretKeySpec(key, ConfigureEncryptAndDecrypt.AES_ALGORITHM);
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, ConfigureEncryptAndDecrypt.AES_ALGORITHM);
			Cipher cipher = Cipher.getInstance(ConfigureEncryptAndDecrypt.AES_ALGORITHM);// 创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, seckey);// 初始化
			byte[] result = cipher.doFinal(data);
			return result; // 加密
		} catch (Exception e){
			throw new RuntimeException("encrypt fail!", e);
		}
	}

	/**
	 * 解密
	 */
	public static byte[] decrypt(byte[] data, byte[] key) {
		if(key.length!=16){
			throw new RuntimeException("Invalid AES key length (must be 16 bytes)");
		}
		try {
			SecretKeySpec secretKey = new SecretKeySpec(key, ConfigureEncryptAndDecrypt.AES_ALGORITHM);
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, ConfigureEncryptAndDecrypt.AES_ALGORITHM);
			Cipher cipher = Cipher.getInstance(ConfigureEncryptAndDecrypt.AES_ALGORITHM);// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, seckey);// 初始化
			byte[] result = cipher.doFinal(data);
			return result; // 加密
		} catch (Exception e){
			throw new RuntimeException("decrypt fail!", e);
		}
	}
	
	public static String encryptToBase64(String data, String key){
		try {
			byte[] valueByte = encrypt(data.getBytes(ConfigureEncryptAndDecrypt.CHAR_ENCODING), key.getBytes(ConfigureEncryptAndDecrypt.CHAR_ENCODING));
			return new String(Base64.encode(valueByte));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("encrypt fail!", e);
		}
		
	}
	
	public static String decryptFromBase64(String data, String key){
		try {
			byte[] originalData = Base64.decode(data.getBytes());
			byte[] valueByte = decrypt(originalData, key.getBytes(ConfigureEncryptAndDecrypt.CHAR_ENCODING));
			return new String(valueByte, ConfigureEncryptAndDecrypt.CHAR_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("decrypt fail!", e);
		}
	}


	public static String generateString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return sb.toString();
	}


}
