<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://javaschool.tsystems.com/functions" %>
<html>
<head>
    <title>Список событий</title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <h2>Предстоящие события</h2>
        <table class="table table-bordered table-hover mt-2">
            <thead>
            <tr>
                <th>Дата</th>
                <th>Пациент</th>
                <th class="text-center">Назначение</th>
                <th class="text-center">Статус события</th>
                <th colspan="2" class="text-center">Изменить статус</th>
                <th>Комментарий</th>
            </tr>
            </thead>
            <c:forEach items="${events}" var="event">
                <jsp:useBean id="event" type="com.tsystems.javaschool.model.Event"/>
                <c:set var="status" scope="session" value="${event.eventStatus}"/>
                <tr <c:choose>
                    <c:when test="${status == 'STATUS_CANCELED'}">
                        data-eCanceled
                    </c:when>
                    <c:when test="${status == 'STATUS_DONE'}">
                        data-eDone
                    </c:when>
                </c:choose>>
                    <td>${fn:formatDateTime(event.dateTime)}</td>
                        <%--<td>${event.eventStatus}</td>--%>
                    <td>${event.patient.name}</td>
                    <td class="text-center">${event.procedureOrMedicine.name} <c:if
                            test="${event.dose > 0}"> ${event.dose} шт </c:if></td>
                    <td class="text-center">
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
                    <td class="text-center"><c:if test="${event.eventStatus == 'STATUS_PLANNED'}"><a class="btn btn-success" href="events/done?id=${event.id}" role="button"><span class="fa fa-check"></span></a></c:if></td>
                    <td class="text-center"><c:if test="${event.eventStatus == 'STATUS_PLANNED'}"><a class="btn btn-danger" href="events/cancel?id=${event.id}" role="button"><span class="fa fa-times"></span></a></c:if></td>
                    <td>${event.message}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>