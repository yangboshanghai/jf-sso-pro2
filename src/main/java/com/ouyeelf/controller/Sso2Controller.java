package com.ouyeelf.controller;


import com.alibaba.fastjson.JSONObject;
import com.ouyeelf.Repository.SdUserRepository;
import com.ouyeelf.Repository.UserRepository;
import com.ouyeelf.dbentity.SdUser;
import com.ouyeelf.dbentity.User;
import com.ouyeelf.entity.ClientInfo;
import com.ouyeelf.entity.JfUser;
import com.ouyeelf.service.JfSsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("sduser")
public class Sso2Controller {

    @Resource
    JfSsoService jfSsoService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SdUserRepository sdUserRepository;
    @RequestMapping("login")
    public ModelAndView login(String username, String password, HttpSession session) {
        if(username==null){
            return new ModelAndView("login");
        }
        User user=userRepository.QueryByUserName(username,password);
        if(user!=null){
            ModelAndView mv=new ModelAndView("main");
            String toUrl="M001001";//固定值，跳转的蜀通宝页面
            session.setAttribute("userId",user.getUserName());
            session.setMaxInactiveInterval(60000);//设置session的有效时间
            mv.addObject("href","loginjf?toUrl="+toUrl);
            return mv;
        }else{
            Map map=new HashMap();
            map.put("errorCode","E1001");
            map.put("errorMessage","用户名称或者密码错误，请重新登录");
            return new ModelAndView("login").addAllObjects(map);
        }
    }

    @RequestMapping("loginjf")
    public ModelAndView loginjf(String toUrl, HttpSession session,HttpServletRequest request) throws Exception {
        String orderId=System.currentTimeMillis()+"";
        Object userId2= session.getAttribute("userId");
        if(userId2==null){
            ModelAndView mv=new ModelAndView("login");
            System.out.println("用户未登录");
            return mv;
        }
        SdUser user=sdUserRepository.QueryByCode(userId2.toString());

        String userAgent=request.getHeader("User-Agent");
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress(); //ip 地址
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String serviceToken=jfSsoService.getServiceToken();
        //用户手机号码
        String mobile=user.getOpertPhone();
        //用户身份证号码
        String idNo=user.getOpertCertNo();
        //用户名称
        String userName=user.getOpertName();
        //统一社会信用代码
        String businessCreditNo=user.getTydmCode();
        //公司名称
        String companyName=user.getCompName();
        //用户ID
        String userId=user.getUserCode();
        //公司ID
        String companyId="U0001";
        //用户IP
//        String ip="10.50.10.352";
        ClientInfo item=new ClientInfo( orderId,  serviceToken,  mobile,  idNo,  userName,  businessCreditNo,  companyName,  userId,  companyId,  ip,userAgent);
        JSONObject json=  jfSsoService.getClientToken(item);
        String status=json.getString("status");
        String ssoToken=json.getString("data");
        String message=json.getString("message");
        ModelAndView mv=new ModelAndView("error");
        if(status.equals("S01")){//用户公司已经存在
            System.out.println("生成的用户token为："+ssoToken);
            //根据这个返回值需要拼接地址
            String redirectUrl="http://testjfsso.ouyeelf.com/sso-web?ssoToken="+ssoToken+"&toUrl="+toUrl;
            return new ModelAndView("redirect:"+redirectUrl);
        }else if(status.equals("E01")){//需要走反向注册接口，对公司进行注册
            JSONObject json2= regedit(user,ssoToken,serviceToken);
            String status2=json2.getString("status");
            String data2=json2.getString("data");
            String message2=json2.getString("message");
            if(status2.equals("0")){
                //再执行一次
                loginjf(toUrl,session,request);
            }else{
                System.out.println("注册失败");
                //跳转到错误提醒页面
                mv.addObject("errorCode",status);
                mv.addObject("errorMessage",message);
            }
            return mv;
        }else{
            mv.addObject("errorCode",status);
            mv.addObject("errorMessage",message);
            return mv;
        }
    }

