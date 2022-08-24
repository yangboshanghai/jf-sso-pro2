package com.ouyeelf.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: RsaUtil
 * @description：加解密
 * @author：徐波
 * @date: Created in 2022/7/27 13:56
 */
public class RsaUtil {
    /**
     * 获取服务端token时，加密客户端ID及客户端密钥时的类型
     */
    public final static String CLIENT_ENCRYPT_TYPE="client";
    /**
     * 传递参数时，加密手机号及身份证号时的类型
     */
    public final static String PARAM_ENCRYPT_TYPE="param";

    /**
     * 类型
     */
    public static final String ENCRYPT_TYPE = "RSA";

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    /**
     * 长度
     */
    private static final int KEY_SIZE = 1024;
    /**
     * 公钥加密
     *
     * @param content   要加密的内容
     * @param publicKey 公钥
     */
    public static String encrypt(String content, String publicKey) {
        try {
            RSA rsa = new RSA(null, publicKey);
            return rsa.encryptBase64(content, KeyType.PublicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 私钥解密
     *
     * @param content    要解密的内容
     * @param privateKey 私钥
     */
    public static String decrypt(String content, String privateKey) {
        try {
            RSA rsa = new RSA(privateKey, null);
            return rsa.decryptStr(content, KeyType.PrivateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> generateKeyPair(String secret) {
        try {
            long l = System.currentTimeMillis();
            KeyPair pair = SecureUtil.generateKeyPair(ENCRYPT_TYPE,KEY_SIZE,(l+secret).getBytes());
            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();
            // 获取 公钥和私钥 的 编码格式（通过该 编码格式 可以反过来 生成公钥和私钥对象）
            byte[] pubEncBytes = publicKey.getEncoded();
            byte[] priEncBytes = privateKey.getEncoded();

            // 把 公钥和私钥 的 编码格式 转换为 Base64文本 方便保存
            String pubEncBase64 = Base64.getEncoder().encodeToString(pubEncBytes);
            String priEncBase64 = Base64.getEncoder().encodeToString(priEncBytes);

            Map<String, String> map = new HashMap<String, String>(2);
            map.put(PUBLIC_KEY, pubEncBase64);
            map.put(PRIVATE_KEY, priEncBase64);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String encryptMap(Map<String, Object> param, String publicKey,String type) throws Exception {
        if (CollectionUtil.isEmpty(param)) {
            System.out.println("原字符串不能为空");
            return null;
        }
        if (StringUtil.isEmpty(type)) {
            System.out.println("加密参数类型不能为空");
            return null;
        }
        StringBuilder sb=new StringBuilder();
        if (CLIENT_ENCRYPT_TYPE.equals(type)){
            String clientId = StringUtil.isEmpty(param.get("clientId")) ? "" : param.get("clientId").toString();
            String clientSecret = StringUtil.isEmpty(param.get("clientSecret")) ? "" : param.get("clientSecret").toString();
            sb.append(clientSecret).append("&").append(clientId);
        }else if (PARAM_ENCRYPT_TYPE.equals(type)){
            String mobile = StringUtil.isEmpty(param.get("mobile")) ? "" : param.get("mobile").toString();
            String idNo = StringUtil.isEmpty(param.get("idNo")) ? "" : param.get("idNo").toString();
            sb.append(mobile).append("&").append(idNo);
        }
        if (sb==null||"".equals(sb.toString())) {
            System.out.println("加密参数类型对应的参数不存在");
            return null;
        }
        String encrypt = encrypt(sb.toString(), publicKey);
        return encrypt;
    }
}
