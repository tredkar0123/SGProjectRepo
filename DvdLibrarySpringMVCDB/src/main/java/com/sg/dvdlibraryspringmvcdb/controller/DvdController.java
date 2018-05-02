/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvcdb.controller;

import com.sg.dvdlibraryspringmvcdb.exceptions.DataIntegrityException;
import com.sg.dvdlibraryspringmvcdb.model.Dvd;
import com.sg.dvdlibraryspringmvcdb.pagination.Pagination;
import com.sg.dvdlibraryspringmvcdb.service.DvdLibraryServiceLayer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */

/**
 * Dvd Library Controller class
 * @author apprentice
 */
@Controller
public class DvdController {
    
    private DvdLibraryServiceLayer service;
    private Pagination page;
    
    private List<Dvd> fullDvdList = new ArrayList<>();
    private List<Dvd> displayDvdList = new ArrayList<>();
    
    @Inject
    public DvdController(DvdLibraryServiceLayer service, Pagination page)
    {
        this.service = service;
        this.page = page;
    }
    
    /**
     * Initialization of fields before display of the dvd library main page
     * @param model
     * @param request
     * @return redirects to the displayDvdPage endpoint.
     */
    @RequestMapping(value="/displayDvd",method=RequestMethod.GET)
    public String displayDvd(Model model, HttpServletRequest request)
    {
        try {
            this.fullDvdList=service.listAllDvds();
            page.setDataSet(this.fullDvdList);
            page.initializePaginationFields();
            this.displayDvdList = 
                this.fullDvdList.subList(page.getDvdIndexBegin(), 
                                         page.getDvdIndexEnd());
            return "redirect:displayDvdPage";
        }
        catch (DataIntegrityException e)
        {
            model.addAttribute("errorMessage", e.getMessage());
            return "DvdLibraryPage";
        }
    }
    
    /**
     * method determines which Dvds should be displayed when a 
     * pagination page link is clicked on the main dvd library page
     * @param request
     * @return redirects to displayDvdPage endpoint
     */
    @RequestMapping(value="/dvdCurrPagination", method=RequestMethod.GET)
    public String determineDvdsForCurrentPage(HttpServletRequest request)
    {
        page.setCurrentPage(Integer.parseInt(request.getParameter("pageIdx")));
        page.determineDvdsForCurrentPage();
        this.displayDvdList = 
            this.fullDvdList.subList(page.getDvdIndexBegin(),
                                     page.getDvdIndexEnd());
        return "redirect:displayDvdPage";
    }
    
    /**
     * method determines the previous 10 pages to be displayed in pagination
     * navbar when the previous button is clicked. If already at the first 
     * 10 pages - does nothing.
     * @param request
     * @return redirects to the displayDvdPage endpoint
     */
    @RequestMapping(value="dvdPaginationPrev",method=RequestMethod.GET)
    public String determinePreviousPageGroup(HttpServletRequest request)
    {
        page.determinePreviousPageGroup();
        this.displayDvdList = 
            this.fullDvdList.subList(page.getDvdIndexBegin(),
                                     page.getDvdIndexEnd());
        return "redirect:displayDvdPage"; 
    }
    
    /**
     * method determines the next 10 pages to be displayed in pagination navbar
     * when the next button is clicked. If already at the last pages - does nothing.
     * @param request
     * @return redirects to the displayDvdPage endpoint
     */
    @RequestMapping(value="dvdPaginationNext", method=RequestMethod.GET)
    public String determineNextPageGroup(HttpServletRequest request)
    {
        page.determineNextPageGroup();
        this.displayDvdList = 
            this.fullDvdList.subList(page.getDvdIndexBegin(),
                                     page.getDvdIndexEnd());
        return "redirect:displayDvdPage"; 
    }
    
    /**
     * method displays the dvd main page (DvdLibraryPage).
     * @param model
     * @return DvdLibraryPage.jsp
     */
    @RequestMapping(value="/displayDvdPage", method=RequestMethod.GET)
    public String displayDvdPage(Model model)
    {  
        List<Dvd> dvdList = displayDvdList;
        model.addAttribute("dvdList", dvdList); 
        model.addAttribute("pageBegin", page.getPageBegin());
        model.addAttribute("pageEnd", page.getPageEnd());
        model.addAttribute("currentPage", page.getCurrentPage());
        model.addAttribute("totalPages", page.getTotalPages());
        return "DvdLibraryPage";   
    }
    
    /**
     * method displays the create dvd page
     * @param model
     * @return CreateDvdPage.jsp
     */
    @RequestMapping(value="/displayCreateDvdForm", method=RequestMethod.GET)
    public String displayCreateDvdForm(Model model)
    {
        try {
            model.addAttribute("ratingList",service.getAllMovieRatings());
            model.addAttribute("dvd",new Dvd());
            return "CreateDvdPage";
        }
        catch (DataIntegrityException e)
        {
            model.addAttribute("errorMessage", e.getMessage());
            return "DvdLibraryPage";
        }
    }
    
    /**
     * method adds the dvd to the database provided validations have been passed.
     * If validations failed, returns the create dvd form to redo.
     * @param request
     * @param dvd
     * @param result
     * @param model
     * @return redirect to displayDvd endpoint if dvd added, else 
     *  returns the CreateDvdPage.jsp
     */
    @RequestMapping(value="/createDvd", method=RequestMethod.POST)
    public String createDvd(HttpServletRequest request,
                            @Valid @ModelAttribute("dvd") Dvd dvd, 
                            BindingResult result, Model model)
    {  
        try 
        {
            if (result.hasErrors()) {
                model.addAttribute("ratingList",service.getAllMovieRatings());
                return "CreateDvdPage";
            }       
            service.addDvd(dvd);
            return "redirect:displayDvd";   
        }
        catch (DataIntegrityException e)
        {
            model.addAttribute("errorMessage", e.getMessage());
            return "DvdLibraryPage";
        }
    }
    
