<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<section>
    <hr>
    <h2>${prescription.id == null ? 'Новое назначение' : 'Изменить назначение'}</h2>
    <jsp:useBean id="prescription" type="com.tsystems.javaschool.model.Prescription" scope="request"/>
    <form method="post" action="prescriptions">
        <input type="hidden" name="id" value="${prescription.id}">
        <dl>
            <label>Лекарство<input type="radio" name="type" value="medicine"></label>
            <label>Процедура<input type="radio" name="type" value="procedure"></label>
        </dl>
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" value="${prescription.timePattern}" name="timepattern" required></dd>
        </dl>
        <c:choose>
            <c:when test="'type' == 'medicine'">Odin</c:when>
            <c:when test="'type' == medicine">Dva</c:when>
        </c:choose>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()" type="button">Отмена</button>
    </form>
</section>

</body>
</html>
