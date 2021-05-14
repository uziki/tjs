<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<section>
    <hr>
    <h2>${prescription.id == null ? 'Назначить лекарство' : 'Изменить назначенное лекарство'}</h2>
    <jsp:useBean id="prescription" type="com.tsystems.javaschool.model.Prescription" scope="request"/>
    <form method="post" action="prescriptions">
        <input type="hidden" name="id" value="${prescription.id}">
        <dl>
            <dt>Название:</dt>
            <dd><input type="text" value="${prescription.procedureOrMedicine.name}" name="name" required></dd>
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