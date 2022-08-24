package com.ouyeelf.controller;

import com.ouyeelf.Repository.UserRepository;
import com.ouyeelf.dbentity.User;
import com.ouyeelf.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @RequestMapping("list")
    public ModelAndView list(String username, String password, HttpSession session) {
        ModelAndView mv=new ModelAndView("user/list");
        List<User> items= userService.findAll();
        mv.addObject("items",items);
        return mv;
    }
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mv=new ModelAndView("user/add");
        mv.addObject("user",new User());
        return mv;
    }
    @RequestMapping("save")
    public ModelAndView save(User user) {
        ModelAndView mv=new ModelAndView("redirect:"+"list");
        if(user.getId()==null){
            user.setId(UUID.randomUUID().toString());
        }
        userService.saveUser(user);
        return mv;
    }

    @RequestMapping("del")
    public ModelAndView del(User user) {
        ModelAndView mv=new ModelAndView("redirect:"+"list");
        userRepository.delete(user);
        return mv;
    }
    @RequestMapping("copy")
    public ModelAndView copy(User user) {
        ModelAndView mv=new ModelAndView("redirect:"+"list");
        User user2=userRepository.getById(user.getId());
        User user3=new User();
        BeanUtils.copyProperties(user2,user3);
        user3.setId(UUID.randomUUID().toString());
        userRepository.save(user3);
        return mv;
    }

    @RequestMapping("toEdit")
    public ModelAndView toEdit(User user) {
        ModelAndView mv=new ModelAndView("user/add");
       User user2= userRepository.findById(user.getId()).get();
       mv.addObject("user",user2);
        return mv;
    }
}
