/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvcdb.dao;

import com.sg.dvdlibraryspringmvcdb.mapper.UserMapper;
import com.sg.dvdlibraryspringmvcdb.model.User;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class UserDaoDbImpl implements UserDao {

    private static final String SQL_INSERT_USER =
        "insert into users (username, password, enabled) values (?, ?, 1)";
    
    private static final String SQL_INSERT_AUTHORITY =
        "insert into authorities (username, authority) values (?, ?)";
    
    private static final String SQL_DELETE_USER =
        "delete from users where username = ?";
    
    private static final String SQL_DELETE_AUTHORITIES =
        "delete from authorities where username = ?";
    
    private static final String SQL_SELECT_USER_BY_USERNAME = 
        "select * from users where username = ?";
    
    private static final String SQL_GET_ALL_USERS =
        "select * from users";
    
    private static final String SQL_GET_LAST_INSERT_ID =
        "select LAST_INSERT_ID()";
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public User addUser(User user) {
        jdbcTemplate.update(SQL_INSERT_USER,
                            user.getUsername(),
                            user.getPassword());
        user.setId(jdbcTemplate.queryForObject(SQL_GET_LAST_INSERT_ID, 
                                               Integer.class));
        List<String> authorities = user.getAuthorities();
        
        for (String auth : authorities)
        {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY,
                                user.getUsername(),
                                auth);
        }
        
        return user;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void deleteUser(String username) {
        jdbcTemplate.update(SQL_DELETE_AUTHORITIES, 
                            username);
        jdbcTemplate.update(SQL_DELETE_USER,
                            username);
    }

    @Override
    public User getUser(String username) {
        try 
        {
            return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_USERNAME,
                                               new UserMapper());
            
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_GET_ALL_USERS, 
                                  new UserMapper());
    }
    
}
