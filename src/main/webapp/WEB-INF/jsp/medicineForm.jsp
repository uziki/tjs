<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<section>
    <hr>
    <h2>${prescription.id == null ? "Назначить лекарство" : "Изменить назначенное лекарство"}</h2>
    <jsp:useBean id="prescription" type="com.tsystems.javaschool.model.Prescription" scope="request"/>
    <form method="post" action="patients/prescriptions">
        <input type="hidden" name="id" value="${prescription.id}">
        <input type="hidden" name="type" value="TYPE_MEDICINE">
        <input type="hidden" name="patientid" value="${patientId}">

        <dl>
            <dt>Название:</dt>
            <dd><input type="text" value="${prescription.procedureOrMedicine.name}" name="name" required <c:if test="${prescription.active}"> disabled </c:if> </dd>
        </dl>
        <dl>
            <dt>Утром:</dt>
            <dd><input type="time" name="morning"></dd>
        </dl>
        <dl>
            <dt>Днем:</dt>
            <dd><input type="time" name="afternoon"></dd>
        </dl>
        <dl>
            <dt>Вечером:</dt>
            <dd><input type="time" name="evening"></dd>
        </dl>
        <dl>
            <dt>Доза:</dt>
            <dd><input type="number" value="${prescription.dose}" name="dose" required></dd>
        </dl>
        <dl>
            <dt>Курс (кол-во дней):</dt>
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