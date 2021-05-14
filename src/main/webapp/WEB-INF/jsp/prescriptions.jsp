<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title>Список пациентов</title>
</head>
<body>
<section>
    <h3><a href="${pageContext.request.contextPath}">На главную</a></h3>
    <hr/>
    <h2>Назначения</h2>
    <h1>${patient.name} - ${patient.diagnosis}</h1>
    <a href="patients/prescriptions/create?type=medicine">Новое лекарство</a> <a href="patients/prescriptions/create?type=procedure">Новая процедура</a>

    <table border="1" cellpadding="8" cellspacing="0">

        <thead>
        <tr>
            <th>Назначение</th>
            <th>Врач</th>
            <th>Изменить</th>
            <th>Отменить</th>
        </tr>
        </thead>
        <c:forEach items="${prescriptions}" var="prescription">
            <jsp:useBean id="prescription" type="com.tsystems.javaschool.model.Prescription"/>
            <tr>
                <td>${prescription}</td>
                <td>${prescription.doctor.name}</td>
                <td><a href="patients/prescriptions/update?id=${prescription.id}">Изменить</a></td>
                <td><a href="patients/prescriptions/delete?id=${prescription.id}">Отменить</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>