<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Регистрация</title>
</head>
<body>
<h1>Регистрация персонала</h1>
<form action="register" method="post">
    <table style="with: 50%">
        <tr>
            <td>ФИО</td>
            <td><input type="text" name="name" /></td>
        </tr>
        <tr>
            <td>email</td>
            <td><input type="text" name="Электронная почта" /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="Пароль" /></td>
        </tr>

        <tr><select name="isDoctor">
        <option value="1" selected>Врач</option>
        <option value="0">Медсестра</option>
        </select></tr>
        </table>
    <input type="submit" value="Submit" /></form>
</body>
</html>