    /**
     * method displays dvd details page
     * @param request
     * @param model
     * @return DvdDetailsPage.jsp
     */
    @RequestMapping(value="/displayDvdDetails",method=RequestMethod.GET)
    public String displayDvdDetails(HttpServletRequest request, Model model)
    {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            Dvd dvd = service.listDvd(id);
            model.addAttribute("dvd", dvd);
            return "DvdDetailsPage";
        }
        catch (DataIntegrityException e)
        {
            model.addAttribute("errorMessage", e.getMessage());
            return "DvdLibraryPage";
        }
    }
    
    /**
     * method deletes the selected dvd from the database. If this endpoint is
     * reached, the user has confirmed deletion on the client side.
     * @param request
     * @param model
     * @return redirect to the displayDvd endpoint.
     */
    @RequestMapping(value="/deleteDvd",method=RequestMethod.GET)
    public String deleteDvd(HttpServletRequest request, Model model)
    {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            service.deleteDvd(id);
            return "redirect:displayDvd";
        }
        catch (DataIntegrityException e)
        {
            model.addAttribute("errorMessage", e.getMessage());
            return "DvdLibraryPage";
        }
    }
    
    /**
     * method displays the edit dvd page/form.
     * @param request
     * @param model
     * @return EditDvdPage.jsp
     */
    @RequestMapping(value="/displayEditDvdPage",method=RequestMethod.GET)
    public String displayEditDvdPage(HttpServletRequest request, Model model)
    {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            Dvd dvd = service.listDvd(id);
            model.addAttribute("dvd",dvd);
            model.addAttribute("ratingList",service.getAllMovieRatings());
            return "EditDvdPage";
        }
        catch (DataIntegrityException e)
        {
            model.addAttribute("errorMessage", e.getMessage());
            return "DvdLibraryPage";
        }
    }
    
    /**
     * method persists the updated dvd info to the database if validations have
     * been passed. Otherwise redirects back to the EditDvdPage
     * @param dvd
     * @param result
     * @param model
     * @return redirects to displayDvd endpoint if dvd successfully added to the
     * database, otherwise returns EditDvdPage.jsp
     */
    @RequestMapping(value="/editDvd",method=RequestMethod.POST)
    public String editDvd(@Valid @ModelAttribute("dvd") Dvd dvd, BindingResult result, 
            Model model)
    {
        try { 
            if (result.hasErrors()) {
                model.addAttribute("ratingList",service.getAllMovieRatings());
                return "EditDvdPage";
            }
            service.updateDvd(dvd);
            return "redirect:displayDvd";
        }
        catch (DataIntegrityException e)
        {
            model.addAttribute("errorMessage", e.getMessage());
            return "DvdLibraryPage";
        }
    }
    
    /**
     * method searches through the library based on search term and string to 
     * find the dvds that match the criteria.
     * @param request
     * @param model
     * @return redirects to the displayDvdPage endpoint after search has been
     * completed.
     */
    @RequestMapping(value="/searchDvds", method=RequestMethod.GET)
    public String searchDvds(HttpServletRequest request, Model model)
    {
        try
        {
            String term = request.getParameter("search-term");
            String currentTerm = request.getParameter("search-string");
            this.fullDvdList= service.searchDvds(term, currentTerm);
            page.setDataSet(this.fullDvdList);
            page.initializePaginationFields();
            this.displayDvdList = 
                this.fullDvdList.subList(page.getDvdIndexBegin(), 
                                         page.getDvdIndexEnd());
            return "redirect:displayDvdPage";
        }
        catch (DataIntegrityException e)
        {
            model.addAttribute("errorMessage", e.getMessage());
            return "DvdLibraryPage";
        }        
    }
    
    /**
     * method sorts the dvd list on the page by title, ascending
     * @return redirect to the displayDvdPage endpoint.
     */
    @RequestMapping(value="/sortTitleAsc", method=RequestMethod.GET)
    public String sortDvdListByTitleAsc()
    {
        this.displayDvdList.sort(Comparator.comparing(Dvd::getTitle)); 
        return "redirect:displayDvdPage";
    }
    
    /**
     * method sorts the dvd list on the page by title, descending
     * @return redirect to the displayDvdPage endpoint.
     */
    @RequestMapping(value="/sortTitleDesc", method=RequestMethod.GET)
    public String sortDvdListByTitleDesc()
    {
        this.displayDvdList.sort(Comparator.comparing(Dvd::getTitle).reversed());
        return "redirect:displayDvdPage";
    }
    
    /**
     * method sorts the dvd list on the page by year, ascending
     * @return redirect to the displayDvdPage endpoint.
     */
    @RequestMapping(value="/sortDateAsc", method=RequestMethod.GET)
    public String sortDvdListByDateAsc()
    {
        this.displayDvdList.sort(Comparator.comparing(Dvd::getYear)); 
        return "redirect:displayDvdPage";
    }
    
    /**
     * method sorts the dvd list on the page by year, descending
     * @return redirect to the displayDvdPage endpoint.
     */
    @RequestMapping(value="/sortDateDesc", method=RequestMethod.GET)
    public String sortDvdListByDateDesc()
    {
        this.displayDvdList.sort(Comparator.comparing(Dvd::getYear).reversed());
        return "redirect:displayDvdPage";
    }
 
}
