package com.danning.silkroad.helipay.utils;

import com.danning.silkroad.helipay.common.constents.AuthConstants;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 
 * @ClassName: Disguiser
 * @Description: 哈希算法的工具类，提供SHA MD5 HMAC等算法
 *
 */
public class Disguiser {

	public static final String ENCODE = "UTF-8";
	private static final String KEY = "8data998mnwepxugnk03-2zirb";

	public static String disguiseMD5(String message) {

		if (null == message) {

			return null;
		}

		return disguiseMD5(message, ENCODE);
	}
	
	public static String disguise(String message){
    	return disguise(message+KEY,ENCODE);
    	
    }
	public static String disguise(String message,String encoding){
    	message = message.trim();
        byte value[];
        try{
            value = message.getBytes(encoding);
        }
        catch(UnsupportedEncodingException e){
            value = message.getBytes();
        }
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("SHA");
        }catch(NoSuchAlgorithmException e){
        	e.printStackTrace();
            return null;
        }
        return ConvertUtils.toHex(md.digest(value));
    }

	public static String disguiseMD5(String message, String encoding) {

		if (null == message || null == encoding) {

			return null;
		}

		message = message.trim();
		byte value[];
		try {
			value = message.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			value = message.getBytes();
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return ConvertUtils.toHex(md.digest(value));
	}

	public static void main(String[] args) {
		String d = Disguiser.disguiseMD5("Transfer0000C1800532519120012312300001157717830"+AuthConstants.privatekey);
		System.out.println(d);
	}

}
