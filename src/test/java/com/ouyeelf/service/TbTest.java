package com.ouyeelf.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ouyeelf.util.RsaUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TbTest {

    String url="http://10.60.36.245:8080/sso/auth/";
    String clientId="TB_001";
    String clientSecret="e5746121f91afb03783cb2ef3608c152";
    String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC16l/jiIsuEwuJkZovdHseqNFfQrpKsI7ni7ehlKqwwEqMsKDGa42kQWYOda06Icffm5aSB8r7vA1lj5/m0ww8zQNmFWIrIvj2BqLwcxcjhCdiaKS6mrvPvKsjW4kvwudwhxzntEQuUvjJBEtAm3tG9BUEX5zkrqXISrBOTAUhdQIDAQAB";
    @Test
    public void getServiceToken() throws Exception {
        String orderId=System.currentTimeMillis()+"";
        Map encryptParam=new HashMap<>();//对用户名和密码进行加密
        encryptParam.put("clientId",clientId);
        encryptParam.put("clientSecret",clientSecret);
        String param= RsaUtil.encryptMap(encryptParam,publicKey,RsaUtil.CLIENT_ENCRYPT_TYPE);
        Map infParam=new HashMap();//接口参数
        infParam.put("orderId",orderId);
        infParam.put("clientId",clientId);
        infParam.put("param",param);
        String body= JSON.toJSONString(infParam);
        url=url+"getServiceToken";//接口地址
        String outstr= HttpUtil.post(url,body);
        System.out.println(outstr);
        JSONObject json=JSON.parseObject(outstr);
        String isSuccess=json.getString("isSuccess");
        String status=json.getString("status");
        String serviceToken=json.getString("data");
        String message=json.getString("message");

        if(isSuccess.equals("true")){ //接口调用成功
            if(status.equals("0")){//生成服务端token成功
                System.out.println("生成的服务端token为："+serviceToken);
            }else {
                System.out.println("生成的服务端失败，错误代码为：" + status);
                System.out.println("生成的服务端失败，错误为：" + message);
            }
        }else{
            System.out.println("接口调用失败，错误代码为：" + isSuccess);
        }

//       {"isSuccess":true,"status":"0","message":"请求成功","orderId":"1661158250446","data":"FdbBEHcXjrlLiC3TC8QKl6eZuR+L/iS7mSOzcRx6Mixea0R/0s0sSQRSNgUyd/w0F5mW7Sc2/QXUGI8OYkk5Ng=="}
    }

    @Test
    public void getTokenCode() throws Exception {
        Map header=new HashMap();//将服务端TOKEN放在http header中
//        header.put("service_token","FdbBEHcXjrlLiC3TC8QKl6eZuR+L/iS7mSOzcRx6Mixea0R/0s0sSQRSNgUyd/w0F5mW7Sc2/QXUGI8OYkk5Ng==");
        String orderId=System.currentTimeMillis()+"";
        String mobile="13309098080";//用户手机号码
        String idNo="123577";//用户身份证号码
        String userName="资管计划CAP名称一";//用户名称
        String businessCreditNo="91431200MA4L4NH655";//统一社会信用代码
        String companyName="资管计划CAP名称一";//公司名称
        String userId="00014";//用户ID
        String companyId="F1009979";//公司ID
        String ip="10.50.10.352";//用户IP
        String toUrl="M001001";//固定值，跳转的蜀通宝页面
        String sourceClientId="SD_001";
        String sourceClientName="蜀物智链";
        String targetClientId="TB_001";
        String targetClientSecret="e5746121f91afb03783cb2ef3608c152";
        Map infParam=new HashMap();//接口参数
        infParam.put("orderId",orderId);
        infParam.put("userId",userId);
        infParam.put("sourceUserId",userId);
        infParam.put("userName",userName);
        infParam.put("companyName",companyName);
        infParam.put("mobile",mobile);
        infParam.put("idNo",idNo);
        infParam.put("sourceClientId",sourceClientId);
        infParam.put("sourceClientName",sourceClientName);
        infParam.put("targetClientId",targetClientId);
        infParam.put("targetClientSecret",targetClientSecret);

        url="http://10.60.36.245:8081/auth/getTokenCode";
        String body= JSON.toJSONString(infParam);
        System.out.println(url);
        String outstr= HttpRequest.post(url).addHeaders(header).body(body).execute().body();
        System.out.println(outstr);
        //{"isSuccess":true,"status":"0","message":"请求成功","orderId":"1661159703068","data":"703abd232e2044b0909bcc5894e9acf5"}
    }
    @Test
    public void getJwtToken() throws Exception {
        Map header=new HashMap();//将服务端TOKEN放在http header中
        header.put("service_token","FdbBEHcXjrlLiC3TC8QKl6eZuR+L/iS7mSOzcRx6Mixea0R/0s0sSQRSNgUyd/w0F5mW7Sc2/QXUGI8OYkk5Ng==");
        String orderId=System.currentTimeMillis()+"";
        String code="703abd232e2044b0909bcc5894e9acf5";
        Map infParam=new HashMap();//接口参数
        infParam.put("orderId",orderId);
        infParam.put("code",code);
        infParam.put("clientId",clientId);
        url="http://10.60.36.245:8080/sso/auth/getJwtToken";
        String body= JSON.toJSONString(infParam);
        System.out.println(url);
        String outstr= HttpRequest.post(url).addHeaders(header).body(body).execute().body();
        System.out.println(outstr);
        //{"isSuccess":true,"status":"0","message":"请求成功","orderId":"1661159703068","data":"703abd232e2044b0909bcc5894e9acf5"}
    }
}
