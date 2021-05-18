<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Больница</title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<h3>Проект Больница</h3>
<hr>
<ul>
    <li><a href="patients">Пациенты</a></li>
</ul>
<ul>
    <li><a href="events">События</a></li>
</ul>
<ul>
    <li><a href="register">Регистрация</a></li>
</ul>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

