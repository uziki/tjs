<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <h2>${patient.id == null ? 'New patient' : 'Edit patient'}</h2>
        <jsp:useBean id="patient" type="com.tsystems.javaschool.model.Patient" scope="request"/>
        <form method="post" action="patients">
            <input type="hidden" name="id" value="${patient.id}">
            <c:if test="${patient.ill == true}"><input type="hidden" name="isill" value="true"></c:if>
            <dl>
                <dt>Name:</dt>
                <dd><input type="text" value="${patient.name}" name="name" required></dd>
            </dl>
            <dl>
                <dt>Diagnosis:</dt>
                <dd><input type="text" value="${patient.diagnosis}" name="diagnosis"></dd>
            </dl>
            <dl>
                <dt>Insurance number:</dt>
                <dd><input type="text" value="${patient.insuranceNumber}" name="insurance" required></dd>
            </dl>
            <dl>
                <c:if test="${patient.ill == false}">
                    <select name="isill">
                        <option value="${true}" selected>Is ill</option>
                        <option value="${false}">Is healthy</option>
                    </select>
                </c:if>
            </dl>
            <button type="submit" class="btn btn-success"><span class="fa fa-check fa-2x"></span></button>
            <button onclick="window.history.back()" class = "btn btn-danger"><span class="fa fa-times fa-2x"></span></button>
        </form>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
