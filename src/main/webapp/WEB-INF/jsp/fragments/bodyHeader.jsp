<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="navbar navbar-dark bg-dark py-0">
    <div class="container">
        <a href="${pageContext.request.contextPath}" class="navbar-brand"><img src="resources/images/hospital1.png">Home</a>
        <form class="form-inline my-2">
            <div class="input-group-text">
                Welcome, ${userName}
                <a class="btn btn-primary" href="logout">
                    <span class="fa fa-sign-out"></span>
                </a>
            </div>
        </form>
    </div>
</nav>
