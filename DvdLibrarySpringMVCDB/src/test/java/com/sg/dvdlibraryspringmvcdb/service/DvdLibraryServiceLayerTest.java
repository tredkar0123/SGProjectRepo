/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvcdb.service;

import com.sg.dvdlibraryspringmvcdb.dao.DvdLibraryDao;
import com.sg.dvdlibraryspringmvcdb.exceptions.DataIntegrityException;
import com.sg.dvdlibraryspringmvcdb.model.Dvd;
import com.sg.dvdlibraryspringmvcdb.model.MovieRatings;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class DvdLibraryServiceLayerTest {
   
    private DvdLibraryServiceLayer service;
    
    public DvdLibraryServiceLayerTest() {
        
        ApplicationContext ctx
            = new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");
        service = ctx.getBean("service", DvdLibraryServiceLayer.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx
            = new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");
        service = ctx.getBean("service", DvdLibraryServiceLayer.class);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addDvd method, of class DvdLibraryServiceLayer.
     */
    @Test
    public void testAddDvd() throws Exception {
        MovieRatings rating = new MovieRatings();
        rating.setId(5);
        rating.setCode("R");
        rating.setDescription("Restricted");
        rating.setNotes("");
        
        Dvd dvd= new Dvd();
        dvd.setId(1001);
        dvd.setTitle("ServiceLayerTest Add");
        dvd.setYear(2000);
        dvd.setDirector("Test");
        dvd.setNotes("");
        dvd.setRating(rating);
        
       
        service.addDvd(dvd);        
    }
    
    /**
     * Test of addDvd method - data validations causing exception
     * to be thrown
     */
    @Test
    public void testAddValid() throws Exception {
        MovieRatings rating = new MovieRatings();
        rating.setId(5);
        rating.setCode("R");
        rating.setDescription("Restricted");
        rating.setNotes("");
        
        Dvd dvd= new Dvd();
        dvd.setId(1001);
        dvd.setTitle("");
        dvd.setYear(0);
        dvd.setDirector("");
        dvd.setNotes("");
        dvd.setRating(rating);
        
        try {
        service.addDvd(dvd);        
        fail("Exception not thrown");
        }
        catch (DataIntegrityException e)
        {
            return;
        }
    }
    
    /**
     * Test of deleteDvd method, of class DvdLibraryServiceLayer.
     */
    @Test
    public void testDeleteDvd() throws Exception {
        
        service.deleteDvd(1000);
    }

    /**
     * Test of updateDvd  and ListDvd method, of class DvdLibraryServiceLayer.
     */
    @Test
    public void testUpdateAndListDvd() throws Exception {
        
        Dvd dvd = service.listDvd(1000);
        assertNotNull(dvd);
        dvd.setNotes("Test Update Service Layer");
        service.updateDvd(dvd); 
    }

    /**
     * Test of listAllDvds method, of class DvdLibraryServiceLayer.
     */
    @Test
    public void testListAllDvds() throws Exception {
        List<Dvd> dvdList = service.listAllDvds();
        assertEquals(1,dvdList.size());
    }

    /**
     * Test of searchDvds method, of class DvdLibraryServiceLayer.
     */
    @Test
    public void testSearchDvds() throws Exception {
        
        String searchTerm = "YEAR";
        String searchString = "2000";
        List<Dvd> dvdList = service.searchDvds(searchTerm, searchString);
        assertEquals(1,dvdList.size());
    }

    /**
     * Test of getAllMovieRatings method, of class DvdLibraryServiceLayer.
     */
    @Test
    public void testGetAllMovieRatings() throws Exception {
        List<MovieRatings> ratingList = service.getAllMovieRatings();
        assertEquals(1,ratingList.size());
    }
    
    /**
     * Test of getMovieRatingByCode method, of class DvdLibraryServiceLayer.
     */
    @Test
    public void testGetMovieRatingByCode() throws Exception {
        String code = "R";
        MovieRatings rating = service.getMovieRatingByCode(code);
        assertNotNull(rating);
        
    }
   
}
