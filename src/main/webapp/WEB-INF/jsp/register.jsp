<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>
<body class="text-center">
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
        <button type="submit">Зарегистрировать</button>
        <button onclick="window.history.back()" type="button">Отмена</button>
    </form>
</div>


</body>
</html>
