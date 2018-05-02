/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvcdb.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author apprentice
 */
public class PWEnc {
    
    public static void main(String[] args) {
    //  $2a$10$fOlWmpqNXJx/GPYd4HNVp.Ck3TbOqN3J0HHbNgidMIAgVikArc.7S
    String clearPw = "password";
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String hashPw = encoder.encode(clearPw);
    System.out.println(hashPw);
    }
}
