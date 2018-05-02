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
            <h1>Edit DVD: <c:out value="${dvd.title}" /> </h1>
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
            <sf:form role="form" class="form-horizontal" method="POST" 
                     action="editDvd" modelAttribute="dvd">
                
                <div class="form-group">
                    <label class="col-md-3 control-label" for="edit-title">
                        Title:
                    </label>
                    <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="edit-title"
                                  path="title" placeholder="Title" />
                        <sf:errors path="title" css="error"></sf:errors>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label" for="edit-year">
                        Release Year:
                    </label>
                    <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="edit-year"
                                  path="year" placeholder="Release Year" />
                        <sf:errors path="year" css="error"></sf:errors>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-md-3 control-label" for="edit-director">
                        Director:
                    </label>
                    <div class="col-md-6">
                        <sf:input type="text" class="form-control" id="edit-director"
                                  path="director" placeholder="Director" />
                        <sf:errors path="director" css="error"></sf:errors>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-md-3 control-label" for="edit-rating">
                        Rating:
                    </label>
                    <div class="col-md-6">
                        <sf:select class="form-control" id="edit-rating"
                                  path="rating">
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
                    <label class="col-md-3 control-label" for="edit-notes">
                        Notes:
                    </label>
                    <div class="col-md-6">
                        <sf:textarea rows="10" class="form-control" id="edit-notes"
                                     path="notes" placeholder="Notes"></sf:textarea>
                        <sf:errors path="notes" css="error"></sf:errors>
                        <sf:hidden path="id" />
                    </div>
                </div>
                    
                <div class="form-group">
                    <div class="col-md-offset-3 col-md-6">
                        <input type="submit" class="btn btn-primary" value="Save Changes" />
                        <a href="${pageContext.request.contextPath}/displayDvd">
                            <input type="button" class="btn btn-primary" value="Cancel" />
                        </a>
                    </div>
                </div>
                
            </sf:form>
 
        </div> <!-- end container -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

