/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvcdb.service;

import com.sg.dvdlibraryspringmvcdb.exceptions.DataIntegrityException;
import com.sg.dvdlibraryspringmvcdb.model.Dvd;
import com.sg.dvdlibraryspringmvcdb.model.MovieRatings;
import com.sg.dvdlibraryspringmvcdb.model.User;
import java.util.List;

/**
 * Service Layer Interface
 * @author apprentice
 */
public interface DvdLibraryServiceLayer {
    
    /**
     * Performs business validations before calling the dao layer to
     * persist a new dvd to the database
     * @param dvd
     * @throws DataIntegrityException 
     */
    public void addDvd(Dvd dvd) throws DataIntegrityException;
    
    /**
     * Calls the dao layer to remove dvd to the database
     * @param id
     * @throws DataIntegrityException 
     */
    public void deleteDvd(long id) throws DataIntegrityException;
    
    /**
     * Performs business validations before calling the dao layer to
     * persist an updated dvd to the database
     * @param dvd
     * @throws DataIntegrityException 
     */
    public void updateDvd(Dvd dvd) throws DataIntegrityException;
    
    /**
     * method calls dao layer to retrieve dvd by id given
     * @param id
     * @return Dvd, null if no dvd exists by the id given
     * @throws DataIntegrityException 
     */
    public Dvd  listDvd(long id) throws DataIntegrityException;
    
    /**
     * Calls dao layer to return all the dvds objects from the database
     * @return List<Dvd>, null if none exist
     * @throws DataIntegrityException 
     */
    public List<Dvd> listAllDvds() throws DataIntegrityException;
    
    /**
     * validates the search term and search string before calling dao layer
     * to find dvds in the database that match the search criteria
     * @param searchTerm
     * @param searchString
     * @return List<Dvd>
     * @throws DataIntegrityException 
     */
    public List<Dvd> searchDvds(String searchTerm, String searchString)
        throws DataIntegrityException;;
    
    /**
     * retrieves all movie ratings in the database
     * @return List<MovieRatings>
     * @throws DataIntegrityException 
     */
    public List<MovieRatings> getAllMovieRatings() 
        throws DataIntegrityException;
    
    /**
     * returns Movie Rating by the code given
     * @param code
     * @return MovieRatings, null if none exists by code given
     * @throws DataIntegrityException 
     */
    public MovieRatings getMovieRatingByCode(String code)
        throws DataIntegrityException; 
    
    /**
     * persists new user to the database after validations
     * @param user
     * @return User
     * @throws DataIntegrityException 
     */
    public User addUser(User user)
        throws DataIntegrityException;
    
    /**
     * Deletes user from database (by username)
     * @param username
     * @throws DataIntegrityException 
     */
    public void removeUser(String username)
        throws DataIntegrityException;
    
    /**
     * Retrieves user from database by username
     * @param username
     * @return User 
     */
    public User getUser(String username);
    
    /**
     * retrieves all users in the database
     * @return List<User> 
     */
    public List<User> getAllUsers();
}
