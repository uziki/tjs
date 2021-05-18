<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<nav class="navbar navbar-dark bg-dark py-0">
    <div class="container">
        <div class="navbar-brand"><img src="resources/images/hospital1.png">Больница</div>
        <form class="form-inline my-2" id="login_form" action="spring_security_check" method="post">
            <div class="input-group-text">
            <input class="form-control mr-1" type="text" placeholder="Email" name="username">
            <input class="form-control mr-1" type="password" placeholder="Password" name="password">
            <button class="btn btn-success" type="submit">
                <span class="fa fa-sign-in"></span>
            </button>
                <a class ="btn btn-warning" href="register" role="button"><span class="fa fa-user-plus"></span></a>
            </div>
        </form>
    </div>
</nav>

<div class="jumbotron py-0">
    <div class="container">
        <c:if test="${param.error}">
            <div class="error">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
        </c:if>
        <c:if test="${not empty param.message}">
            <div class="message">${param.message}</div>
        </c:if>
        <%--<br/>
        <p>
            <button type="submit" class="btn btn-lg btn-primary" onclick="login('doctor@gmail.com', 'doctor')">
                Зайти как врач
            </button>
            <button type="submit" class="btn btn-lg btn-primary" onclick="login('nurse@gmail.com', 'nurse')">
                Зайти как медсестра
            </button>
        </p>
        <br/>--%>
    </div>
</div>
<script type="text/javascript">
    function login(username, password) {
        $('input[name="username"]').val(username);
        $('input[name="password"]').val(password);
        $("#login_form").submit();
    }
</script>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>