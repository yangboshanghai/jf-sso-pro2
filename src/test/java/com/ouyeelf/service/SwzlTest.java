package com.ouyeelf.service;

import cn.hutool.http.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ouyeelf.entity.ClientInfo;
import com.ouyeelf.util.RsaUtil;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class SwzlTest {
    String url="http://testjfsso.ouyeelf.com/sso/";
    String clientId="SD_001";
    String clientSecret="7a8e65f3ed31c47a8716b87fed649c27";
    String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCaOTalMyKYta10Jwav5S20jY/qiVbTjZ9+M/kYQd4P9QGsZMs+QBUCtQOLKt082uK6oHIS3Nw4zyrJp3vOfi8uIj/VI0Dviaml/peKAG/LDGNm72tUZkUpl0qJZ8G7BhQi2yw930DoOs2+li345WoSrNQPcAlbarazd3VmQEf9xQIDAQAB";
    String service_token="K2N+EbVfkYdNpefzFnpdrZ6FjR1v6a5Qq1Bt2+pP1wVUcF0I8r3grdOn7DEcjAAlF5mW7Sc2/QXUGI8OYkk5Ng==";

    @Test
    public void regedit() throws Exception {
        String url2=url+"user/regedit";
        System.out.println(url2);
        //企业名称
        String compName="";
        //企业类型代码
        String compType="";
        //注册方式代码
        String registWay="";
        //来源平台标识
        String registSource="";
        //统一社会信用代码
        String tydmCode="";
        //企业操作员登录账号
        String userCode="";
        //企业操作员登录密码
        String userPassword="";
        //手机号码
        String opertPhone="";
        //操作员姓名
        String opertName="";
        //操作员证件类型
        String opertCertType="";
        //操作员证件号
        String opertCertNo="";
        //操作员证件截止日期
        String opertCertEndDate="";
        //法人姓名
        String reptName="";
        //法人联系方式
        String reptPhone="";
        //法人证件类型
        String reptIdType="";
        //法人证件号码
        String reptIdNo="";
        //法人证件过期时间
        String reptIdExpDate="";
        //新版营业执照地址
        String busCertFileUrl="";
        //新版营业执照文件保存名称
        String busCertFileName="";
        //法人附件正面地址
        String frCertFileUrl_1="";
        //法人附件正面文件保存名称
        String frCertFileName_1="";
        //法人附件反面地址
        String frCertFileUrl_2="";
        //法人附件反面文件保存名称
        String frCertFileName_2="";
        //操作员委托书文件地址
        String wtsCertFileUrl="";
        //操作员委托书文件保存名称
        String wtsCertFileName="";
        //委托书版本类型非必填项
        String wtsCertVersion="";
        String orderId=System.currentTimeMillis()+"";
        Map header=new HashMap();//将服务端TOKEN放在http header中
        header.put("service_token",service_token);
        header.put("Content-Type", "application/json");
        System.out.println(url);

        Map infParam=new HashMap();//接口参数
        infParam.put("orderId",orderId);
        infParam.put("ssoToken","0793a75048dd452fa2ba10c2455d3351");
        infParam.put("compName",compName);
        infParam.put("compType",compType);
        infParam.put("registWay",registWay);
        infParam.put("registSource",registSource);
        infParam.put("tydmCode",tydmCode);
        infParam.put("userCode",userCode);
        infParam.put("userPassword",userPassword);
        infParam.put("opertPhone",opertPhone);
        infParam.put("opertName",opertName);
        infParam.put("opertCertType",opertCertType);
        infParam.put("opertCertNo",opertCertNo);
        infParam.put("opertCertEndDate",opertCertEndDate);
        infParam.put("reptName",reptName);
        infParam.put("reptPhone",reptPhone);
        infParam.put("reptIdType",reptIdType);
        infParam.put("reptIdNo",reptIdNo);
        infParam.put("reptIdExpDate",reptIdExpDate);
        infParam.put("busCertFileUrl",busCertFileUrl);
        infParam.put("busCertFileName",busCertFileName);
        infParam.put("frCertFileUrl_1",frCertFileUrl_1);
        infParam.put("frCertFileName_1",frCertFileName_1);
        infParam.put("frCertFileUrl_2",frCertFileUrl_2);
        infParam.put("frCertFileName_2",frCertFileName_2);
        infParam.put("wtsCertFileUrl",wtsCertFileUrl);
        infParam.put("wtsCertFileName",wtsCertFileName);
        infParam.put("wtsCertVersion",wtsCertVersion);
        String body= JSON.toJSONString(infParam);
        String outstr= HttpRequest.post(url2).addHeaders(header).body(body).execute().body();
        System.out.println(outstr);
    }

    //获取服务端token
    @Test
    public void getServiceTokenBatch()throws Exception {
//        StringBuffer sb=new StringBuffer();
//        for (int i = 0; i <100; i++) {
//            long t1=System.currentTimeMillis();
            getServiceToken();
//            long t2=System.currentTimeMillis();
//            System.out.println("t1:"+t1+",t2:"+t2+",时间差："+(t2-t1));
//            sb.append("t1:"+t1+",t2:"+t2+",时间差："+(t2-t1)+"\n");
//        }
//        System.out.println(sb.toString());
    }

    public void getServiceToken() throws Exception {
        String orderId="S"+System.currentTimeMillis()+"";
        Map encryptParam=new HashMap<>();//对用户名和密码进行加密
        encryptParam.put("clientId",clientId);
        encryptParam.put("clientSecret",clientSecret);
        long t1=System.currentTimeMillis();
        String param=RsaUtil.encryptMap(encryptParam,publicKey,RsaUtil.CLIENT_ENCRYPT_TYPE);
        long t2=System.currentTimeMillis();
        System.out.println("t1:"+t1+",t2:"+t2+",时间差："+(t2-t1));
        Map infParam=new HashMap();//接口参数
        infParam.put("orderId",orderId);
        infParam.put("clientId",clientId);
        infParam.put("param",param);
        String body=JSON.toJSONString(infParam);
        System.out.println(body);
        String   url2=url+"auth/getServiceToken";//接口地址
        System.out.println(url2);
        HashMap<String, String> headers = new HashMap<>();//存放请求头，可以存放多个请求头
        headers.put("Content-Type", "application/json");
        String outstr= HttpRequest.post(url2).addHeaders(headers).body(body).execute().body();
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

//       {"isSuccess":true,"status":"0","message":"请求成功","orderId":"1661249596288","data":"K2N+EbVfkYdNpefzFnpdrcaGQjLfTYSgfgIw6nTHmd8gSdFVnDNWlw8JdlLYOktzF5mW7Sc2/QXUGI8OYkk5Ng=="}

    }
    //获取客户端token
    @Test
    public void testGetClientToken() throws Exception {
        ClientInfo item=new ClientInfo();
        String orderId=System.currentTimeMillis()+"";
        //取上面接口返回的token值
//        String service_token="K2N+EbVfkYdNpefzFnpdrQrHDLm5SCAVXJPrT2od0DicrGm4vQ0IS8lfzEOUsgalF5mW7Sc2/QXUGI8OYkk5Ng==";
        String mobile="13309098080";//用户手机号码
        String idNo="123577";//用户身份证号码
        String userName="资管计划CAP名称一22";//用户名称
        String businessCreditNo="91431200MA4L4NH655";//统一社会信用代码
        String companyName="资管计划CAP名称一";//公司名称
        String userId="00014";//用户ID
        String companyId="F1009979";//公司ID
        String ip="10.50.10.352";//用户IP
        String toUrl="M001001";//固定值，跳转的蜀通宝页面
        String userAgent="";
        item.setCompanyId(companyId);
        item.setOrderId(orderId);
        item.setBusinessCreditNo(businessCreditNo);
        item.setIp(ip);
        item.setIdNo(idNo);
        item.setServiceToken(service_token);
        item.setCompanyName(companyName);
        item.setMobile(mobile);
        item.setUserName(userName);
        item.setUserId(userId);
        item.setUserAgent(userAgent);
        String outstr=getClientToken(item);
        System.out.println(outstr);
        JSONObject json=JSON.parseObject(outstr);
        String isSuccess=json.getString("isSuccess");
        String status=json.getString("status");
        String ssoToken=json.getString("data");
        String message=json.getString("message");
        if(isSuccess.equals("true")){ //接口调用成功
             if(status.equals("S01")){//用户公司已经存在
                 System.out.println("生成的用户token为："+ssoToken);
                 //根据这个返回值需要拼接地址
                 String redirectUrl="http://testjfsso.ouyeelf.com/sso-web?ssoToken="+ssoToken+"&toUrl="+toUrl;
                 System.out.println("跳转的地址为："+redirectUrl);
             }else if(status.equals("E01")){//需要走反向注册接口，对公司进行注册

             }else{
                 System.out.println("生成的服务端失败，错误代码为：" + status);
                 System.out.println("生成的服务端失败，错误为：" + message);
             }
        }else{
            System.out.println("接口调用失败，错误代码为：" + isSuccess);
        }
//      {"isSuccess":true,"status":"S01","message":"用户已存在！","orderId":"1661227137687","data":"7ef1966a679c4f919e1328691e6b942d"}
    }


    public String getClientToken(ClientInfo item) throws Exception {
        Map encryptParam=new HashMap<>();// 对敏感信息进行加密，如手机号码和身份证
        encryptParam.put("mobile",item.getMobile());
        encryptParam.put("idNo",item.getIdNo());
        String param=RsaUtil.encryptMap(encryptParam,publicKey,RsaUtil.PARAM_ENCRYPT_TYPE);
        Map infParam=new HashMap();//接口参数
        infParam.put("orderId",item.getOrderId());
        System.out.println(item.getOrderId());
        infParam.put("userName",item.getUserName());
        infParam.put("businessCreditNo",item.getBusinessCreditNo());
        infParam.put("ip",item.getIp());
        infParam.put("companyName",item.getCompanyName());
        infParam.put("userId",item.getUserId());
        infParam.put("companyId",item.getCompanyId());
        infParam.put("param",param);
        String body=JSON.toJSONString(infParam);
        String url2=url+"auth/getUserToken"; //接口地址
        System.out.println(url2);
        Map header=new HashMap();//将服务端TOKEN放在http header中
        header.put("service_token",item.getServiceToken());
        header.put("Content-Type", "application/json");
        String outstr=HttpRequest.post(url2).addHeaders(header).body(body).execute().body();
       return outstr;

    }


    @Test
    public void getClientToken() throws Exception {
        String orderId=System.currentTimeMillis()+"";
//        String service_token="K2N+EbVfkYdNpefzFnpdrYrMmHyS/VLDBpX8KqGSSuKDiHCEEo0RZWDpLveGtPkoF5mW7Sc2/QXUGI8OYkk5Ng==";
        String mobile="15601639035";
        String idNo="6103241982040181811";
        String userName="资管计划CAP名称一";
        String businessCreditNo="91431200MA4L4NH655";
        String companyName="资管计划CAP名称一";
        String userId="00014";
        String companyId="F1009979";
        String ip="10.50.10.352";
        Map encryptParam=new HashMap<>();// 对敏感信息进行加密，如手机号码和身份证
        encryptParam.put("mobile",mobile);
        encryptParam.put("idNo",idNo);
        String param=RsaUtil.encryptMap(encryptParam,publicKey,RsaUtil.PARAM_ENCRYPT_TYPE);
        Map infParam=new HashMap();//接口参数
        infParam.put("orderId",orderId);
        System.out.println(orderId);
        infParam.put("userName",userName);
        infParam.put("businessCreditNo",businessCreditNo);
        infParam.put("ip",ip);
        infParam.put("companyName",companyName);
        infParam.put("userId",userId);
        infParam.put("companyId",companyId);
        infParam.put("param",param);
        String body=JSON.toJSONString(infParam);
        String  url2=url+"auth/getUserToken"; //接口地址
        Map header=new HashMap();//将服务端TOKEN放在http header中
        header.put("service_token",service_token);
        String outstr=HttpRequest.post(url2).addHeaders(header).body(body).execute().body();
        System.out.println(outstr);
    }


}
