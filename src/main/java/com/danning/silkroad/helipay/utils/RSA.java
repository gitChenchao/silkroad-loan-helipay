package com.danning.silkroad.helipay.utils;

import org.apache.commons.lang.ArrayUtils;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 私钥签名，私钥签名（只有私钥能签），公钥验证签名，确认发起人是私钥持有人
 * 公钥加密，公钥加密只有私钥能解密
 *
 * @author datou
 */
public class RSA {

    /**
     * String to hold name of the encryption padding.
     */
    public static final String NOPADDING = "RSA/NONE/NoPadding";

    public static final String RSANONEPKCS1PADDING = "RSA/NONE/PKCS1Padding";

    public static final String RSAECBPKCS1PADDING = "RSA/ECB/PKCS1Padding";

    public static final String PROVIDER = "BC";

    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    /**
     * 验证签名
     *
     * @param data      数据
     * @param sign      签名
     * @param publicKey 公钥
     * @return
     */
    public static boolean verifySign(byte[] data, byte[] sign,
                                     PublicKey publicKey) {
        try {
            Signature signature = Signature
                    .getInstance("MD5withRSA");
            signature.initVerify(publicKey);
            signature.update(data);
            boolean result = signature.verify(sign);
            return result;
        } catch (Exception e) {

            throw new RuntimeException("verifySign fail!", e);
        }
    }

    /**
     * 验证签名
     *
     * @param data     数据
     * @param sign     签名
     * @param pubicKey 公钥
     * @return
     */
    public static boolean verifySign(String data, String sign,
                                     PublicKey pubicKey) {
        try {
            byte[] dataByte = data
                    .getBytes("UTF-8");
            byte[] signByte = Base64.decode(sign
                    .getBytes("UTF-8"));
            return verifySign(dataByte, signByte, pubicKey);
        } catch (UnsupportedEncodingException e) {

            throw new RuntimeException("verifySign fail! data[" + data + "] sign[" + sign + "]", e);
        }
    }

    /**
     * 签名
     *
     * @param data
     * @param key
     * @return
     */
    public static byte[] sign(byte[] data, PrivateKey key) {
        try {
            Signature signature = Signature
                    .getInstance("MD5withRSA");
            signature.initSign(key);
            signature.update(data);
            return signature.sign();
        } catch (Exception e) {
            throw new RuntimeException("sign fail!", e);
        }
    }

    /**
     * 签名
     *
     * @param data
     * @param key
     * @return
     */
    public static String sign(String data, PrivateKey key) {
        try {
            byte[] dataByte = data.getBytes("UTF-8");
            return new String(Base64.encode(sign(dataByte, key)));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("sign fail!", e);
        }
    }

