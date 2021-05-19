<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>
<body class="text-center">
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
<h1 class="h3 mb-3 fw-normal">Регистрация</h1>
<div class="container">
    <form method="post" action="register">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" placeholder="Name" name="name" required></dd>
        </dl>
        <dl>
            <dt>Email:</dt>
            <dd><input type="email" placeholder="Email" name="email" required></dd>
        </dl>
        <dl>
            <dt>Пароль:</dt>
            <dd><input type="text" placeholder="Password" name="password" required></dd>
        </dl>
        <dl>
            <select name="role">
                <option value="DOCTOR" selected>Врач</option>
                <option value="NURSE">Медсестра</option>
            </select>
        </dl>
        <c:if test="${not empty param.message}">
            <div class="message">Пользователь с таким email уже существует!</div>
        </c:if>
        <button type="submit" class="btn btn-success"><span class="fa fa-check fa-2x"></span></button>
        <button onclick="window.history.back()" class="btn btn-danger"><span class="fa fa-times fa-2x"></span></button>
    </form>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
