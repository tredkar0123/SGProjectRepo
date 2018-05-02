<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DVD Library</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
          rel="stylesheet" />
    </head>
    <body>
        <div class="container">
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <h6 class="text-right text-primary">
                    <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </h6>
            </c:if>
            <h1>DVD Library: Listings</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/index.jsp">
                            Home
                        </a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/displayDvd">
                            DVD Library
                        </a>
                    </li>
                </ul>    
            </div>
            <div class="row">
                <div class="col-md-3">
                    <form class="form-horizontal" role="form"
                        method="GET" action="displayCreateDvdForm">
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <div class=form-group>
                                <input type="submit" class="btn btn-primary" value="Create DVD" />
                            </div>
                        </sec:authorize>
                    </form>
                </div>
                <div class="col-md-9">
                    <form class="form-horizontal" role="form"
                          method="GET" action="searchDvds">
                        
                        <div class="form-group">
                            <div class="col-md-2">
                                <input type="submit" class="btn btn-primary" 
                                       id="search-btn" value="Search" />
                            </div>
                            <div class="col-md-3">
                                <select class="form-control" name="search-term"
                                        id="search-term">
                                    <option disabled selected>Search Category</option>
                                    <option value="TITLE">Title</option>
                                    <option value="YEAR">Release Date</option>
                                    <option value="DIRECTOR">Director</option>
                                    <option value="RATING">Rating</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="search-string"
                                       id=search-string" />
                            </div>
                        </div>
                    </form>
                </div>
            </div> <!-- end row1 -->
            
            <hr/>
            <ul id="errorMessages" class="alert-danger">
                <c:if test="${not empty errorMessage}"> 
                    <span class="glyphicon glyphicon-exclamation-sign"></span>
                    <c:out value="Error:${errorMessage}" />
                </c:if>
            </ul>
            
            <div class="row"> 
                <table class="table table-responsive table-bordered table-hover">
                    <thead>
                        <tr>
                            <th width=30% class=text-center>
                                Title &nbsp;&nbsp;
                                <a href="sortTitleAsc">
                                    <span class="glyphicon glyphicon-sort-by-alphabet"></span>
                                </a>
                                &nbsp;
                                <a href="sortTitleDesc">
                                    <span class="glyphicon glyphicon-sort-by-alphabet-alt"></span>
                                </a>
                            </th>
                            <th width=15% class=text-center>
                                Release Date &nbsp;&nbsp;
                                <a href="sortDateAsc">
                                    <span class="glyphicon glyphicon-sort-by-order"></span>
                                </a>
                                &nbsp;
                                <a href="sortDateDesc">
                                    <span class="glyphicon glyphicon-sort-by-order-alt"></span>
                                </a>
                            </th>
                            <th width=25% class=text-center>Director</th>
                            <th width=10% class=text-center>Rating</th>
                            <th width=20% class=text-center>Action</th>
                        </tr>
                    </thead>
                    <tbody id=content-rows class=text-center>
                        <c:forEach var="currentDVD" items="${dvdList}" varStatus="vs">
                            <tr>
                                <td>
                                    <a href="displayDvdDetails?id=${currentDVD.id}">
                                        <c:out value="${currentDVD.title}" />
                                    </a> 
                                </td>
                                <td>
                                    <c:out value="${currentDVD.year}" />
                                </td>
                                <td>
                                    <c:out value="${currentDVD.director}" />
                                </td>
                                <td>
                                    <c:out value="${currentDVD.rating.code}" />
                                </td>
                                <td>
                                    <a href="displayEditDvdPage?id=${currentDVD.id}" >
                                        Edit      
                                    </a>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        &nbsp; &nbsp; | &nbsp; &nbsp;
                                    
                                        <a  href="" data-toggle="modal" data-target="#confDelModal${vs.index}">
                                            Delete
                                        </a>  
                                    </sec:authorize>
                                        
                                </td>
                                
                                <div class="modal fade" id="confDelModal${vs.index}" tabindex="-1" 
                                    role="dialog"  aria-labelledby="exampleModalLabel" aria-hidden="true">

                                   <div class="modal-dialog" role="document">
                                       <div class="modal-content">
                                           <div class="modal-header">
                                               <h5 class="modal-title" id="confDelModalLabel">
                                                   Confirm Deletion?</h5>
                                               <button type="button" class="close" 
                                                       data-dismiss="modal" aria-label="Close">
                                                   <span aria-hidden="true">&times;</span>
                                               </button>
                                           </div>

                                           <div class="modal-body">
                                               Please confirm deletion of the DVD.
                                           </div>

                                           <div class="modal-footer">
                                                <a href="deleteDvd?id=${currentDVD.id}">
                                                <button type="submit" class="btn btn-primary">
                                                    Delete DVD</button></a>
                                                <button type="button" class="btn btn-secondary" 
                                                       data-dismiss="modal">Cancel</button> 
                                           </div>
                                       </div> 
                                   </div>
                                </div>     

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div> <!--end row2 -->
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li class="${pageBegin == 1 ? "disabled":""}">
                      <a href="dvdPaginationPrev" 
                         aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                      </a>
                    </li>
                     
                    <c:forEach begin="${pageBegin}" end="${pageEnd}" var="pageIdx">
                        <li class="${pageIdx == currentPage ? 
                            "active" : ""}">
                            <a href="dvdCurrPagination?pageIdx=${pageIdx}">
                                <c:out value="${pageIdx}" />
                            </a>
                        </li>  
                    </c:forEach>
                    <li class="${pageBegin + 10 > totalPages ? "disabled" : ""}">
                      <a href="dvdPaginationNext" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                      </a>
                    </li>
                </ul>
            </nav>
        </div> <!-- end container -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
