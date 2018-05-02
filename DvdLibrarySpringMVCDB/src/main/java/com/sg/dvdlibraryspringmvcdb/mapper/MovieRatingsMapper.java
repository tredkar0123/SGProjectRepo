/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvcdb.mapper;

import com.sg.dvdlibraryspringmvcdb.model.MovieRatings;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Class implements the RowMapper interface to map movie rating entity to 
 * model(dto) object.
 * @author apprentice
 */
public class MovieRatingsMapper implements RowMapper<MovieRatings> {

    @Override
    public MovieRatings mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        MovieRatings rating = new MovieRatings();
        
        rating.setId(rs.getInt("rating_id"));
        rating.setCode(rs.getString("rating_code"));
        rating.setDescription(rs.getString("rating_description"));
        rating.setNotes(rs.getString("rating_notes"));
        
        return rating;
    }
    
}
