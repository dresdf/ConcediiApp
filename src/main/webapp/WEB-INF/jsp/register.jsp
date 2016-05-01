<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ConcediiApp - Signup</title>
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
        <link href="../css/base.css" rel="stylesheet">
        <link href="../css/index.css" rel="stylesheet">

        <script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>  
    </head>
    <body>
        <form class="form-signin" action="${pageContext.request.contextPath}/user/doregister" method="POST" name="frm" >
            <center>
                <h2 class="form-signin-heading">INREGISTRARE UTILIZATOR NOU</h2>
            </center>
            <center>
                <h2 class='form-signin-heading error'>Error message ${message}</h2>
            </center>
            <input class="form-control" type="text" name="first_name" id="first_name" value="${first_name}" placeholder="Prenume" required autocomplete="off" />
            <input class="form-control" type="text" name="last_name" id="last_name" value="${last_name}" placeholder="Nume" required autocomplete="off" />
            <input class="form-control" type="text" name="email" id="email" value="${email}" placeholder="Email" required autocomplete="off" />
            <input class="form-control" type="text" name="uname" id="uname" value="" placeholder="Username" required autocomplete="off" />
            <input class="form-control" type="password" name="pass" id="pass" value="" placeholder="Parola" required autocomplete="off" />
            <input class="btn btn-lg btn-primary btn-block"  type="submit" value="Inregistreaza">
        </form>
    </body>
</html>
