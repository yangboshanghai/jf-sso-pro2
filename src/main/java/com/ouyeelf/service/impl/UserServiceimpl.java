package com.ouyeelf.service.impl;

import com.ouyeelf.Repository.UserRepository;
import com.ouyeelf.dbentity.User;
import com.ouyeelf.service.UserService;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public  void saveUser(User user){
         userRepository.save(user);
    }
    @Override
    public List<User> findAll(){
        List<User> items=userRepository.findAll();
        return items;
    }

    @Override
    public User getUserById(String id){
         User item=userRepository.findById(id).get();
         return item;
    }

    @Override
    public User QueryByUserName(String userName, String password){
       return userRepository.QueryByUserName(userName,password);
    }
}
