<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <h2>${prescription.id == null ? "Prescribe procedure" : "Change prescribed procedure"}</h2>
        <jsp:useBean id="prescription" type="com.tsystems.javaschool.model.Prescription" scope="request"/>
        <form method="post" action="patients/prescriptions">
            <input type="hidden" name="id" value="${prescription.id}">
            <input type="hidden" name="type" value="TYPE_PROCEDURE">
            <input type="hidden" name="patientId" value="${patientId}">
            <input type="hidden" name="dose" value=0>

            <dl>
                <dt>Name:</dt>
                <dd><input type="text" value="${prescription.procedureOrMedicine.name}" name="name" required
                <c:if test="${prescription.active}"> disabled </c:if> </dd>
            </dl>
            <dl>
                <dt>Time</dt>
            </dl>
            <dl>
                <dt>Monday:</dt>
                <dd><input type="time" value="${day0}" name="monday"></dd>
            </dl>
            <dl>
                <dt>Tuesday:</dt>
                <dd><input type="time" value="${day1}" name="tuesday"></dd>
            </dl>
            <dl>
                <dt>Wednesday:</dt>
                <dd><input type="time" value="${day2}" name="wednesday"></dd>
            </dl>
            <dl>
                <dt>Thursday:</dt>
                <dd><input type="time" value="${day3}" name="thursday"></dd>
            </dl>
            <dl>
                <dt>Friday:</dt>
                <dd><input type="time" value="${day4}" name="friday"></dd>
            </dl>
            <dl>
                <dt>Saturday:</dt>
                <dd><input type="time" value="${day5}" name="saturday"></dd>
            </dl>
            <dl>
                <dt>Sunday:</dt>
                <dd><input type="time" value="${day6}" name="sunday"></dd>
            </dl>
            <dl>
                <dt>Number of weeks:</dt>
                <dd><input type="number" min="1" value="${prescription.timePeriod}" name="timePeriod" required></dd>
            </dl>

            <c:if test="${prescription.active}">
                <input type="hidden" name="name" value="${prescription.procedureOrMedicine.name}">
            </c:if>
            <button type="submit" class="btn btn-success"><span class="fa fa-check fa-2x"></span></button>
            <button onclick="window.history.back()" class = "btn btn-danger"><span class="fa fa-times fa-2x"></span></button>
        </form>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>