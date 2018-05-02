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
 * Deprecated at the moment.
 * @author apprentice
 */
public class DvdMapper implements RowMapper<Dvd> {

    @Override
    public Dvd mapRow(ResultSet rs, int i) throws SQLException 
    {
        Dvd dvd = new Dvd();
        
        dvd.setId(rs.getLong("dvd_id"));
        dvd.setTitle(rs.getString("title"));
        dvd.setYear(rs.getInt("release_year"));
        dvd.setDirector(rs.getString("director"));
        dvd.setNotes(rs.getString("notes"));
        
        return dvd;
    }
    
}
