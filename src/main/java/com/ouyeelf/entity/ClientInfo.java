package com.ouyeelf.entity;

public class ClientInfo {
    String orderId=System.currentTimeMillis()+"";
    String serviceToken="";
    String mobile="";
    String idNo="";
    String userName="";
    String businessCreditNo="";
    String companyName="";
    String userId="";
    String companyId="";
    String userAgent="";
    String ip="";
    public ClientInfo(){

    }
    public ClientInfo(String orderId, String serviceToken, String mobile, String idNo, String userName, String businessCreditNo, String companyName, String userId, String companyId, String ip,String userAgent) {
        this.orderId = orderId;
        this.serviceToken = serviceToken;
        this.mobile = mobile;
        this.idNo = idNo;
        this.userName = userName;
        this.businessCreditNo = businessCreditNo;
        this.companyName = companyName;
        this.userId = userId;
        this.companyId = companyId;
        this.ip = ip;
        this.userAgent=userAgent;

    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ClientInfo{");
        sb.append("orderId='").append(orderId).append('\'');
        sb.append(", serviceToken='").append(serviceToken).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", idNo='").append(idNo).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", businessCreditNo='").append(businessCreditNo).append('\'');
        sb.append(", companyName='").append(companyName).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", companyId='").append(companyId).append('\'');
        sb.append(", userAgent='").append(userAgent).append('\'');
        sb.append(", ip='").append(ip).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBusinessCreditNo() {
        return businessCreditNo;
    }

    public void setBusinessCreditNo(String businessCreditNo) {
        this.businessCreditNo = businessCreditNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
