<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inregistrare Utilizator</title>
        <link href="<c:url value="/resources/css/base.css" />" rel="stylesheet">  
    </head>
    <body>

    <center><img src="<c:url value="/resources/css/img/Jademy.png"/>" alt="Jademy.png"/></center>

    <form action="${pageContext.request.contextPath}/user/doregister" method="POST" name="frm" >
        <center><p>INREGISTRARE UTILIZATOR NOU</p></center>
        <center><p><h2 class='form-signin-heading error'>${message}</h2></p></center>
        <table style="margin: auto;">

            <tr>
                <td>Prenume :</td>
                <td><input type="text" name="first_name" id="first_name" value="${first_name}" required autocomplete="off" /></td>
            </tr>
            <tr>
                <td>Nume :</td>
                <td><input type="text" name="last_name" id="last_name" value="${last_name}" required autocomplete="off" /></td>
            </tr>
            <tr>
                <td>Email :</td>
                <td><input type="text" name="email" id="email" value="${email}" required autocomplete="off" /></td>
            </tr>
            <tr>
                <td>Username :</td>
                <td><input type="text" name="uname" id="uname" value="" required autocomplete="off" /></td>
            </tr>
            <tr>
                <td>Parola :</td>
                <td><input type="password" name="pass" id="pass" value="" required autocomplete="off" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Inregistreaza"></td>
               
                
            </tr>
        </table>
    </form>
</body>
</html>
