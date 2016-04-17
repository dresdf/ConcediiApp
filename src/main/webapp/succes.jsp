<%@page import="org.jfree.util.Rotation"%>
<%@page import="org.jfree.chart.plot.PiePlot3D"%>
<%@page import="com.jademy.concediiapp.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="java.sql.*"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cerere Noua de Concediu</title>
        <link href="login.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/themes/base/jquery-ui.css" type="text/css" media="all" />

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>

        <script type="text/javascript">
            $(function () {
                $('#datastart').datepicker({dateFormat: 'yy-mm-dd'}).datepicker("setDate", new Date());
                $('#datastart').datepicker();
                $('#datafinal').datepicker({dateFormat: 'yy-mm-dd'}).datepicker("setDate", new Date());
                $('#datafinal').datepicker();
            });
        </script>

        <script type="text/javascript">
            $(document).ready(function () {
                $('#id').attr('readonly', true);
                $('#id').addClass('input-disabled');
            });
        </script>

        <script>
            $(function () {
                $("#tabs").tabs();
            });
        </script>

        <script>
            function validateForm()
            {
                if (document.frm.datastart.value > document.frm.datafinal.value)
                {
                    alert("Data de inceput trebuie sa fie mai mica decat data de sfarsit");
                    document.frm.datastart.focus();
                    return false;
                }
            }
        </script>

        <script type="text/javascript">
            function setHiddenId(val) {
                document.getElementById('hiddenid').value = val;
                alert('Cererea de concediu cu numarul ' + val + ' a fost aprobata');
            }
        </script>

        <script type="text/javascript">
            function setHiddenIdReject(val) {
                document.getElementById('hiddenidreject').value = val;
                alert('Cererea de concediu cu numarul ' + val + ' a fost respinsa');
            }
        </script>

    </head>
    <body>
    <center><img src="css/img/Jademy.png" alt="Jademy.png"/></center>
    <h1>APLICATIE CONCEDII - Echipa Jademy #8</h1>
    <a href='logoutServlet'>Deconectare [<% User currentUser = (User) session.getAttribute("currentuser"); out.print(currentUser.getUsername());%>]</a>
</body>
</html>
