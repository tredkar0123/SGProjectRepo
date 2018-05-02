/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvcdb.converter;

import com.sg.dvdlibraryspringmvcdb.dao.DvdLibraryDao;
import com.sg.dvdlibraryspringmvcdb.exceptions.DvdLibraryPersistenceException;
import com.sg.dvdlibraryspringmvcdb.model.MovieRatings;
import javax.inject.Inject;
import org.springframework.core.convert.converter.Converter;

/**
 * MovieRatingsConverts a String movie rating (code) to Movie Rating Converter.
 * @author apprentice
 */
public class MovieRatingsConverter implements Converter<String,MovieRatings> {

    private DvdLibraryDao dao;
    
    @Inject
    public MovieRatingsConverter(DvdLibraryDao dao)
    {
        this.dao = dao;
    }
    
    @Override
    public MovieRatings convert(String s) {
        
        MovieRatings rating = new MovieRatings();
        try 
        {
            rating = dao.getMovieRatingByCode(s.trim());
        }
        catch (DvdLibraryPersistenceException e)
        {
            rating.setId(1);
            rating.setCode("00000");
            rating.setDescription("Unknown");
        }
        
        return rating;
    }
    
}
