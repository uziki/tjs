<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <h2>Причина отмены</h2>
        <jsp:useBean id="event" type="com.tsystems.javaschool.model.Event" scope="request"/>

        <div class="input-group-prepend">
            <form method="post" action="events">
                <input type="hidden" name="id" value="${event.id}">
                <%--<p><input type="text" class="form-control-lg" name="message"></p>--%>


                <textarea name="message" class="form-control-lg" aria-label="With textarea"></textarea>
                <br>
                <br>
                <button type="submit" class="btn btn-success"><span class="fa fa-check fa-2x"></span></button>
                <button onclick="window.history.back()" class="btn btn-danger"><span class="fa fa-times fa-2x"></span>
                </button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
