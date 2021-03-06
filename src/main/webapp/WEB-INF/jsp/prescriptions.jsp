<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Prescription list</title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <h2>Prescriptions</h2>
        <h1>${patient.name} - ${patient.diagnosis}</h1>
        <a class="btn btn-primary" href="patients/prescriptions/create?patientId=${patient.id}&type=medicine" role="button"><span class="fa fa-plus"></span>New medicine</a>
        <a class="btn btn-primary" href="patients/prescriptions/create?patientId=${patient.id}&type=procedure" role="button"><span class="fa fa-plus"></span>New procedure</a>
        <table class="table  table-bordered table-hover mt-2">
            <thead>
            <tr>
                <th>Prescription</th>
                <th class="text-center">Doctor</th>
                <th class="text-center">Edit</th>
                <th class="text-center">Cancel</th>
            </tr>
            </thead>
            <c:forEach items="${prescriptions}" var="prescription">
                <jsp:useBean id="prescription" type="com.tsystems.javaschool.model.Prescription"/>
                <tr data-prescrtiptionActive="${prescription.active}">
                    <td>${prescription}</td>
                    <td class="text-center">${prescription.doctor.name}</td>
                    <c:if test="${prescription.active}">
                        <td class="text-center"><a href="patients/prescriptions/update?id=${prescription.id}"><span class="fa fa-pencil fa-2x"></span></a></td>
                        <td class="text-center"><a href="patients/prescriptions/delete?id=${prescription.id}&patientId=${patient.id}"><span class="fa fa-remove fa-2x"></span></a>
                        </td>
                    </c:if>
                    <c:if test="${prescription.active == false}">
                        <td colspan="2" class="text-center">Canceled</td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>