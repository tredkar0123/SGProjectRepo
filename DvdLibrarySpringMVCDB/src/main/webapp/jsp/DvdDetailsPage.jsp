<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <h1>${dvd.title}</h1>
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
                <table>
                    <tr>
                        <td width="10%"><h3>Release Year:</h3></td>
                        <td width="25%"><h3><c:out value="${dvd.year}"/></h3></td>
                    </tr>
                    <tr>
                        <td width="10%"><h3>Director:</h3></td>
                        <td width="25%"><h3><c:out value="${dvd.director}"/></h3></td>
                    </tr>
                    <tr>
                        <td width="10%"><h3>Rating</h3></td>
                        <td width="25%">
                            <h3><c:out value="${dvd.rating.code} - ${dvd.rating.description}"/>
                            </h3></td>
                    </tr>
                    <tr>
                        <td width="10%"><h3>Notes:</h3></td>
                        <td width="25%"><h3><c:out value="${dvd.notes}"/></h3></td>
                    </tr>
                </table>
                
                <a href="${pageContext.request.contextPath}/displayDvd">
                   <input type="submit" class="btn btn-primary" value="Back" />
                </a>
                
            </div> <!--end row -->
        </div> <!-- end container -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
