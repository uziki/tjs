<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://javaschool.tsystems.com/functions" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
<html>
<head>
    <title>Event list</title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <h2>Upcoming events</h2>
        <c:set var="sort" scope="session" value="${sort}"/>
        <form class="form-check-inline my-sm-2" action="events/find" method="post">
            <div class="input-group-text">
                <a class="<c:choose>
                    <c:when test="${sort == 'ALL'}">
                        btn btn-primary
                    </c:when>
                    <c:otherwise>
                        btn btn-light
                    </c:otherwise>
                </c:choose>" href="events/sort?sort=all" role="button"><span class="fa fa-sort"></span>Show all</a>
                <a class="<c:choose>
                    <c:when test="${sort == 'THIS_DAY'}">
                        btn btn-primary
                    </c:when>
                    <c:otherwise>
                        btn btn-light
                    </c:otherwise>
                </c:choose>" href="events/sort?sort=this_day" role="button"><span
                        class="fa fa-sort"></span>This day</a>
                <a class="<c:choose>
                    <c:when test="${sort == 'THIS_HOUR'}">
                        btn btn-primary
                    </c:when>
                    <c:otherwise>
                        btn btn-light
                    </c:otherwise>
                </c:choose>" href="events/sort?sort=this_hour" role="button"><span
                        class="fa fa-sort"></span>This hour</a>
                <input class="form-control-sm me-2" type="search" name="find" placeholder="${find}" aria-label="Search">
                <button class="btn btn-outline-success" type="submit"><span class="fa fa-search"></span></button>
            </div>
        </form>
        <table class="table table-bordered table-hover mt-2">
            <thead>
            <tr>
                <th>Date</th>
                <th>Patient</th>
                <th class="text-center">Prescription</th>
                <th class="text-center">Event status</th>
                <th colspan="2" class="text-center">Change status</th>
                <th>Comment</th>
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
                    <td>${event.patient.name}</td>
                    <td class="text-center">${event.procedureOrMedicine.name} <c:if
                            test="${event.dose > 0}"> ${event.dose} pcs </c:if></td>
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${status == 'STATUS_PLANNED'}">
                                Planned
                            </c:when>
                            <c:when test="${status == 'STATUS_DONE'}">
                                Done
                            </c:when>
                            <c:otherwise>
                                Canceled
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="text-center"><c:if test="${event.eventStatus == 'STATUS_PLANNED'}"><a
                            class="btn btn-success" href="events/done?id=${event.id}" role="button"><span
                            class="fa fa-check"></span></a></c:if></td>
                    <td class="text-center"><c:if test="${event.eventStatus == 'STATUS_PLANNED'}"><a
                            class="btn btn-danger" href="events/cancel?id=${event.id}" role="button"><span
                            class="fa fa-times"></span></a></c:if></td>
                    <td>${event.message}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>

</body>
</html>