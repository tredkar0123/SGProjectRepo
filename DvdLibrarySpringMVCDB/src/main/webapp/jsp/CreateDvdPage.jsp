<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
            <h1>Create DVD</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/index.jsp">
                            Home
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayDvd">
                            DVD Library
                        </a>
                    </li>
                </ul>    
            </div>
            <div class="row">
                <sf:form class="form-horizontal" role="form"
                         method="POST" action="createDvd" modelAttribute="dvd">
                    <div class="form-group">
                        <label for="title" class="col-md-3 control-label">
                            Title:
                        </label>
                        <div class="col-md-6">
                            <sf:input type="text" class="form-control" id="title" 
                                      path="title" placeholder="Movie Title" />
                            <sf:errors path="title" css="error"></sf:errors>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="year" class="col-md-3 control-label">
                            Release Date:
                        </label>
                        <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="year" 
                                   path="year" placeholder="Release Year"/>
                            <sf:errors path="year" css="error"></sf:errors>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="director" class="col-md-3 control-label">
                            Director:
                        </label>
                        <div class="col-md-6">
                            <sf:input type="text" class="form-control" id="director"
                                   path="director" placeholder="Director" />
                            <sf:errors path="director" css="error"></sf:errors>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="rating" class="col-md-3 control-label">
                            Rating:
                        </label>
                        <div class="col-md-6">
                            <sf:select path="rating" class="form-control" id="rating">
                                <c:forEach var="currRating" items="${ratingList}">
                                    <sf:option value="${currRating.code}">
                                        <c:out value="${currRating.code} - ${currRating.description}" />
                                    </sf:option>
                                </c:forEach> 
                            </sf:select>
                            <sf:errors path="rating" css="error"></sf:errors>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="notes" class="col-md-3 control-label">
                            Notes:
                        </label> 
                        <div class="col-md-6">
                            <sf:textarea rows="10" class="form-control" id="notes"
                                     path="notes" placeholder="Notes"></sf:textarea>
                            <sf:errors path="notes" css="error"></sf:errors>
                        </div>
                    </div>
                        
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-6">
                            <input type="submit" class="btn btn-primary" value="Create DVD" />
                            <a href="${pageContext.request.contextPath}/displayDvd">
                                <input type="button" class="btn btn-primary" value="Cancel" />
                            </a>
                        </div>
                    </div>
                            
                </sf:form>
            </div> <!--end row -->
        </div> <!-- end container -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
