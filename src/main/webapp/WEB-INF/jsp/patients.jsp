<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Patient list</title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center">Patients</h3>
        <a class="btn btn-primary" href="patients/create" role="button"><span class="fa fa-plus"></span>New patient</a>
        <table class="table  table-bordered table-hover mt-2">
            <thead>
            <tr>
                <th>Patient</th>
                <th>Diagnosis</th>
                <th>Doctor</th>
                <%--<th>Prescription history</th>--%>
                <th class="text-center">Edit prescriptions</th>
                <th class="text-center">Discharge a patient</th>
            </tr>
            </thead>
            <c:forEach items="${patients}" var="patient">
                <jsp:useBean id="patient" type="com.tsystems.javaschool.model.Patient"/>
                <tr data-ill="${patient.ill}">
                    <td><a href="patients/update?id=${patient.id}">${patient.name}</a></td>
                    <td>${patient.diagnosis}</td>
                    <td>${patient.doctorName}</td>
                    <%--<td>${patient.prescriptions}</td>--%>
                    <td class="text-center"><a href="patients/prescriptions?id=${patient.id}"><span class="fa fa-pencil fa-2x"></span></a></td>
                    <td class="text-center"><a href="patients/delete?id=${patient.id}"><span class="fa fa-remove fa-2x"></span></a></td>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>