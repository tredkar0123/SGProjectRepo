/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvcdb.pagination;

import com.sg.dvdlibraryspringmvcdb.model.Dvd;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * Helper class for pagination 
 * @author apprentice
 */
public class Pagination {
    
    private List<Dvd> dataSet;
    private int pageBegin;
    private int pageEnd;
    private int librarySize = 0;
    private int totalPages = 1;
    private int currentPage = 1;
    private int dvdIndexBegin;
    private int dvdIndexEnd;

    public List<Dvd> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<Dvd> dataSet) {
        this.dataSet = dataSet;
    }

    public int getPageBegin() {
        return pageBegin;
    }

    public void setPageBegin(int pageBegin) {
        this.pageBegin = pageBegin;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    public int getLibrarySize() {
        return librarySize;
    }

    public void setLibrarySize(int librarySize) {
        this.librarySize = librarySize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getDvdIndexBegin() {
        return dvdIndexBegin;
    }

    public void setDvdIndexBegin(int dvdIndexBegin) {
        this.dvdIndexBegin = dvdIndexBegin;
    }

    public int getDvdIndexEnd() {
        return dvdIndexEnd;
    }

    public void setDvdIndexEnd(int dvdIndexEnd) {
        this.dvdIndexEnd = dvdIndexEnd;
    }
    
    public int calculateLibrarySize() {
        return this.dataSet.size();
    }
    
    public int calculateTotalPages() {
        return ((this.librarySize -1) / (int) 10) + 1;
    }
    
    /**
     * method initializes pagination information 
     */
    public void initializePaginationFields() {
        
        this.librarySize = calculateLibrarySize();
        this.totalPages = calculateTotalPages();
        this.dvdIndexBegin = 0;
        this.dvdIndexEnd = (10 > this.librarySize) ? 
            this.librarySize : 10;
        this.pageBegin = 1;
        this.pageEnd = (this.totalPages > 10) ? 10 : this.totalPages;
    }
    
    /**
     * Determine the beginning and end indexes to show dvds for the current page
     */
    public void determineDvdsForCurrentPage() {
        
        this.dvdIndexBegin = ((this.currentPage - 1) * 10) ; 
        this.dvdIndexEnd = this.currentPage * 10;
        if (this.dvdIndexEnd > this.librarySize)
        {
            this.dvdIndexEnd = this.librarySize ;
        }
    }
    
    /**
     * determine the pages shown in the pagination navbar when the previous
     * button is clicked.
     */
    public void determinePreviousPageGroup()
    {
        if (pageBegin == 1)
        {
            return;
        }
        this.pageBegin -= 10;
        this.pageEnd = this.pageBegin + 9;
        this.currentPage = this.pageEnd;
        this.dvdIndexBegin = ((this.currentPage - 1) * 10) ; 
        this.dvdIndexEnd = this.currentPage * 10;
        if (this.dvdIndexEnd > this.librarySize)
        {
            this.dvdIndexEnd = this.librarySize ;
        }
    }
    
    /**
     * determine the pages shown in the pagination navbar when the next button 
     * is clicked.
     */
    public void determineNextPageGroup()
    {
        if (this.pageEnd == this.totalPages)
        {
            return;
        }
        
        this.pageBegin +=10; 
        this.currentPage = this.pageEnd;
        this.pageEnd = (this.pageEnd + 10 > this.totalPages) ? 
                        this.totalPages: this.pageEnd + 10;
        this.currentPage = this.pageBegin;
        this.dvdIndexBegin = ((this.currentPage - 1) * 10) ; 
        this.dvdIndexEnd = this.currentPage * 10;
        if (this.dvdIndexEnd > this.librarySize)
        {
            this.dvdIndexEnd = this.librarySize;
        }

    }
}
