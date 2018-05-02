/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvcdb.controller;

import com.sg.dvdlibraryspringmvcdb.exceptions.DataIntegrityException;
import com.sg.dvdlibraryspringmvcdb.exceptions.UpdateIntegrityException;
import com.sg.dvdlibraryspringmvcdb.model.Dvd;
import com.sg.dvdlibraryspringmvcdb.service.DvdLibraryServiceLayer;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller for the RESTservice for the dvdlibrary
 * @author apprentice
 */
@CrossOrigin
@Controller
public class RESTController {
    
    private DvdLibraryServiceLayer service;

    @Inject
    public RESTController(DvdLibraryServiceLayer service)
    {
        this.service = service;
    }
    
    /**
     * endpoint gets the dvd for the id provided
     * @param id
     * @return Dvd
     * @throws UpdateIntegrityException 
     */
    @RequestMapping(value="/dvd/{id}", method=RequestMethod.GET)
    @ResponseBody
    public Dvd getDvd(@PathVariable("id") long id)
        throws UpdateIntegrityException
    {
        try {
            return service.listDvd(id);
        }
        catch (DataIntegrityException e)
        {
            throw new UpdateIntegrityException(e.getMessage());
        }
    }
    
    /**
     * method adds a new dvd to the database
     * @param dvd
     * @throws UpdateIntegrityException 
     */
    @RequestMapping(value="/dvd", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    //@ResponseBody
    public void createDvd(@Valid @RequestBody Dvd dvd)
        throws UpdateIntegrityException
    {
        try {
            service.addDvd(dvd);  
        }
        catch (DataIntegrityException e)
        {
            throw new UpdateIntegrityException(e.getMessage());
        }
                
    }
    
    /**
     * method deletes a dvd from the database
     * @param id
     * @throws UpdateIntegrityException 
     */
    @RequestMapping(value="/dvd/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDvd(@PathVariable("id") long id)
        throws UpdateIntegrityException
    {
        try {
            service.deleteDvd(id);
        }
        catch (DataIntegrityException e)
        {
            throw new UpdateIntegrityException(e.getMessage());
        }
    }
    
    /**
     * method persists updated dvd to the database
     * @param id
     * @param dvd
     * @throws UpdateIntegrityException 
     */
    @RequestMapping(value="/dvd/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDvd(@PathVariable("id") long id, 
                          @Valid @RequestBody Dvd dvd)
        throws UpdateIntegrityException
    {
        if (id != dvd.getId())
        {
            throw new UpdateIntegrityException("DVD Id on URL"
                    + "must match DVD Id in submitted data");
        }
        try {
            service.updateDvd(dvd);
        }
        catch (DataIntegrityException e)
        {
            throw new UpdateIntegrityException(e.getMessage());
        }  
    }
    
    /**
     * method gets all the dvds in the database
     * @return List<Dvd>
     * @throws UpdateIntegrityException 
     */
    @RequestMapping(value="/dvds", method=RequestMethod.GET)
    @ResponseBody
    public List<Dvd> getAllDvds()
        throws UpdateIntegrityException
    {
        try {
            return service.listAllDvds();
        }
        catch (DataIntegrityException e)
        {
            throw new UpdateIntegrityException(e.getMessage());
        }
    }
    
}
