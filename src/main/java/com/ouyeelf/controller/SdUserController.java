package com.ouyeelf.controller;

import com.ouyeelf.Repository.SdUserRepository;
import com.ouyeelf.Repository.UserRepository;
import com.ouyeelf.dbentity.SdUser;
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
@RequestMapping("sduser")
public class SdUserController {
    @Autowired
    SdUserRepository userRepository;

    @RequestMapping("list")
    public ModelAndView list(String username, String password, HttpSession session) {
        ModelAndView mv=new ModelAndView("sduser/list");
        List<SdUser> items= userRepository.findAll();
        mv.addObject("items",items);
        return mv;
    }
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mv=new ModelAndView("sduser/add");
        mv.addObject("user",new SdUser());
        return mv;
    }
    @RequestMapping("save")
    public ModelAndView save(SdUser user) {
        ModelAndView mv=new ModelAndView("redirect:"+"list");
        if(user.getId()==null){
            user.setId(UUID.randomUUID().toString());
        }
        userRepository.save(user);
        return mv;
    }

    @RequestMapping("del")
    public ModelAndView del(SdUser user) {
        ModelAndView mv=new ModelAndView("redirect:"+"list");
        userRepository.delete(user);
        return mv;
    }
    @RequestMapping("copy")
    public ModelAndView copy(SdUser user) {
        ModelAndView mv=new ModelAndView("redirect:"+"list");
        SdUser user2=userRepository.getById(user.getId());
        SdUser user3=new SdUser();
        BeanUtils.copyProperties(user2,user3);
        user3.setId(UUID.randomUUID().toString());
        userRepository.save(user3);
        return mv;
    }

    @RequestMapping("toEdit")
    public ModelAndView toEdit(SdUser user) {
        ModelAndView mv=new ModelAndView("sduser/add");
       SdUser user2= userRepository.findById(user.getId()).get();
       mv.addObject("user",user2);
        return mv;
    }
}
