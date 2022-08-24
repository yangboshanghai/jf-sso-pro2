package com.ouyeelf.dbentity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.UUID;
@Entity
@Table(name = "t_sduser")
@Data
public class SdUser {
    @Id
    private  String id= UUID.randomUUID().toString();
    private Date createTime;
    private Date updateTime;
    private Boolean deleted;
    private String creator;
    private String updater;
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
}