    /**
     * 加密
     *
     * @param data
     * @param key
     * @return
     */
    public static byte[] encrypt(byte[] data, Key key, String padding) {
        try {
            final Cipher cipher = Cipher.getInstance(padding, PROVIDER);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {

            throw new RuntimeException("encrypt fail!", e);
        }
    }

    /**
     * 加密
     *
     * @param data
     * @param key
     * @return
     */
    public static String encryptToBase64(String data, Key key, String padding) {
        try {
            return new String(Base64.encode(encrypt(
                    data.getBytes("UTF-8"),
                    key, padding)));
        } catch (Exception e) {
            throw new RuntimeException("encrypt fail!", e);
        }
    }

    /**
     * 解密
     *
     * @param data
     * @param key
     * @return
     */
    public static byte[] decrypt(byte[] data, Key key, String padding) {
        try {
            final Cipher cipher = Cipher.getInstance(padding, PROVIDER);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("encrypt fail!", e);
        }
    }

    /**
     * 解密
     *
     * @param data
     * @param key
     * @return
     */
    public static String decryptFromBase64(String data, Key key, String padding) {
        try {
            return new String(decrypt(Base64.decode(data.getBytes()), key, padding),
                    "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("encrypt fail!", e);
        }
    }

    public static void createKeyPairs(int size) throws Exception {
        // create the keys
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", PROVIDER);
        generator.initialize(size, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();
        PublicKey pubKey = pair.getPublic();
        PrivateKey privKey = pair.getPrivate();
        byte[] pk = pubKey.getEncoded();
        byte[] privk = privKey.getEncoded();
        String strpk = new String(Base64.encodeBase64(pk));
        String strprivk = new String(Base64.encodeBase64(privk));
        System.out.println("公钥:" + Arrays.toString(pk));
        System.out.println("私钥:" + Arrays.toString(privk));
        System.out.println("公钥Base64编码:" + strpk);
        System.out.println("私钥Base64编码:" + strprivk);
    }

    public static PublicKey getPublicKey(String base64EncodePublicKey) throws Exception {
        KeyFactory keyf = KeyFactory.getInstance("RSA", PROVIDER);
        X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(Base64.decodeBase64(base64EncodePublicKey.getBytes()));
        PublicKey pubkey = keyf.generatePublic(pubX509);
        return pubkey;
    }

    public static PrivateKey getPrivateKey(String base64EncodePrivateKey) throws Exception {
        KeyFactory keyf = KeyFactory.getInstance("RSA", PROVIDER);
        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(base64EncodePrivateKey.getBytes()));
        PrivateKey privkey = keyf.generatePrivate(priPKCS8);
        return privkey;
    }


    public static byte[] encode(String encodeString, Key key, String padding) throws Exception {
        final Cipher cipher = Cipher.getInstance(padding, PROVIDER);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bytes = encodeString.getBytes("UTF-8");
        byte[] encodedByteArray = new byte[]{};
        for (int i = 0; i < bytes.length; i += 117) {
            byte[] subarray = ArrayUtils.subarray(bytes, i, i + 117);
            byte[] doFinal = cipher.doFinal(subarray);
            encodedByteArray = ArrayUtils.addAll(encodedByteArray, doFinal);
        }
        return encodedByteArray;
    }

    /**
     * 加密
     *
     * @param data
     * @param key
     * @return
     */
    public static String encodeToBase64(String data, Key key, String padding) {
        try {
            return new String(Base64.encode(encode(data,
                    key, padding)));
        } catch (Exception e) {
            throw new RuntimeException("encrypt fail!", e);
        }
    }

    public static String decode(byte[] decodeByteArray, Key key, String padding) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, NoSuchProviderException {
        final Cipher cipher = Cipher.getInstance(padding, PROVIDER);
        cipher.init(Cipher.DECRYPT_MODE, key);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < decodeByteArray.length; i += 128) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(decodeByteArray, i, i + 128));
            sb.append(new String(doFinal));
        }
        return sb.toString();
    }

    /**
     * 解密
     *
     * @param data
     * @param key
     * @return
     */
    public static String decodeFromBase64(String data, Key key, String padding) {
        try {
            return new String(decode(Base64.decode(data.getBytes()), key, padding).getBytes(),
                    "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("encrypt fail!", e);
        }
    }

    /**
     * 得到密钥字符串（经过base64编码）
     *
     * @return
     */
    public static String getKeyString(Key key) throws Exception {
        byte[] keyBytes = key.getEncoded();
        String s = (new BASE64Encoder()).encode(keyBytes);
        return s;
    }

    public static String getKeyStringByCer(String path) throws Exception {
        CertificateFactory cff = CertificateFactory.getInstance("X.509");
        FileInputStream fis1 = new FileInputStream(path);
        Certificate cf = cff.generateCertificate(fis1);
        PublicKey pk1 = cf.getPublicKey();
        String key = getKeyString(pk1);
        System.out.println("public:\n" + key);
        return key;
    }
    public static PublicKey getPublicKeyByCert(String path) throws Exception {
        CertificateFactory cff = CertificateFactory.getInstance("X.509");
        FileInputStream fis1 = new FileInputStream(path);
        Certificate cf = cff.generateCertificate(fis1);
        PublicKey publicKey = cf.getPublicKey();
        return publicKey;
    }

    public static String getKeyStringByPfx(String strPfx, String strPassword) {
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            FileInputStream fis = new FileInputStream(strPfx);
            // If the keystore password is empty(""), then we have to set
            // to null, otherwise it won't work!!!
            char[] nPassword = null;
            if ((strPassword == null) || strPassword.trim().equals("")) {
                nPassword = null;
            } else {
                nPassword = strPassword.toCharArray();
            }
            ks.load(fis, nPassword);
            fis.close();
            System.out.println("keystore type=" + ks.getType());
            // Now we loop all the aliases, we need the alias to get keys.
            // It seems that this value is the "Friendly name" field in the
            // detals tab <-- Certificate window <-- view <-- Certificate
            // Button <-- Content tab <-- Internet Options <-- Tools menu
            // In MS IE 6.
            Enumeration enumas = ks.aliases();
            String keyAlias = null;
            if (enumas.hasMoreElements())// we are readin just one certificate.
            {
                keyAlias = (String) enumas.nextElement();
                System.out.println("alias=[" + keyAlias + "]");
            }
            // Now once we know the alias, we could get the keys.
            System.out.println("is key entry=" + ks.isKeyEntry(keyAlias));
            PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
            Certificate cert = ks.getCertificate(keyAlias);
            PublicKey pubkey = cert.getPublicKey();

            String basePrikey = RSA.getKeyString(prikey);
            System.out.println("cert class = " + cert.getClass().getName());
            System.out.println("cert = " + cert);
            System.out.println("public key = " + pubkey);
            System.out.println("private key = " + prikey);
            System.out.println("pubkey key = " + RSA.getKeyString(pubkey));
            System.out.println("prikey key = " + RSA.getKeyString(prikey));
            System.out.println("pubkey key length = " + RSA.getKeyString(pubkey).length());
            System.out.println("prikey key length = " + RSA.getKeyString(prikey).length());
            return basePrikey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PrivateKey getPrivateKey(String pfxPath, String pfxPassword) {
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            FileInputStream fis = new FileInputStream(pfxPath);
            // If the keystore password is empty(""), then we have to set
            // to null, otherwise it won't work!!!
            char[] nPassword = null;
            if ((pfxPassword == null) || pfxPassword.trim().equals("")) {
                nPassword = null;
            } else {
                nPassword = pfxPassword.toCharArray();
            }
            ks.load(fis, nPassword);
            fis.close();
            Enumeration enumas = ks.aliases();
            String keyAlias = null;
            if (enumas.hasMoreElements())// we are readin just one certificate.
            {
                keyAlias = (String) enumas.nextElement();
            }
            PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
            return prikey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PublicKey getPublicKey(String pfxPath, String pfxPassword) {
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            FileInputStream fis = new FileInputStream(pfxPath);
            // If the keystore password is empty(""), then we have to set
            // to null, otherwise it won't work!!!
            char[] nPassword = null;
            if ((pfxPassword == null) || pfxPassword.trim().equals("")) {
                nPassword = null;
            } else {
                nPassword = pfxPassword.toCharArray();
            }
            ks.load(fis, nPassword);
            fis.close();
            Enumeration enumas = ks.aliases();
            String keyAlias = null;
            if (enumas.hasMoreElements()) {
                keyAlias = (String) enumas.nextElement();
            }
            Certificate cert = ks.getCertificate(keyAlias);
            PublicKey pubkey = cert.getPublicKey();
            return pubkey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 512位PKCS#8
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

    }
}
