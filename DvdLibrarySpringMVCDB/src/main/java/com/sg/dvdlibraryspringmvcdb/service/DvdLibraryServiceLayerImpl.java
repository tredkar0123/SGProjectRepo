/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvcdb.service;

import com.sg.dvdlibraryspringmvcdb.dao.DvdLibraryDao;
import com.sg.dvdlibraryspringmvcdb.dao.UserDao;
import com.sg.dvdlibraryspringmvcdb.exceptions.DataIntegrityException;
import com.sg.dvdlibraryspringmvcdb.exceptions.DvdLibraryDataValidationException;
import com.sg.dvdlibraryspringmvcdb.exceptions.DvdLibraryPersistenceException;
import com.sg.dvdlibraryspringmvcdb.model.Dvd;
import com.sg.dvdlibraryspringmvcdb.model.MovieRatings;
import com.sg.dvdlibraryspringmvcdb.model.SearchTerm;
import com.sg.dvdlibraryspringmvcdb.model.User;
import java.util.List;
import javax.inject.Inject;

/**
 * Class to implement the DvdLibraryServiceLayer Interface
 * @author apprentice
 */
public class DvdLibraryServiceLayerImpl implements DvdLibraryServiceLayer {
    
    private DvdLibraryDao dao;
    private UserDao udao;
    
    @Inject
    public DvdLibraryServiceLayerImpl(DvdLibraryDao dao, UserDao udao)
    {
        this.dao = dao;
        this.udao = udao;
    }

    @Override
    public void addDvd(Dvd dvd) throws DataIntegrityException {
       
        try {
            
            validateDvdData(dvd);
            dao.addDvd(dvd);
        }
        catch (DvdLibraryPersistenceException | DvdLibraryDataValidationException e)
        {
            throw new DataIntegrityException(e.getMessage());
        }
    }

    @Override
    public void deleteDvd(long id) 
        throws DataIntegrityException {
        
        try {
            dao.deleteDvd(id);
        }
        catch (DvdLibraryPersistenceException e)
        {
            throw new DataIntegrityException(e.getMessage());
        }
        
    }

    @Override
    public void updateDvd(Dvd dvd) 
        throws DataIntegrityException 
    {
        try {
            validateDvdData(dvd);
            dao.updateDvd(dvd);
        }
        catch (DvdLibraryPersistenceException | DvdLibraryDataValidationException e)
        {
            throw new DataIntegrityException(e.getMessage());
        }
    }

    @Override
    public Dvd listDvd(long id) 
        throws DataIntegrityException 
    {
        try {
            return dao.listDvd(id);
        }
        catch (DvdLibraryPersistenceException e)
        {
            throw new DataIntegrityException(e.getMessage());
        }
    }

    @Override
    public List<Dvd> listAllDvds() 
        throws DataIntegrityException
    {
        try {
            return dao.listAllDvds();
        }
        catch (DvdLibraryPersistenceException e)
        {
            throw new DataIntegrityException(e.getMessage());
        }
    }

    @Override
    public List<Dvd> searchDvds(String term, String searchString) 
        throws DataIntegrityException {
        
        SearchTerm searchTerm = null;
        int year;
        
        if ((null == searchString || searchString.isEmpty()) ||
           (null == term        || term.isEmpty()))
        {
            throw new DataIntegrityException("Search Term and String are Required.");
        }
        
         searchTerm = SearchTerm.valueOf(term);
         
        if (searchTerm == SearchTerm.YEAR)
        {
            try {
                year = Integer.parseInt(searchString);
            }
            catch (NumberFormatException e)
            {
                throw new DataIntegrityException("Year must be numeric");
            }
        }
        
        try
        {
            return dao.searchDvds(searchTerm, searchString);
        }
        catch (DvdLibraryPersistenceException e)
        {
            throw new DataIntegrityException(e.getMessage());
        }
    }

    @Override
    public List<MovieRatings> getAllMovieRatings() 
        throws DataIntegrityException
    {
        try 
        {
            return dao.getAllMovieRatings();
        }
        catch (DvdLibraryPersistenceException e)
        {
            throw new DataIntegrityException(e.getMessage());
        }
    }

    @Override
    public MovieRatings getMovieRatingByCode(String code) 
        throws DataIntegrityException
    {
         try 
        {
            return dao.getMovieRatingByCode(code);
        }
        catch (DvdLibraryPersistenceException e)
        {
            throw new DataIntegrityException(e.getMessage());
        }
    }
    
    private void validateDvdData(Dvd dvd) 
        throws DvdLibraryDataValidationException
    {
        
        if (null == dvd.getTitle()    || dvd.getTitle().isEmpty()    ||
            null == dvd.getDirector() || dvd.getDirector().isEmpty())
        {
            throw new DvdLibraryDataValidationException("Title/Director cannot be empty");
        }
        
        if (dvd.getYear() < 1900 || dvd.getYear() > 2100)
        {
            throw new DvdLibraryDataValidationException("Year must be 4 digit year between"
                    + "1900 & 2100");
        }
        try {
            MovieRatings validRating = dao.getMovieRatingByCode(dvd.getRating().getCode());
        }
        catch (DvdLibraryPersistenceException e)
        {
            throw new DvdLibraryDataValidationException("Invalid movie rating");
        } 
    }

    @Override
    public User addUser(User user) throws DataIntegrityException {
        boolean userExists = (null != this.getUser(user.getUsername())) ? true : false;
        if (userExists)
        {
            throw new DataIntegrityException("User already exists in database.");
        }
        return udao.addUser(user);
    }

    @Override
    public void removeUser(String username) throws DataIntegrityException {
        udao.deleteUser(username);
    }

    @Override
    public User getUser(String username) {
        return udao.getUser(username);
    }
    
    @Override
    public List<User> getAllUsers() {
        return udao.getAllUsers();
    }
    
}
