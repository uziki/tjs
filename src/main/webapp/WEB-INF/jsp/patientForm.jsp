<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<section>
    <hr>
    <h2>${patient.id == null ? 'Добавить пациента' : 'Изменить пациента'}</h2>
    <jsp:useBean id="patient" type="com.tsystems.javaschool.model.Patient" scope="request"/>
    <form method="post" action="patients">
        <input type="hidden" name="id" value="${patient.id}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" value="${patient.name}" name="name" required></dd>
        </dl>
        <dl>
            <dt>Диагноз:</dt>
            <dd><input type="text" value="${patient.diagnosis}" name="diagnosis"></dd>
        </dl>
        <dl>
            <dt>Номер страховки:</dt>
            <dd><input type="text" value="${patient.insuranceNumber}" name="insurance" required></dd>
        </dl>
        <dl>
            <c:if test="${patient.ill == fal}" >
            <select name="isill">
                <option value="${true}" selected>Болен</option>
                <option value="${false}">Здоров</option>
            </select>
            </c:if>
        </dl>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()" type="button">Отмена</button>
    </form>
</section>
</body>
</html>
