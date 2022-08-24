package com.ouyeelf;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.ouyeelf.util.RsaUtil;
import com.ouyeelf.util.StringUtil;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: RsaTest
 * @description：
 * @author：徐波
 * @date: Created in 2022/8/17 14:35
 */
public class RsaTest {
    /**
     * 获取服务端token时，加密客户端ID及客户端密钥时的类型
     */
    private final static String CLIENT_ENCRYPT_TYPE = "client";
    /**
     * 传递参数时，加密手机号及身份证号时的类型
     */
    private final static String PARAM_ENCRYPT_TYPE = "param";
    private String pub="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC16l/jiIsuEwuJkZovdHseqNFfQrpKsI7ni7ehlKqwwEqMsKDGa42kQWYOda06Icffm5aSB8r7vA1lj5/m0ww8zQNmFWIrIvj2BqLwcxcjhCdiaKS6mrvPvKsjW4kvwudwhxzntEQuUvjJBEtAm3tG9BUEX5zkrqXISrBOTAUhdQIDAQAB";
    private String pri="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALXqX+OIiy4TC4mRmi90ex6o0V9CukqwjueLt6GUqrDASoywoMZrjaRBZg51rTohx9+blpIHyvu8DWWPn+bTDDzNA2YVYisi+PYGovBzFyOEJ2JopLqau8+8qyNbiS/C53CHHOe0RC5S+MkES0Cbe0b0FQRfnOSupchKsE5MBSF1AgMBAAECgYARlGLsVNVoYDBI/NBa3iWIvrUvAL6Kebnxn9Og0oJVPtvlcSs0SZAAS86ePpp9J+cOEZXRKl4gChn8Us8XyhTgQB1cYloVZVPztIS2hBGd4NDVpdzt4XtqIny46RoC4n3S30ofOIhr/pdUlrCEDzRaZ12M8Zv2sbz17fIV+92qKQJBAPqgmL3dkpA0TEdxErkTEzvOmvxjV1kx0xxTL1tFznBq1bnJohI5ty7V62FLoRbU+rJJeQLa8LMkyWapaY4CLTkCQQC50LLGuqjyl/7yV20ueabyWfPjhCTrXlOocZrRYy6xrmZFBiCCdKwIQzId9O6AzZAldh7+5wcQOUqF9xybWBIdAkEAjlhEKCH+KhTp9fGMtrzUwbUYZZxuceRQiNfeH7LICyVCeiDBAiTxm5I3+6yes38HW9dK+YosBzMviDVUFCm8UQJBAKWF73twE8fm9kjS8IOSAtGop8VolarFYCiQ6qTTgWjuPlQGXTdYz1ac5SwiX8OFWn7EdmADVRtyrZwgwO7wyVECQQCu5GAw+K17cPZ7i00QMT8hrj5HaD1md524Oz+AaO1GlVdHrolNvHtuWtqFpxaJmbfXKqJNSf4yOoXcneXtC5uQ";
    @Test
    public void test() throws Exception {
        //获取平台token参数加密串
        Map<String, Object> param = new HashMap<>();
        param.put("clientId", "TB_001");
        param.put("clientSecret", "e5746121f91afb03783cb2ef3608c152");
        String str = encryptMap(param, pub, CLIENT_ENCRYPT_TYPE);
        System.out.println(str);
        String ss="Rv1OYPcZE5O33Qu8w+sIfuuKjtmN+9+K0gpqbdkGp9WfEuuFu6QhPVlqIA68bldFQ9SwgWtzk4Bgrrz3xShOepAyA1mFEuAXcvRriOCj6w5npp58ztU+e5q62buNIWcQWBidzZY7J87rWI/qzrmaX+uI85At5X6JIZ9oiPF0SEw=";
        String decrypt = RsaUtil.decrypt(ss, pri);
        System.out.println("decrypt="+decrypt);
        //获取手机号及身份证号加密串
        Map<String, Object> param1 = new HashMap<>();
        param1.put("mobile", "13211111111");
        param1.put("idNo", "411521111111111111");
        String publicKey1 = "adssdaasdasdasdsadsadasds";
        String str1 = encryptMap(param, pub, CLIENT_ENCRYPT_TYPE);


    }

    public static String encryptMap(Map<String, Object> param, String publicKey, String type) throws Exception {
        if (CollectionUtil.isEmpty(param)) {
            System.out.println("原字符串不能为空");
            return null;
        }
        if (StringUtil.isEmpty(type)) {
            System.out.println("加密参数类型不能为空");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (CLIENT_ENCRYPT_TYPE.equals(type)) {
            String clientId = StringUtil.isEmpty(param.get("clientId")) ? "" : param.get("clientId").toString();
            String clientSecret = StringUtil.isEmpty(param.get("clientSecret")) ? "" : param.get("clientSecret").toString();
            sb.append(clientSecret).append("&").append(clientId);
        } else if (PARAM_ENCRYPT_TYPE.equals(type)) {
            String mobile = StringUtil.isEmpty(param.get("mobile")) ? "" : param.get("mobile").toString();
            String idNo = StringUtil.isEmpty(param.get("idNo")) ? "" : param.get("idNo").toString();
            sb.append(mobile).append("&").append(idNo);
        }
        if (sb == null || "".equals(sb.toString())) {
            System.out.println("加密参数类型对应的参数不存在");
            return null;
        }
        String encrypt = encrypt(sb.toString(), publicKey);
        return encrypt;
    }

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

}
