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
<jsp:include page="../fragments/headTag.jsp"/>
<body>
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="container">
    <br>
    <br>
    <br>
    <br>
    <br>
    <div class="container-fluid">
        <div align="center">
            <h1 class="text-primary">Action failed!</h1>
            <br>
            <h1>Error! There is no such page :(</h1>
        </div>
    </div>
    <br>
    <br>
    <br>
    <br>
    <br>
</div>
<!--
${exception.message}
<c:forEach items="${exception.stackTrace}" var="stackTrace">
    ${stackTrace}
</c:forEach>
-->
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
