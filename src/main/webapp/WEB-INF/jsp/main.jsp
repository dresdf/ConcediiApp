<%@page import="java.util.ArrayList"%>
<%@page import="org.jfree.util.Rotation"%>
<%@page import="org.jfree.chart.plot.PiePlot3D"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
        <script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <title>Cerere Noua de Concediu</title>
        <link href="../css/base.css" rel="stylesheet">
        <script src="../js/main.js"></script>

<!--        <script type="text/javascript">
            $(function () {
                $('#datastart').datepicker({dateFormat: 'yy-mm-dd'}).datepicker("setDate", new Date());
                $('#datastart').datepicker();
                $('#datafinal').datepicker({dateFormat: 'yy-mm-dd'}).datepicker("setDate", new Date());
                $('#datafinal').datepicker();
            });
        </script>-->

        
        <script type="text/javascript">
            jQuery(function () {
                jQuery('#tabs li a').tab('show');
            })
        </script>

    </head>
    <body>
        <div id="content" class="container">
            <center><img src="../css/img/Jademy.png"/></center>
            <h1>APLICATIE CONCEDII - Echipa Jademy #8</h1>
            <a href='${pageContext.request.contextPath}/user/logout'>Deconectare [${currentuser.firstName}]</a>


            <ul id="tabs" class="nav nav-pills" data-tabs="tabs">
                <li role="presentation"><a href="#cerere" data-toggle="tab">Cerere Noua</a></li>
                <li role="presentation"><a href="#aprobare" data-toggle="tab">Aprobare Concedii</a></li>
                <li role="presentation"><a href="#rapoarte" data-toggle="tab">Rapoarte</a></li>
                <li role="presentation"><a href="#profil" data-toggle="tab">Profil</a></li>
            </ul>
            <div id="my-tab-content" class="tab-content">
                <!-- cerere -->
                <div id="cerere" class="tab-pane">
                    <form action="${pageContext.request.contextPath}/cerere/adauga" method="post" name="frm" onSubmit="return validateForm()">
                        <p>Situatia concediilor la data: <%= (new java.util.Date())%></p>
                        <table class="imagetable">
                            <tr>
                                <th>ID</th>
<!--                                <th>PRENUME</th>
                                <th>NUME</th>
                                <th>EMAIL</th>-->
                                <th>TIP CONCEDIU</th>
                                <th>DATA START</th>
                                <th>DATA FINAL</th>
                                <th>ZILE</th>
                                <th>STATUS</th>
                            </tr>
                            <c:forEach items="${listacereri}" var="item">
                                <tr>
                                    <td>${item.ID}</td>
<%--                                    <td>${item.firstName}</td>
                                    <td>${item.lastName}</td>
                                    <td>${item.email}</td>--%>
                                    <td>${item.tipConcediu}</td>
                                    <td>${item.dataStart}</td>
                                    <td>${item.dataFinal}</td>
                                    <td>${item.duration}</td>
                                    <td>${item.status}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <p>CERERE NOUA DE CONCEDIU</p>

                        <table>
                            <tr>
                                <td>Utilizator :</td>
                                <td><input type="text" name="id" id="id" value="username"/></td>
                                <td rowspan="7"><img src="<c:url value="/resources/profile/${currentuser.poza}" />" width="200" border="1" alt="Profile Picture" /></td>
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

                <!-- aprobare -->
                <div id="aprobare" class="tab-pane">

                    <form action="${pageContext.request.contextPath}/cerere/aproba" method="post" name="frm1">
                        <p>Situatia concediilor de aprobat la data: <%= (new java.util.Date())%></p>
                        <table class="imagetable">
                            <tr>
                                <th>ID</th>
<!--                                <th>PRENUME</th>
                                <th>NUME</th>
                                <th>EMAIL</th>-->
                                <th>TIP CONCEDIU</th>
                                <th>DATA START</th>
                                <th>DATA FINAL</th>
                                <th>ZILE</th>
                                <th>STATUS</th>
                            </tr>
                            <c:forEach items="${listapending}" var="item">
                                <tr>
                                    <td>${item.ID}</td>
                                    <%--<td>${item.firstName}</td>
                                    <td>${item.lastName}</td>
                                    <td>${item.email}</td>--%>
                                    <td>${item.tipConcediu}</td>
                                    <td>${item.dataStart}</td>
                                    <td>${item.dataFinal}</td>
                                    <td>${item.duration}</td>
                                    <td>${item.status}</td>
                                    <td><input type="submit" id="btnApprove" onclick="setHiddenId(${item.ID});" value="Aproba Cererea" /></td> 
                                    <td><input type="submit" id="btnReject" onclick="setHiddenIdReject(${item.ID});" value="Respinge Cererea"/></td> 
                                </tr>
                            </c:forEach>
                        </table>
                        <input type="hidden" name="hiddenid" id="hiddenid"/>   
                        <input type="hidden" name="hiddenidreject" id="hiddenidreject"/>  
                    </form>

                </div>

                <!-- rapoarte -->
                <div id="rapoarte"  class="tab-pane">
                    <%--
                    <form method="post">
                        <%@ page import="java.awt.*" %>
                        <%@ page import="java.io.*" %>
                        <%@ page import="org.jfree.chart.*" %>
                        <%@ page import="org.jfree.chart.entity.*" %>
                        <%@ page import ="org.jfree.data.general.*"%>
                        <%
                            DbUtils dbu = new DbUtils();
                            DefaultPieDataset dataset = dbu.showPieChart(currentUser);

                    JFreeChart chart = ChartFactory.createPieChart("Raport concedii efectuate - " + currentUser.getUsername(), dataset, true, true, false);
                    try {
                        final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
//                    final File file1 = new File(getServletContext().getRealPath(".") + "/" + session.getAttribute("userid") + "_piechart.png");
                        final File file1 = new File(getServletContext().getRealPath("/") + currentUser.getUsername() + "_piechart.png");

                        System.out.println(getServletContext().getRealPath("."));
                        ChartUtilities.saveChartAsPNG(file1, chart, 500, 400, info);
                    } catch (Exception e) {
                        out.println(e);
                    }
                %>      
                <img src="${currentuser.username}_piechart.png" width="500" height="400" border="0" usemap="#chart">
                <img src="${currentuser.username}_piechart.png" width="500" height="400" border="0" usemap="#chart">
            </form>
                    --%>
                </div>

                <!-- profil -->
                <div id="profil" class="tab-pane">

                    <form action="" method="post" name="frm2" enctype="multipart/form-data">
                        <p>ACTUALIZARE PROFIL UTILIZATOR</p>
                        <table>
                            <tr>
                                <td>Utilizator :</td>
                                <td><input type="text" name="id" id="id" value="username"/></td>
                                <td rowspan="7"><img src="<c:url value="/resources/profile/${currentuser.poza}"/>" width="200" border="1"/></td>
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
                                <td colspan="2"><input type="file" name="file" disabled/></td>
                            </tr> 
                            <tr>
                                <td colspan="2"><input type="submit" value="Upload" disabled/></td>
                            </tr> 
                            <tr>
                                <td></td>
                            </tr> 
                        </table>
                        <input type="text" name="utilizator" id="utilizator" value="username"/>
                    </form>

                </div>
            </div>
        </div>
    </body>
</html>
