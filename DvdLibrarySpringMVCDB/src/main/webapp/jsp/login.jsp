<%-- 
    Document   : login.jsp
    Created on : Apr 19, 2018, 6:12:48 PM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DVD LibraryL Login</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
            rel="stylesheet" />
    </head>
    <body>
        <div class="container">
            <h1>Welcome to the DVD Library!</h1>
            <hr/>
            <h2 class="text-center">Login Page</h2>
            
            <c:if test="${param.login_error == 1}">
                <h3>Wrong id or password - please try again. </h3>
            </c:if>
            
            <form class="form-horizontal" role="form" method="POST"
                  action="j_spring_security_check">
                <div class="form-group">
                    <label for="j_username" class="col-md-4 control-label">
                        Username: 
                    </label>
                    <div class="col-md-4">
                        <input type="text" class="form-control"
                               name="j_username" placeholder="Username" />
                    </div>
                </div>
                <div class="form-group">
                    <label for=j_password" class="col-md-4 control-label">
                        Password:
                    </label>
                    <div class="col-md-4">
                        <input type="password" class="form-control"
                               name="j_password" placeholder="Password" />
                    </div>
                </div>
                <div class="form-group">
                    <input type="submit" class="col-md-offset-4 btn btn-primary"
                           id="login-button" value="Sign In" />
                </div>
            </form>  
        </div> <!-- end container div -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
