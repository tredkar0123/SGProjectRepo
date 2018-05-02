/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvcdb.dao;

import com.sg.dvdlibraryspringmvcdb.exceptions.DvdLibraryPersistenceException;
import com.sg.dvdlibraryspringmvcdb.model.Dvd;
import com.sg.dvdlibraryspringmvcdb.model.MovieRatings;
import com.sg.dvdlibraryspringmvcdb.model.SearchTerm;
import java.util.List;
import java.util.Map;

/**
 * Dao Layer Interface
 * @author apprentice
 */
public interface DvdLibraryDao {
    
    /**
     * Persists a new dvd to the database
     * @param dvd
     * @throws DvdLibraryPersistenceException 
     */
    public void addDvd(Dvd dvd) throws DvdLibraryPersistenceException;
    
    /**
     * Removes a dvd from the database
     * @param id
     * @throws DvdLibraryPersistenceException 
     */
    public void deleteDvd(long id) throws DvdLibraryPersistenceException;
    
    /**
     * Persists an updated dvd to the database
     * @param dvd
     * @throws DvdLibraryPersistenceException 
     */
    public void updateDvd(Dvd dvd) throws DvdLibraryPersistenceException;
    
    /**
     * Method retrieves the dvd information by id from the database
     * @param id
     * @return Dvd, null if no dvd exists by the id given
     * @throws DvdLibraryPersistenceException 
     */
    public Dvd  listDvd(long id) throws DvdLibraryPersistenceException;
    
    /**
     * Method returns all Dvds from the database
     * @return List<Dvd>, null if none exist
     * @throws DvdLibraryPersistenceException 
     */
    public List<Dvd> listAllDvds() throws DvdLibraryPersistenceException;
    
    /**
     * method searches the database for dvds matching search criteria
     * @param searchTerm
     * @param searchString
     * @return List<Dvd>, null if none exist
     * @throws DvdLibraryPersistenceException 
     */
    public List<Dvd> searchDvds(SearchTerm searchTerm, String searchString)
        throws DvdLibraryPersistenceException;
    
    /**
     * method retrieves all movie ratings from the database
     * @return List<MovieRatings>
     * @throws DvdLibraryPersistenceException 
     */
    public List<MovieRatings> getAllMovieRatings() 
        throws DvdLibraryPersistenceException;
   
    /**
     * method retrieves information for a MovieRating by code
     * @param code
     * @return MovieRatings object
     * @throws DvdLibraryPersistenceException 
     */
    public MovieRatings getMovieRatingByCode(String code)
        throws DvdLibraryPersistenceException;
    
}
