package com.ouyeelf.dbentity;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

import lombok.Data;

import javax.persistence.Id;

@Data
public class BaseUser implements Serializable {

    private Date createTime;
    private Date updateTime;
    private Boolean deleted;
    private String creator;
    private String updater;

}
