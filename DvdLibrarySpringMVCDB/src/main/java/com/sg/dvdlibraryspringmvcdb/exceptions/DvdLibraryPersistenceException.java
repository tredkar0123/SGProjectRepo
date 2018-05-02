/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvcdb.exceptions;

/**
 * Class provides information for exception thrown when problems persisting
 * data in the DAO Layer
 * @author apprentice
 */
public class DvdLibraryPersistenceException extends Exception {
    
    public DvdLibraryPersistenceException(String message)
    {
        super(message);
    }
    
    public DvdLibraryPersistenceException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
