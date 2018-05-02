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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Stub Implementation of the DvdLibraryDao Interface to test the service layer.
 * @author apprentice
 */
public class DvdLibraryDaoStubImpl implements DvdLibraryDao {
    
    private MovieRatings rating = new MovieRatings();
    List<MovieRatings> ratingList = new ArrayList<>();
    private Dvd dvd = new Dvd();
    private List<Dvd> dvdList = new ArrayList<>();

    public DvdLibraryDaoStubImpl ()
    {
        rating.setId(5);
        rating.setCode("R");
        rating.setDescription("Restricted");
        rating.setNotes("");
        
        dvd.setId(1000);
        dvd.setTitle("ServiceLayerTest");
        dvd.setYear(2000);
        dvd.setDirector("Test");
        dvd.setNotes("");
        dvd.setRating(rating);
        
        dvdList.add(dvd);
        ratingList.add(rating);
    }
    
    @Override
    public void addDvd(Dvd dvd) throws DvdLibraryPersistenceException {
        if (this.dvd.getId() == dvd.getId())
        {
            throw new DvdLibraryPersistenceException("Dvd Dao Stub Error");
        }
    }

    @Override
    public void deleteDvd(long id) throws DvdLibraryPersistenceException {
        if (this.dvd.getId() == id)
        {
            
        }
    }

    @Override
    public void updateDvd(Dvd dvd) throws DvdLibraryPersistenceException {
        if (this.dvd.getId() == dvd.getId())
        {
            
        }
    }

    @Override
    public Dvd listDvd(long id) throws DvdLibraryPersistenceException {
        return (this.dvd.getId() == id) ? this.dvd : null;
    }

    @Override
    public List<Dvd> listAllDvds() throws DvdLibraryPersistenceException {
        return dvdList;
    }

    @Override
    public List<Dvd> searchDvds(SearchTerm searchTerm, String searchString) throws DvdLibraryPersistenceException {
        String titleSearch = (searchTerm.equals(SearchTerm.TITLE)) ?
            searchString : null;
        String yearSearch = (searchTerm.equals(SearchTerm.YEAR)) ?
            searchString : null;
        String directorSearch = (searchTerm.equals(SearchTerm.DIRECTOR)) ?
            searchString : null;
        String ratingSearch = (searchTerm.equals(searchTerm.RATING)) ?
            searchString : null;
        Predicate<Dvd> titleMatchPredicate;
        Predicate<Dvd> yearMatchPredicate;
        Predicate<Dvd> directorMatchPredicate;
        Predicate<Dvd> ratingMatchPredicate;
        Predicate<Dvd> truePredicate = (d) -> { return true;};
        titleMatchPredicate = (null == titleSearch || titleSearch.isEmpty())?
            truePredicate : (d) -> d.getTitle().equals(titleSearch);
        yearMatchPredicate = (yearSearch == null || yearSearch.isEmpty()) ?
            truePredicate : d -> d.getYear() == Integer.parseInt(yearSearch);
        directorMatchPredicate = (directorSearch == null || directorSearch.isEmpty())?
                truePredicate : (d) -> d.getDirector().equals(directorSearch);
        ratingMatchPredicate = (ratingSearch == null || ratingSearch.isEmpty()) ?
            truePredicate : d -> d.getRating().getCode().equals(ratingSearch);
        return dvdList
            .stream()
            .filter(titleMatchPredicate
            .and(yearMatchPredicate)
            .and(directorMatchPredicate)
            .and(ratingMatchPredicate))
            .collect(Collectors.toList());
    }

    @Override
    public List<MovieRatings> getAllMovieRatings() throws DvdLibraryPersistenceException {
        return ratingList;
    }

    @Override
    public MovieRatings getMovieRatingByCode(String code) throws DvdLibraryPersistenceException {
        if (this.rating.getCode() != code)
        {
            throw new DvdLibraryPersistenceException("Rating Dao Stub Error");
        }
        
        return this.rating;
    }
    
}
