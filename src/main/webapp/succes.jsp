<%@page import="com.jademy.concediiapp.Cerere"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.jfree.util.Rotation"%>
<%@page import="org.jfree.chart.plot.PiePlot3D"%>
<%@page import="com.jademy.concediiapp.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="java.sql.*"%>
<%@ page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cerere Noua de Concediu</title>
        <link href="css/login.css" rel="stylesheet" type="text/css">
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
    <a href='logoutServlet'>Deconectare [<%
        User currentUser = (User) session.getAttribute("currentuser");
        out.print(currentUser.getUsername());
        %>]</a>

    <div id="tabs">
        <ul>
            <li><a href="#cerere">Cerere Noua</a></li>
            <li><a href="#aprobare">Aprobare Concedii</a></li>
            <li><a href="#rapoarte">Rapoarte</a></li>
            <li><a href="#profil">Profil</a></li>
        </ul>

        <!-- cerere -->
        <div id="cerere">
            <form action="cerereServlet" method="post" name="frm" onSubmit="return validateForm()">
                <p>Situatia concediilor la data: <%= (new java.util.Date())%></p>
                <table class="imagetable">
                    <tr>
                        <th>ID</th>
                        <th>PRENUME</th>
                        <th>NUME</th>
                        <th>EMAIL</th>
                        <th>TIP CONCEDIU</th>
                        <th>DATA START</th>
                        <th>DATA FINAL</th>
                        <th>ZILE</th>
                        <th>STATUS</th>
                    </tr>
                    <c:forEach items="${listacereri}" var="item">
                        <tr>
                            <td>${item.ID}</td>
                            <td>${item.firstName}</td>
                            <td>${item.lastName}</td>
                            <td>${item.email}</td>
                            <td>${item.tipConcediu}</td>
                            <td>${item.dataStart}</td>
                            <td>${item.dataFinal}</td>
                            <td>${item.nrZile}</td>
                            <td>${item.status}</td>
                        </tr>
                    </c:forEach>
                </table>
                <p>CERERE NOUA DE CONCEDIU</p>
                
                <table>
                    <tr>
                        <td>Utilizator :</td>
                        <td><input type="text" name="id" id="id" value="${currentuser.username}"/></td>
<%-- TODO: servlet to load picture
<td rowspan="7"><img src="/mavenproject3/pictureServlet?id=${currentuser.username}" width="200" border="1"/></td>--%>
                        <td rowspan="7"><img src="css/img/skull.jpg" width="200" border="1"/></td>

                    </tr>
                    <tr>
                        <td>Prenume :</td>
                        <td><input type="text" name="first_name" value="${currentuser.firstName}"/></td>
                    </tr>
                    <tr>
                        <td>Nume :</td>
                        <td><input type="text" name="last_name" value="${currentuser.lastName}"/></td>
                    </tr>
                    <tr>
                        <td>Email :</td>
                        <td><input type="text" name="email" value="${currentuser.email}"/></td>
                    </tr>
                    <tr>
                        <td>Tip Concediu :</td>
                        <td>
                            <select name="dropdown" id="dropdown">
                                <option value="Concediu De Odihna" selected>Concediu De Odihna</option>
                                <option value="Concediu De Studii">Concediu De Studii</option>
                                <option value="Concediu Medical">Concediu Medical</option>
                                <option value="Concediu Fara Plata">Concediu Fara Plata</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Data Start :</td>
                        <td><input type="text" name="datastart" id="datastart" /></td>
                    </tr>
                    <tr>
                        <td>Data Final :</td>
                        <td><input type="text" name="datafinal" id="datafinal"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="left"><p style="color: coral">${message}</p></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="left"><input type="submit" value="Depune Cererea"></td>
                    </tr>
                </table>
            </form>
        </div>

    </div>






</body>
</html>
