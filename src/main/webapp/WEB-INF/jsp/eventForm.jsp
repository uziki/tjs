<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<section>
    <hr>
    <h2>Статус назначения</h2>
    <jsp:useBean id="event" type="com.tsystems.javaschool.model.Event" scope="request"/>
    <form method="post" action="events">
        <dl>
        <input type="hidden" name="id" value="${event.id}">
        <input type="radio" id="contactChoice1"
               name="status" value="STATUS_DONE" checked>
        <label for="contactChoice1">Выполнено</label>

        <input type="radio" id="contactChoice2"
               name="status" value="STATUS_CANCELED">
        <label for="contactChoice2">Отменить</label>
        </dl>
        <dl>
            <dt>Комментарий</dt>
            <dd><input type="text" name="message" style="width: 200px; height: 50px;" ></dd>
        </dl>

        <%--<dl>
            <c:if test="${patient.ill == false}" >
            <select name="isill">
                <option value="${true}" selected>Болен</option>
                <option value="${false}">Здоров</option>
            </select>
            </c:if>
        </dl>--%>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()" type="button">Отмена</button>
    </form>
</section>
</body>
</html>
