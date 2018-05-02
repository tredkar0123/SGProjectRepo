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
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
public class DvdLibraryDaoTest {
    
    private DvdLibraryDao dao;
    
    public DvdLibraryDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws DvdLibraryPersistenceException {
        
        ApplicationContext ctx
            = new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");
        dao = ctx.getBean("dao", DvdLibraryDao.class);
        
        // remove all of the Contacts
        List<Dvd> dvdList = dao.listAllDvds();
        for (Dvd dvd : dvdList) {
            dao.deleteDvd(dvd.getId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addDvd & listDvd method, of class DvdLibraryDao.
     */
    @Test
    public void testAddandListDvd() throws Exception {
        
        Dvd dvd = new Dvd();
        MovieRatings rating = new MovieRatings();
        
        rating = dao.getMovieRatingByCode("G");
             
        dvd.setTitle("Junit Add");
        dvd.setYear(2018);
        dvd.setDirector("who");
        dvd.setRating(rating);
        dvd.setNotes("");
        
        dao.addDvd(dvd);
        
        Dvd fromDao = dao.listDvd(dvd.getId());
        
        assertEquals(fromDao, dvd);  
    }
    

    /**
     * Test of deleteDvd method, of class DvdLibraryDao.
     */
    @Test
    public void testDeleteDvd() throws Exception {
        Dvd dvd = new Dvd();
        MovieRatings rating = new MovieRatings();
        
        rating = dao.getMovieRatingByCode("PG");
                
        dvd.setTitle("Junit Delete");
        dvd.setYear(2017);
        dvd.setDirector("who me");
        dvd.setRating(rating);
        dvd.setNotes("");
        
        dao.addDvd(dvd);
        Dvd fromDao = dao.listDvd(dvd.getId());
        assertEquals(fromDao, dvd);
        
        dao.deleteDvd(dvd.getId());
        fromDao = dao.listDvd(dvd.getId());
        assertNull(fromDao);
    }

    /**
     * Test of updateDvd method, of class DvdLibraryDao.
     */
    @Test
    public void testUpdateDvd() throws Exception {
        
        Dvd dvd = new Dvd();
        MovieRatings rating = new MovieRatings();
        
        rating = dao.getMovieRatingByCode("PG");
        
        dvd.setTitle("Junit Update");
        dvd.setYear(2017);
        dvd.setDirector("who me");
        dvd.setRating(rating);
        dvd.setNotes("");
        
        dao.addDvd(dvd);
        Dvd fromDao = dao.listDvd(dvd.getId());
        assertEquals(fromDao, dvd);
        
        dvd.setTitle("Update Test after Update");
        dvd.setYear(2000);
        dao.updateDvd(dvd);
        
        fromDao = dao.listDvd(dvd.getId());
        assertEquals(fromDao, dvd);
        
    }


    /**
     * Test of listAllDvds method, of class DvdLibraryDao.
     */
    @Test
    public void testListAllDvds() throws Exception {
        Dvd dvd = new Dvd();
        MovieRatings rating = new MovieRatings();
        
        rating = dao.getMovieRatingByCode("G");
                
        dvd.setTitle("Junit Add");
        dvd.setYear(2018);
        dvd.setDirector("who");
        dvd.setRating(rating);
        dvd.setNotes("");
        
        dao.addDvd(dvd);
        Dvd fromDao = dao.listDvd(dvd.getId());
        assertEquals(fromDao, dvd); 
        
        Dvd dvd2 = new Dvd();
        MovieRatings rating2 = new MovieRatings();
        
        rating2 = dao.getMovieRatingByCode("PG");
                
        dvd2.setTitle("Junit Delete");
        dvd2.setYear(2017);
        dvd2.setDirector("who me");
        dvd2.setRating(rating2);
        dvd2.setNotes("");
        
        dao.addDvd(dvd2);
        Dvd fromDao2 = dao.listDvd(dvd2.getId());
        assertEquals(fromDao2, dvd2);
        
        assertEquals(2, dao.listAllDvds().size());
        
    }

    /**
     * Test of searchDvds method, of class DvdLibraryDao.
     */
    @Test
    public void testSearchDvds() throws Exception {
         Dvd dvd = new Dvd();
        MovieRatings rating = new MovieRatings();
        
        rating = dao.getMovieRatingByCode("G");
                
        dvd.setTitle("Junit Add");
        dvd.setYear(2018);
        dvd.setDirector("who");
        dvd.setRating(rating);
        dvd.setNotes("");
        
        dao.addDvd(dvd);
        Dvd fromDao = dao.listDvd(dvd.getId());
        assertEquals(fromDao, dvd); 
        
        Dvd dvd2 = new Dvd();
        MovieRatings rating2 = new MovieRatings();
        
        rating2 = dao.getMovieRatingByCode("PG");
                
        dvd2.setTitle("Junit Delete");
        dvd2.setYear(2017);
        dvd2.setDirector("who me");
        dvd2.setRating(rating2);
        dvd2.setNotes("");
        
        dao.addDvd(dvd2);
        Dvd fromDao2 = dao.listDvd(dvd2.getId());
        assertEquals(fromDao2, dvd2);
        
        assertEquals(2, dao.listAllDvds().size());
        
        List<Dvd> dvdList = dao.searchDvds(SearchTerm.YEAR, "2018");
        assertEquals(1, dvdList.size());
        assertEquals(dvdList.get(0),dvd);
    }

    /**
     * Test of getAllMovieRatings method, of class DvdLibraryDao.
     */
    @Test
    public void testGetAllMovieRatings() throws Exception {
        
        List<MovieRatings> ratingList = dao.getAllMovieRatings();
        assertEquals(6, ratingList.size());          
    }

    /**
     * Test of getMovieRatingByCode method, of class DvdLibraryDao.
     */
    @Test
    public void testGetMovieRatingByCode() throws Exception {
        
        String code = "R";
        MovieRatings rating = dao.getMovieRatingByCode(code);
        
        assertEquals("R", rating.getCode().trim());
        assertEquals("Restricted", rating.getDescription().trim());
    }
    
}
