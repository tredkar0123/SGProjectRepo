/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvcdb.mapper;

import com.sg.dvdlibraryspringmvcdb.model.Dvd;
import com.sg.dvdlibraryspringmvcdb.model.MovieRatings;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Class implements the RowMapper interface to map dvd entity to model(dto) object.
 * @author apprentice
 */
public class DvdMovieRatingMapper implements RowMapper<Dvd> {

    @Override
    public Dvd mapRow(ResultSet rs, int i) throws SQLException {
        
        Dvd dvd = new Dvd();
        MovieRatings rating = new MovieRatings();
        
        dvd.setId(rs.getLong(1));
        dvd.setTitle(rs.getString(2));
        dvd.setYear(rs.getInt(3));
        dvd.setDirector(rs.getString(4));
        dvd.setNotes(rs.getString(6));
        
        rating.setId(rs.getInt(5));
        rating.setCode(rs.getString(8));
        rating.setDescription(rs.getString(9));
        rating.setNotes(rs.getString(10));
        
        dvd.setRating(rating);
        
        return dvd;
    }
    
}
