/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvcdb.controller;

import com.sg.dvdlibraryspringmvcdb.exceptions.DataIntegrityException;
import com.sg.dvdlibraryspringmvcdb.model.User;
import com.sg.dvdlibraryspringmvcdb.service.DvdLibraryServiceLayer;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author apprentice
 */
@Controller
public class UserController {
    
    private DvdLibraryServiceLayer service;
    private PasswordEncoder encoder;
    
    @Inject
    public UserController(DvdLibraryServiceLayer service, PasswordEncoder encoder)
    {
        this.service = service;
        this.encoder = encoder;
    }
    
    @RequestMapping(value="/displayUserList", method=RequestMethod.GET)
    public String displayUserList(Model model)
    {
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);
        return "DisplayUserListPage";
    }
    
    @RequestMapping(value="/displayAddUserForm", method=RequestMethod.GET)
    public String displayAddUserForm()
    {
        return "AddUserPage";
    }
    
    @RequestMapping(value="/addUser", method=RequestMethod.GET)
    public String addUser(HttpServletRequest request, Model model)
    {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        String clearPwd = request.getParameter("password");
        String hashPw = encoder.encode(clearPwd);
        user.setPassword(hashPw);
        user.addAuthority("ROLE_USER");
        if (null != request.getParameter("isAdmin")) 
        {
            user.addAuthority("ROLE_ADMIN");
        }
        try {   
        service.addUser(user);
        return "redirect:displayUserList";
        }
        catch (DataIntegrityException e)
        {
            model.addAttribute("errorMsg",e.getMessage());
            return "AddUserPage";
        }
    }
    
    @RequestMapping(value="/deleteUser", method=RequestMethod.GET)
    public String deleteUser(@RequestParam("username") String username, Model model)
    {
        try {
            service.removeUser(username);
            return "redirect:displayUserList";
        }
        catch (DataIntegrityException e)
        {   
            model.addAttribute("errorMsg",e.getMessage());
            return "DisplayUserListPage";
        }
    }
}
