<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
        <link href="css/base.css" rel="stylesheet">
        <link href="css/index.css" rel="stylesheet">

        <script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="js/index.js"></script>

        <title>ConcediiApp - Login</title>



    </head>
    <body>
        <div class="container">
            <span id="clock" name="clock">Starting...</span>
            <div style="clear: both"></div>
            <center>
                <h2 id="title" class="form-signin-heading">Vacation Planner 2.4</h2>
            </center>

            <form class="form-signin" action="${pageContext.request.contextPath}/user/login" method="POST" name="frm">
                <p class='form-signin-heading error'>${message}</p>
                <input type="text" name="username" class="form-control" placeholder="Username" required autofocus autocomplete="off" value="drasec"/>
                <input type="password" name="password" class="form-control" placeholder="Password" required value="1234"/>
                <!--<div class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> Remember me
                    </label>
                </div>-->
                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Conectare">
                <center><h3>Nu esti inregistrat? <a href="${pageContext.request.contextPath}/user/register">Inregistreaza-te</a></h3></center>
            </form>
        </div>



    </body>
</html>