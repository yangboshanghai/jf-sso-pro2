package com.ouyeelf.dbentity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "t_user")
@Data
public class User  extends BaseUser{
    @Id
    private  String id= UUID.randomUUID().toString();
    private  String userName;
    private  String password;


}
