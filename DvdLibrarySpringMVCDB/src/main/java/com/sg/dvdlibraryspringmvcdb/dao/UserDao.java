/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvcdb.dao;

import com.sg.dvdlibraryspringmvcdb.model.User;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface UserDao {
    
    public User addUser(User user);
    
    public void deleteUser(String username);
    
    public User getUser(String username);
    
    public List<User> getAllUsers();
    
}
