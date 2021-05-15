<%@ page import="java.util.stream.Collectors" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title>Список пациентов</title>
</head>
<body>
<section>
    <h3><a href="${pageContext.request.contextPath}">На главную</a></h3>
    <hr/>
    <h2>Пациенты</h2>
    <a href="patients/create">Новый пациент</a>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Пациент</th>
            <th>Диагноз</th>
            <th>Врач</th>
            <th>История назначений</th>
            <th>Добавить назначение</th>
            <th>Редактировать назначения</th>
            <th>Выписать пациента</th>
        </tr>
        </thead>
        <c:forEach items="${patients}" var="patient">
            <jsp:useBean id="patient" type="com.tsystems.javaschool.model.Patient"/>
            <tr>
                <td><a href="patients/update?id=${patient.id}">${patient.name}</a></td>
                <td>${patient.diagnosis}</td>
                <td>${patient.doctorName}</td>
                <td>${patient.prescriptions}</td>
                <td>+</td>
                <td><a href="patients/prescriptions?id=${patient.id}">Изменить</a></td>
                <td><a href="patients/delete?id=${patient.id}">Выписать</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>