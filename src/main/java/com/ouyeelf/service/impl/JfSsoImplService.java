package com.ouyeelf.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ouyeelf.entity.ClientInfo;
import com.ouyeelf.entity.JfUser;
import com.ouyeelf.service.JfSsoService;
import com.ouyeelf.util.RsaUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
@Service
public class JfSsoImplService implements JfSsoService {
    @Value("${clientId}")
    public String clientId;
    @Value("${clientSecret}")
    public String clientSecret;
    @Value("${publicKey}")
    public String publicKey;
    @Value("${url}")
    public String url;
    Logger logger =Logger.getLogger(JfSsoImplService.class.getName());

    @Override
    public String getServiceToken() throws Exception {
        String orderId=System.currentTimeMillis()+"";
        Map encryptParam=new HashMap<>();//对用户名和密码进行加密
        encryptParam.put("clientId",clientId);
        encryptParam.put("clientSecret",clientSecret);
        long t1=System.currentTimeMillis();
        System.out.println(System.currentTimeMillis());
        String param= RsaUtil.encryptMap(encryptParam,publicKey,RsaUtil.CLIENT_ENCRYPT_TYPE);
        long t2=System.currentTimeMillis();
        System.out.println("t1:"+t1+",t2:"+t2+",时间差："+(t2-t1));
        Map infParam=new HashMap();//接口参数
        infParam.put("orderId",orderId);
        infParam.put("clientId",clientId);
        infParam.put("param",param);
        String body=JSON.toJSONString(infParam);
        String url2=url+"auth/getServiceToken";//接口地址
        System.out.println(infParam);
        HashMap<String, String> headers = new HashMap<>();//存放请求头，可以存放多个请求头
        headers.put("Content-Type", "application/json");
        String outstr= HttpRequest.post(url2).addHeaders(headers).body(body).execute().body();
        System.out.println(outstr);
        logger.info("接口返回值："+outstr);
        JSONObject json= JSON.parseObject(outstr);
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
                throw  new Exception("生成的服务端失败，错误为：" + message);
            }
        }else{
            System.out.println("接口调用失败，错误代码为：" + isSuccess);
            throw  new Exception("接口调用失败，错误代码为：" + isSuccess);
        }
        return serviceToken;
    }

    @Override
    public JSONObject getClientToken(ClientInfo item) throws Exception {
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
        String outstr= HttpRequest.post(url2).addHeaders(header).body(body).execute().body();
        JSONObject json=JSON.parseObject(outstr);
        String isSuccess=json.getString("isSuccess");
        String status=json.getString("status");
        String ssoToken=json.getString("data");
        String message=json.getString("message");
        System.out.println(outstr);
        if(isSuccess.equals("true")){ //接口调用成功
            if(status.equals("S01")){//用户公司已经存在
                System.out.println("生成的用户token为："+ssoToken);
                //根据这个返回值需要拼接地址
            }else if(status.equals("E01")){//需要走反向注册接口，对公司进行注册

            }else{
                System.out.println("生成的服务端失败，错误代码为：" + status);
                System.out.println("生成的服务端失败，错误为：" + message);
                throw  new Exception("接口调用失败，错误代码为：" + isSuccess);
            }
        }else{
            System.out.println("接口调用失败，错误代码为：" + isSuccess);
            throw  new Exception("接口调用失败，错误代码为：" + isSuccess);
        }
        return json;
    }

    @Override
    public JSONObject regedit(JfUser user) throws Exception{
        Map header=new HashMap();//将服务端TOKEN放在http header中
        header.put("service_token",user.getServiceToken());
        Map infParam=new HashMap();//接口参数
        infParam.put("orderId",user.getOrderId());
        infParam.put("ssoToken",user.getSsoToken());
        infParam.put("compName",user.getCompName());
        infParam.put("compType",user.getCompType());
        infParam.put("registWay",user.getRegistWay());
        infParam.put("registSource",user.getRegistSource());
        infParam.put("tydmCode",user.getTydmCode());
        infParam.put("userCode",user.getUserCode());
        infParam.put("userPassword",user.getUserPassword());
        infParam.put("opertPhone",user.getOpertPhone());
        infParam.put("opertName",user.getOpertName());
        infParam.put("opertCertType",user.getOpertCertType());
        infParam.put("opertCertNo",user.getOpertCertNo());
        infParam.put("opertCertEndDate",user.getOpertCertEndDate());
        infParam.put("reptName",user.getReptName());
        infParam.put("reptPhone",user.getReptPhone());
        infParam.put("reptIdType",user.getReptIdType());
        infParam.put("reptIdNo",user.getReptIdNo());
        infParam.put("reptIdExpDate",user.getReptIdExpDate());
        infParam.put("busCertFileUrl",user.getBusCertFileUrl());
        infParam.put("busCertFileName",user.getBusCertFileName());
        infParam.put("frCertFileUrl_1",user.getFrCertFileUrl_1());
        infParam.put("frCertFileName_1",user.getFrCertFileName_1());
        infParam.put("frCertFileUrl_2",user.getFrCertFileUrl_2());
        infParam.put("frCertFileName_2",user.getFrCertFileName_2());
        infParam.put("wtsCertFileUrl",user.getWtsCertFileUrl());
        infParam.put("wtsCertFileName",user.getWtsCertFileName());
        infParam.put("wtsCertVersion",user.getWtsCertVersion());
        String body= JSON.toJSONString(infParam);
        String url2=url+"user/regedit";
        System.out.println(url2);
        header.put("Content-Type", "application/json");
        String outstr= HttpRequest.post(url2).addHeaders(header).body(body).execute().body();
        System.out.println(outstr);
        JSONObject json=JSON.parseObject(outstr);
        String isSuccess=json.getString("isSuccess");
        String status=json.getString("status");
        String data=json.getString("data");
        String message=json.getString("message");
        if(isSuccess.equals("true")){ //接口调用成功
            if(status.equals("0")){//用户公司已经存在
                System.out.println("用户注册成功，返回值："+data);
                //这里写入后续处理逻辑

            }else{
                System.out.println("生成的服务端失败，错误代码为：" + status);
                System.out.println("生成的服务端失败，错误为：" + message);
                throw  new Exception("接口调用失败，错误代码为：" + isSuccess);
            }
        }else{
            System.out.println("接口调用失败，错误代码为：" + isSuccess);
            throw  new Exception("接口调用失败，错误代码为：" + isSuccess);
        }
        return json;
    }
}
