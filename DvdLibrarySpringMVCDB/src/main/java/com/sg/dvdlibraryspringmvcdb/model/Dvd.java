/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvcdb.model;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * Dvd class
 * @author apprentice
 */
public class Dvd {
    
    private long id;
    
    @NotNull(message="You must supply a value for Title.")
    @NotEmpty(message = "You must supply a value for Title.")
    @Length(max = 128, message = "Title must be no more than 128 characters in length.")
    private String title;
    
    @Range(min=1900, max=2100, message="Year must be a 4 digit number between 1900 - 2100")
    private int year;
    
    @NotNull(message="You must supply a value for Director.")
    @NotEmpty(message = "You must supply a value for Director.")
    @Length(max = 50, message = "Title must be no more than 50 characters in length.")
    private String director;
    
    private MovieRatings rating;
    
    private String notes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public MovieRatings getRating() {
        return rating;
    }

    public void setRating(MovieRatings rating) {
        this.rating = rating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 71 * hash + Objects.hashCode(this.title);
        hash = 71 * hash + this.year;
        hash = 71 * hash + Objects.hashCode(this.director);
        hash = 71 * hash + Objects.hashCode(this.rating);
        hash = 71 * hash + Objects.hashCode(this.notes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dvd other = (Dvd) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        if (!Objects.equals(this.rating, other.rating)) {
            return false;
        }
        return true;
    }
    
    
            
    
}
