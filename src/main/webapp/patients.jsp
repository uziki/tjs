<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>Список пациентов</title>
</head>
<body>
<section>
    <h3><a href="index.html">На главную</a></h3>
    <hr/>
    <h2>Пациенты</h2>
    <a href="patients?action=create">Новый пациент</a>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Пациент</th>
            <th>Диагноз</th>
            <th>Назначения</th>
            <th>Добавить назначение</th>
            <th>Редактировать назначения</th>
            <th>Выписать пациента</th>
        </tr>
        </thead>
        <c:forEach items="${patients}" var="patient">
            <jsp:useBean id="patient" type="com.tsystems.javaschool.model.Patient"/>
            <tr>
                <td>${patient.name}</td>
                <td>${patient.diagnosis}</td>
                <td>${patient.prescriptions}</td>
                <td>+</td>
                <td><a href="patients?action=update&id=${patient.id}">Изменить</a></td>
                <td><a href="patients?action=delete&id=${patient.id}">Выписать</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>