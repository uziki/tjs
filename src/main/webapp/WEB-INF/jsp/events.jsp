<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://javaschool.tsystems.com/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title>Список событий</title>
</head>
<body>
<section>
    <h3><a href="${pageContext.request.contextPath}">На главную</a></h3>
    <hr/>
    <h2>Предстоящие события</h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Пациент</th>
            <th align="center">Назначение</th>
            <th>Дата</th>
            <th align="center">Статус события</th>
            <th>Изменить статус</th>
            <th>Комментарий</th>
        </tr>
        </thead>
        <c:forEach items="${events}" var="event">
            <jsp:useBean id="event" type="com.tsystems.javaschool.model.Event"/>
            <tr>
                <td>${event.patient.name}</td>
                <td align="center">${event.procedureOrMedicine.name} <c:if
                        test="${event.dose > 0}"> ${event.dose} шт </c:if></td>
                <td>${fn:formatDateTime(event.dateTime)}</td>
                    <%--<td>${event.eventStatus}</td>--%>
                <td align="center"><c:set var="status" scope="session" value="${event.eventStatus}"/>
                    <c:choose>
                        <c:when test="${status == 'STATUS_PLANNED'}">
                            Запланировано
                        </c:when>
                        <c:when test="${status == 'STATUS_DONE'}">
                            Выполнено
                        </c:when>
                        <c:otherwise>
                            Отменено
                        </c:otherwise>
                    </c:choose>
                </td>
                <td><c:if test="${event.eventStatus == 'STATUS_PLANNED'}"><a href="events/update?id=${event.id}">Изменить
                    статус</a></c:if></td>
                <td>${event.message}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>