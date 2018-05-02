/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvcdb.dao;

import com.sg.dvdlibraryspringmvcdb.exceptions.DvdLibraryPersistenceException;
import com.sg.dvdlibraryspringmvcdb.mapper.DvdMovieRatingMapper;
import com.sg.dvdlibraryspringmvcdb.mapper.MovieRatingsMapper;
import com.sg.dvdlibraryspringmvcdb.model.Dvd;
import com.sg.dvdlibraryspringmvcdb.model.MovieRatings;
import com.sg.dvdlibraryspringmvcdb.model.SearchTerm;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * DB Implementation of the DvdLibraryDao Interface
 * @author apprentice
 */
public class DvdLibraryDaoJDBCTemplateImpl implements DvdLibraryDao {
    
    private JdbcTemplate jdbcTemplate;
    
     public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_DVD =
        "insert into dvd (title, release_year, director, rating_id, notes) " +
        " values(?,?,?,?,?)";
    
    private static final String SQL_DELETE_DVD =
        "delete from dvd where dvd_id = ?";
    
    private static final String SQL_UPDATE_DVD = 
        "update dvd " +
        "set title = ?, release_year = ?, director = ?, rating_id = ?, " +
        "notes = ? " + 
        "where dvd_id = ?";
    
    private static final String SQL_SELECT_DVD =
        "select d.dvd_id, d.title, d.release_year, d.director, " +
        "d.rating_id, d.notes, r.rating_id, r.rating_code, r.rating_description, " +
        "r.rating_notes " +
        "from dvd d " +
        "join movie_rating r " +
        "on (d.rating_id = r.rating_id) " +
        "where dvd_id = ?";
    
    private static final String SQL_SELECT_ALL_DVDS =
        "select d.dvd_id, d.title, d.release_year, d.director, " +
        "d.rating_id, d.notes, r.rating_id, r.rating_code, r.rating_description, " +
        "r.rating_notes " +
        "from dvd d " +
        "left outer join movie_rating r " +
        "on (d.rating_id = r.rating_id) " +
        "order by d.title";
    
    private static final String SQL_SELECT_MOVIE_RATINGS_BY_CODE =
        "select rating_id, rating_code, rating_description, rating_notes " +
        "from movie_rating " +
        "where rating_code = ?";
    
    private static final String SQL_SELECT_ALL_MOVIE_RATINGS = 
        "select rating_id, rating_code, rating_description, rating_notes " +
        " from movie_rating";
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void addDvd(Dvd dvd) throws DvdLibraryPersistenceException
    {
        jdbcTemplate.update(SQL_INSERT_DVD, 
                            dvd.getTitle(),
                            dvd.getYear(),
                            dvd.getDirector(),
                            dvd.getRating().getId(),
                            dvd.getNotes());   
        dvd.setId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                                              Long.class));
        
    }

    @Override
    public void deleteDvd(long id) throws DvdLibraryPersistenceException 
    {
        jdbcTemplate.update(SQL_DELETE_DVD,id);
    }

    @Override
    public void updateDvd(Dvd dvd) throws DvdLibraryPersistenceException 
    {
        jdbcTemplate.update(SQL_UPDATE_DVD,
                            dvd.getTitle(),
                            dvd.getYear(),
                            dvd.getDirector(),
                            dvd.getRating().getId(),
                            dvd.getNotes(),
                            dvd.getId());
    }

    @Override
    public Dvd listDvd(long id) throws DvdLibraryPersistenceException {
        try {
            return  
                jdbcTemplate.queryForObject(SQL_SELECT_DVD, 
                                            new DvdMovieRatingMapper(), 
                                            id);        
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<Dvd> listAllDvds() throws DvdLibraryPersistenceException {
        return
            jdbcTemplate.query(SQL_SELECT_ALL_DVDS, 
                               new DvdMovieRatingMapper());    
    }

    @Override
    public List<Dvd> searchDvds(SearchTerm searchTerm, String searchString) 
        throws DvdLibraryPersistenceException 
    {   
        List<Dvd> dvdList = this.listAllDvds();
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
    public List<MovieRatings> getAllMovieRatings() 
        throws DvdLibraryPersistenceException
    {
        List<MovieRatings> ratingList = 
            jdbcTemplate.query(SQL_SELECT_ALL_MOVIE_RATINGS, new MovieRatingsMapper());   
        if (ratingList.size() == 0)
        {    throw new DvdLibraryPersistenceException("No MovieRatings in DB - "
                   + "Contact Tech Support");
        }
        return ratingList;
    }

    @Override
    public MovieRatings getMovieRatingByCode(String code) 
        throws DvdLibraryPersistenceException 
    {   
        MovieRatings rating = new MovieRatings();
        try {
            rating = 
                jdbcTemplate.queryForObject(SQL_SELECT_MOVIE_RATINGS_BY_CODE,
                                            new MovieRatingsMapper(),
                                            code.trim());
            return rating;
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new DvdLibraryPersistenceException("That Movie Rating doesn't "
                   + "exist in the database - Contact Tech Support");
        }
    }
}
