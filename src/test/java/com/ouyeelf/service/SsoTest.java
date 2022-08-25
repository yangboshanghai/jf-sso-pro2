package com.ouyeelf.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.ouyeelf.util.RsaUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SsoTest {
    String url="http://10.60.36.245:8080/sso/user/";
        String url2="http://10.60.36.245:8080/sso/auth/";
    String clientId="SSO_WEB";
    String clientSecret="a1cf82d4b6e566be8943c6a4084635d2";
    String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXOkmfyF7Vl5CNc5IhFkoctdMuUVz9EnU9+1esKaAKWTmLNizqsjfqkf0YS2okkn1cUYO1cUf9zs3FhHs00Sp7tPLRit9QD1yFRuXujbMH80dUteUmiEPjDpB9bvIU6z0sN4pRBEnstoxhGnEDBgPaoDzRr/guM6beMfIGKD4aqQIDAQAB";
    String service_token="O6Iy3tTMbCEtM4KJQ5FBs29CIHZ16dreJFpuM7XpMqlPT2Q8zmkUIVALutmqXlaTvz0RBxfIHbL9MwAfLhWfVg==";
    //内部
    String ssoToken="JFoNZj+mPBzMme/amZo4Ox1qlk+DTTuCseP+zxPZ1NW85+CV5Rc05X7I5kBjZPwn";
    //外部
    String ssoToken_w="3b9d5306976744758accd58ffedd91ad";
    @Test
    public void getServiceToken() throws Exception {
        String orderId=System.currentTimeMillis()+"";
        Map encryptParam=new HashMap<>();//对用户名和密码进行加密
        encryptParam.put("clientId",clientId);
        encryptParam.put("clientSecret",clientSecret);
        String param=RsaUtil.encryptMap(encryptParam,publicKey,RsaUtil.CLIENT_ENCRYPT_TYPE);
        System.out.println(param);
        Map infParam=new HashMap();//接口参数
        infParam.put("orderId",orderId);
        infParam.put("clientId",clientId);
        infParam.put("param",param);
        String body=JSON.toJSONString(infParam);
        url2=url2+"getServiceToken";//接口地址
        HashMap<String, String> header = new HashMap<>();//存放请求头，可以存放多个请求头
        header.put("Content-Type", "application/json");
        String outstr= HttpRequest.post(url2).addHeaders(header).body(body).execute().body();
        System.out.println(outstr);
//        {"isSuccess":true,"status":"0","message":"请求成功","orderId":"1661226957565","data":"O6Iy3tTMbCEtM4KJQ5FBs44Hm4uyWhAJC8W+zMwVyek0u3SIQuULmWOKsx6pvOb1vz0RBxfIHbL9MwAfLhWfVg=="}
    }


    @Test
    public void getUserToUrl() throws Exception {
        url+="getUserToUrl";
        String toUrl="M001001";
        String orderId=System.currentTimeMillis()+"";
        String ssoToken=ssoToken_w;
        Map infParam=new HashMap();//接口参数
        infParam.put("orderId",orderId);
        infParam.put("ssoToken",ssoToken);
        infParam.put("toUrl",toUrl);
        String body= JSON.toJSONString(infParam);
        Map header=new HashMap();//将服务端TOKEN放在http header中
        header.put("service_token",service_token);
        header.put("Content-Type", "application/json");
        System.out.println(url);
        String outstr= HttpRequest.post(url).addHeaders(header).body(body).execute().body();
        System.out.println(outstr);
//       {"isSuccess":true,"status":"9101","message":"用户存在，请绑定用户","orderId":null,"data":{"companyName":"资管计划CAP名称一","userName":"资********一","phone":"133****8080","certNo":null,"ssoToken":"JFoNZj+mPBzMme/amZo4Ox1qlk+DTTuCseP+zxPZ1NW85+CV5Rc05X7I5kBjZPwn"}}
    }

    @Test
    public void getVeriCode() throws Exception {
//        String ssoToken="JFoNZj+mPBzMme/amZo4Ox1qlk+DTTuCseP+zxPZ1NW85+CV5Rc05X7I5kBjZPwn";
        Map infParam=new HashMap();//接口参数
        String body= JSON.toJSONString(infParam);
        Map header=new HashMap();//将服务端TOKEN放在http header中
        header.put("service_token",service_token);
        header.put("ssoToken",ssoToken);
        header.put("Content-Type", "application/json");
        url="http://10.60.36.245:8080/sso/messageCode/getVeriCode";
        System.out.println(url);
        String outstr= HttpRequest.get(url).addHeaders(header).body(body).execute().body();
        System.out.println(outstr);
//        {"isSuccess":false,"status":"9101","message":"用户存在，请绑定用户","orderId":null,"data":{"companyName":"资管计划CAP名称一","userName":"资********一","phone":"133****8080","certNo":null,"ssoToken":"sso:token:user:00014_SD_001"}}
    }
    @Test
    public void getMessageCode() throws Exception {
        url="http://10.60.36.245:8080/sso/messageCode/getMessageCode";
        System.out.println(url);
//        String ssoToken="JFoNZj+mPBzMme/amZo4Ox1qlk+DTTuCseP+zxPZ1NW85+CV5Rc05X7I5kBjZPwn";
        String phone="13309098080";
        String sortCode="0789";
        Map infParam=new HashMap();//接口参数
        infParam.put("phone",phone);
        infParam.put("sortCode",sortCode);
        String body= JSON.toJSONString(infParam);
        Map header=new HashMap();//将服务端TOKEN放在http header中
        header.put("service_token",service_token);
        header.put("ssoToken",ssoToken);
        header.put("Content-Type", "application/json");
        String outstr= HttpRequest.post(url).addHeaders(header).body(body).execute().body();
        System.out.println(outstr);
    }

    @Test
    public void userBindSMSCode() throws Exception {
        url="http://10.60.36.245:8080/sso/user/userBindSMSCode";
//        String ssoToken="JFoNZj+mPBzMme/amZo4Ox1qlk+DTTuCseP+zxPZ1NW85+CV5Rc05X7I5kBjZPwn";
        Map header=new HashMap();//将服务端TOKEN放在http header中
        header.put("service_token",service_token);
        header.put("ssoToken",ssoToken);
        header.put("Content-Type", "application/json");
        Map infParam=new HashMap();//接口参数
        infParam.put("smsCode","666666");
        String body= JSON.toJSONString(infParam);
        String outstr= HttpRequest.post(url).addHeaders(header).body(body).execute().body();
        System.out.println(outstr);
    }

    @Test
    public void userBindPassword() throws Exception {
        url="http://10.60.36.245:8080/sso/user/userBindPassword";
//        String ssoToken="JFoNZj+mPBzMme/amZo4Ox1qlk+DTTuCseP+zxPZ1NW85+CV5Rc05X7I5kBjZPwn";
        Map header=new HashMap();//将服务端TOKEN放在http header中
        header.put("service_token",service_token);
        header.put("ssoToken",ssoToken);
        header.put("Content-Type", "application/json");
        Map infParam=new HashMap();//接口参数
        infParam.put("userId","shanghairunyi");
        infParam.put("password","Tb12345678");
        infParam.put("code","0789");
        String body= JSON.toJSONString(infParam);
        String outstr= HttpRequest.post(url).addHeaders(header).body(body).execute().body();
        System.out.println(outstr);
    }

}
