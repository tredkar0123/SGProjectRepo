/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvcdb.exceptions;

/**
 * Class provides information for exception thrown in RESTController class.
 * @author apprentice
 */
public class UpdateIntegrityException extends Exception {
    
    public UpdateIntegrityException(String message)
    {
        super(message);
    }
    
}