    public JSONObject  regedit(SdUser user,String ssoToken,String serviceToken) throws Exception {
        //企业名称
        String compName=user.getCompName();
        //企业类型代码
        String compType=user.getCompType();
        //注册方式代码
        String registWay=user.getRegistWay();
        //来源平台标识
        String registSource=user.getRegistSource();
        //统一社会信用代码
        String tydmCode=user.getTydmCode();
        //企业操作员登录账号
        String userCode=user.getUserCode();
        //企业操作员登录密码
        String userPassword=user.getUserPassword();
        //手机号码
        String opertPhone=user.getOpertPhone();
        //操作员姓名
        String opertName=user.getOpertName();
        //操作员证件类型
        String opertCertType=user.getOpertCertType();
        //操作员证件号
        String opertCertNo=user.getOpertCertNo();
        //操作员证件截止日期
        String opertCertEndDate=user.getOpertCertEndDate();
        //法人姓名
        String reptName=user.getReptName();
        //法人联系方式
        String reptPhone=user.getReptPhone();
        //法人证件类型
        String reptIdType=user.getReptIdType();
        //法人证件号码
        String reptIdNo=user.getReptIdNo();
        //法人证件过期时间
        String reptIdExpDate=user.getReptIdExpDate();
        //新版营业执照地址
        String busCertFileUrl=user.getBusCertFileUrl();
        //新版营业执照文件保存名称
        String busCertFileName=user.getBusCertFileName();
        //法人附件正面地址
        String frCertFileUrl_1=user.getFrCertFileUrl_1();
        //法人附件正面文件保存名称
        String frCertFileName_1=user.getFrCertFileName_1();
        //法人附件反面地址
        String frCertFileUrl_2=user.getFrCertFileUrl_2();
        //法人附件反面文件保存名称
        String frCertFileName_2=user.getFrCertFileName_2();
        //操作员委托书文件地址
        String wtsCertFileUrl=user.getWtsCertFileUrl();
        //操作员委托书文件保存名称
        String wtsCertFileName=user.getWtsCertFileName();
        //委托书版本类型非必填项
        String wtsCertVersion=user.getWtsCertVersion();
        String orderId=System.currentTimeMillis()+"";
        JfUser item=new JfUser();
        item.setOrderId(orderId);
        item.setServiceToken(serviceToken);
        item.setSsoToken(ssoToken);
        item.setCompName(compName);
        item.setCompType(compType);
        item.setRegistWay(registWay);
        item.setRegistSource(registSource);
        item.setTydmCode(tydmCode);
        item.setUserCode(userCode);
        item.setUserPassword(userPassword);
        item.setOpertPhone(opertPhone);
        item.setOpertName(opertName);
        item.setOpertCertType(opertCertType);
        item.setOpertCertNo(opertCertNo);
        item.setOpertCertEndDate(opertCertEndDate);
        item.setReptName(reptName);
        item.setReptPhone(reptPhone);
        item.setReptIdType(reptIdType);
        item.setReptIdNo(reptIdNo);
        item.setReptIdExpDate(reptIdExpDate);
        item.setBusCertFileUrl(busCertFileUrl);
        item.setBusCertFileName(busCertFileName);
        item.setFrCertFileUrl_1(frCertFileUrl_1);
        item.setFrCertFileName_1(frCertFileName_1);
        item.setFrCertFileUrl_2(frCertFileUrl_2);
        item.setFrCertFileName_2(frCertFileName_2);
        item.setWtsCertFileUrl(wtsCertFileUrl);
        item.setWtsCertFileName(wtsCertFileName);
        item.setWtsCertVersion(wtsCertVersion);
        JSONObject json=  jfSsoService.regedit(item);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "userIsLogin",produces = "application/json;charset=UTF-8")
    public String userIsLogin(String userId, HttpSession session,HttpServletRequest request) {
        Object userId2= session.getAttribute("userId");
        JSONObject result = new JSONObject();
        result.put("msg", "1");
        if(userId2==null){
            //用户未登录
            result.put("msg", "1");
            return  result.toJSONString();
        }
        String userId3=session.getAttribute("userId").toString();
        if(userId.equals(userId3)){
            //用户已登录
            result.put("msg", "0");
            return  result.toJSONString();
        }else{
            //用户未登录
            result.put("msg", "1");
        }
       return  result.toJSONString();
    }

    }
