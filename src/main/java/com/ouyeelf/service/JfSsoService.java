package com.ouyeelf.service;

import com.alibaba.fastjson.JSONObject;
import com.ouyeelf.entity.ClientInfo;
import com.ouyeelf.entity.JfUser;

public interface JfSsoService {
    public String getServiceToken() throws Exception;
    public JSONObject getClientToken(ClientInfo item) throws Exception;
    public JSONObject regedit(JfUser user) throws Exception;
}
