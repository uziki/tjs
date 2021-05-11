<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Пациент</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <hr>
    <h2>${param.action == 'create' ? 'Добавить пациента' : 'Изменить пациента'}</h2>
    <jsp:useBean id="patient" type="com.tsystems.javaschool.model.Patient" scope="request"/>
    <form method="post" action="patients">
        <input type="hidden" name="id" value="${patient.id}">
        <input type="hidden" name="isill" value="${true}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" value="${patient.name}" name="name" required></dd>
        </dl>
        <dl>
            <dt>Диагноз:</dt>
            <dd><input type="text" value="${patient.diagnosis}" name="diagnosis"></dd>
        </dl>
        <dl>
            <dt>Номер страховки:</dt>
            <dd><input type="text" value="${patient.insuranceNumber}" name="insurance" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
