<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<section>
    <hr>
    <h2>${prescription.id == null ? "Назначить процедуру" : "Изменить назначенную процедуру"}</h2>
    <jsp:useBean id="prescription" type="com.tsystems.javaschool.model.Prescription" scope="request"/>
    <form method="post" action="patients/prescriptions">
        <input type="hidden" name="id" value="${prescription.id}">
        <input type="hidden" name="type" value="TYPE_PROCEDURE">
        <input type="hidden" name="patientid" value="${patientId}">
        <input type="hidden" name="dose" value=0>

        <dl>
            <dt>Название:</dt>
            <dd><input type="text" value="${prescription.procedureOrMedicine.name}" name="name" required <c:if test="${prescription.active}"> disabled </c:if> </dd>
        </dl>
        <dl>
            <dt>Понедельник:</dt>
            <dd><input type="time" name="monday"></dd>
        </dl>
        <dl>
            <dt>Вторник:</dt>
            <dd><input type="time" name="tuesday"></dd>
        </dl>
        <dl>
            <dt>Среда:</dt>
            <dd><input type="time" name="wednesday"></dd>
        </dl>
        <dl>
            <dt>Четверг:</dt>
            <dd><input type="time" name="thursday"></dd>
        </dl>
        <dl>
            <dt>Пятница:</dt>
            <dd><input type="time" name="friday"></dd>
        </dl>
        <dl>
            <dt>Суббота:</dt>
            <dd><input type="time" name="saturday"></dd>
        </dl>
        <dl>
            <dt>Воскресенье:</dt>
            <dd><input type="time" name="sunday"></dd>
        </dl>
        <dl>
            <dt>Курс (кол-во недель):</dt>
            <dd><input type="number" value="${prescription.timePeriod}" name="timeperiod" required></dd>
        </dl>

        <c:if test="${prescription.active}">
            <input type="hidden" name="name" value="${prescription.procedureOrMedicine.name}">
        </c:if>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()" type="button">Отмена</button>
    </form>


</section>
</body>
</html>