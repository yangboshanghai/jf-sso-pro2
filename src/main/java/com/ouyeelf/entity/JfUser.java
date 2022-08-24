package com.ouyeelf.entity;

public class JfUser {
    String orderId="";
    //服务端token
    String serviceToken="";
    //客户端token
    String ssoToken="";
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("JfUser{");
        sb.append("orderId='").append(orderId).append('\'');
        sb.append(", serviceToken='").append(serviceToken).append('\'');
        sb.append(", ssoToken='").append(ssoToken).append('\'');
        sb.append(", compName='").append(compName).append('\'');
        sb.append(", compType='").append(compType).append('\'');
        sb.append(", registWay='").append(registWay).append('\'');
        sb.append(", registSource='").append(registSource).append('\'');
        sb.append(", tydmCode='").append(tydmCode).append('\'');
        sb.append(", userCode='").append(userCode).append('\'');
        sb.append(", userPassword='").append(userPassword).append('\'');
        sb.append(", opertPhone='").append(opertPhone).append('\'');
        sb.append(", opertName='").append(opertName).append('\'');
        sb.append(", opertCertType='").append(opertCertType).append('\'');
        sb.append(", opertCertNo='").append(opertCertNo).append('\'');
        sb.append(", opertCertEndDate='").append(opertCertEndDate).append('\'');
        sb.append(", reptName='").append(reptName).append('\'');
        sb.append(", reptPhone='").append(reptPhone).append('\'');
        sb.append(", reptIdType='").append(reptIdType).append('\'');
        sb.append(", reptIdNo='").append(reptIdNo).append('\'');
        sb.append(", reptIdExpDate='").append(reptIdExpDate).append('\'');
        sb.append(", busCertFileUrl='").append(busCertFileUrl).append('\'');
        sb.append(", busCertFileName='").append(busCertFileName).append('\'');
        sb.append(", frCertFileUrl_1='").append(frCertFileUrl_1).append('\'');
        sb.append(", frCertFileName_1='").append(frCertFileName_1).append('\'');
        sb.append(", frCertFileUrl_2='").append(frCertFileUrl_2).append('\'');
        sb.append(", frCertFileName_2='").append(frCertFileName_2).append('\'');
        sb.append(", wtsCertFileUrl='").append(wtsCertFileUrl).append('\'');
        sb.append(", wtsCertFileName='").append(wtsCertFileName).append('\'');
        sb.append(", wtsCertVersion='").append(wtsCertVersion).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getServiceToken() {
        return serviceToken;
    }

    public void setServiceToken(String serviceToken) {
        this.serviceToken = serviceToken;
    }

    public String getSsoToken() {
        return ssoToken;
    }

    public void setSsoToken(String ssoToken) {
        this.ssoToken = ssoToken;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompType() {
        return compType;
    }

    public void setCompType(String compType) {
        this.compType = compType;
    }

    public String getRegistWay() {
        return registWay;
    }

    public void setRegistWay(String registWay) {
        this.registWay = registWay;
    }

    public String getRegistSource() {
        return registSource;
    }

    public void setRegistSource(String registSource) {
        this.registSource = registSource;
    }

    public String getTydmCode() {
        return tydmCode;
    }

    public void setTydmCode(String tydmCode) {
        this.tydmCode = tydmCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getOpertPhone() {
        return opertPhone;
    }

    public void setOpertPhone(String opertPhone) {
        this.opertPhone = opertPhone;
    }

    public String getOpertName() {
        return opertName;
    }

    public void setOpertName(String opertName) {
        this.opertName = opertName;
    }

    public String getOpertCertType() {
        return opertCertType;
    }

    public void setOpertCertType(String opertCertType) {
        this.opertCertType = opertCertType;
    }

    public String getOpertCertNo() {
        return opertCertNo;
    }

    public void setOpertCertNo(String opertCertNo) {
        this.opertCertNo = opertCertNo;
    }

    public String getOpertCertEndDate() {
        return opertCertEndDate;
    }

    public void setOpertCertEndDate(String opertCertEndDate) {
        this.opertCertEndDate = opertCertEndDate;
    }

    public String getReptName() {
        return reptName;
    }

    public void setReptName(String reptName) {
        this.reptName = reptName;
    }

    public String getReptPhone() {
        return reptPhone;
    }

    public void setReptPhone(String reptPhone) {
        this.reptPhone = reptPhone;
    }

    public String getReptIdType() {
        return reptIdType;
    }

    public void setReptIdType(String reptIdType) {
        this.reptIdType = reptIdType;
    }

    public String getReptIdNo() {
        return reptIdNo;
    }

    public void setReptIdNo(String reptIdNo) {
        this.reptIdNo = reptIdNo;
    }

    public String getReptIdExpDate() {
        return reptIdExpDate;
    }

    public void setReptIdExpDate(String reptIdExpDate) {
        this.reptIdExpDate = reptIdExpDate;
    }

    public String getBusCertFileUrl() {
        return busCertFileUrl;
    }

    public void setBusCertFileUrl(String busCertFileUrl) {
        this.busCertFileUrl = busCertFileUrl;
    }

    public String getBusCertFileName() {
        return busCertFileName;
    }

    public void setBusCertFileName(String busCertFileName) {
        this.busCertFileName = busCertFileName;
    }

    public String getFrCertFileUrl_1() {
        return frCertFileUrl_1;
    }

    public void setFrCertFileUrl_1(String frCertFileUrl_1) {
        this.frCertFileUrl_1 = frCertFileUrl_1;
    }

    public String getFrCertFileName_1() {
        return frCertFileName_1;
    }

    public void setFrCertFileName_1(String frCertFileName_1) {
        this.frCertFileName_1 = frCertFileName_1;
    }

    public String getFrCertFileUrl_2() {
        return frCertFileUrl_2;
    }

    public void setFrCertFileUrl_2(String frCertFileUrl_2) {
        this.frCertFileUrl_2 = frCertFileUrl_2;
    }

    public String getFrCertFileName_2() {
        return frCertFileName_2;
    }

    public void setFrCertFileName_2(String frCertFileName_2) {
        this.frCertFileName_2 = frCertFileName_2;
    }

    public String getWtsCertFileUrl() {
        return wtsCertFileUrl;
    }

    public void setWtsCertFileUrl(String wtsCertFileUrl) {
        this.wtsCertFileUrl = wtsCertFileUrl;
    }

    public String getWtsCertFileName() {
        return wtsCertFileName;
    }

    public void setWtsCertFileName(String wtsCertFileName) {
        this.wtsCertFileName = wtsCertFileName;
    }

    public String getWtsCertVersion() {
        return wtsCertVersion;
    }

    public void setWtsCertVersion(String wtsCertVersion) {
        this.wtsCertVersion = wtsCertVersion;
    }
}
