package com.ouyeelf.service;

import com.ouyeelf.dbentity.User;

import java.util.List;

public interface UserService {
    public  void saveUser(User user);
    public User getUserById(String id);
    public User QueryByUserName(String userName,String password);
    public List<User> findAll();
}
