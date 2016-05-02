<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
        <link href="../css/main.css" rel="stylesheet">

        <script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

        <title>User Main Page</title>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">ConcediiApp</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">Dashboard</a></li>
                        <li><a href="#">Settings</a></li>
                        <li><a href="#">Profile</a></li>
                        <li><a href="#">Help</a></li>
                    </ul>
                    <form class="navbar-form navbar-right">
                        <input type="text" class="form-control" placeholder="Search...">
                    </form>
                </div>
            </div>
        </nav>

        <!--content-->
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <ul class="nav nav-sidebar nav-tabs nav-stacked">
                        <li><a href="#cerere" data-toggle="tab"> Cerere noua <span class="sr-only">(current)</span></a></li>
                        <li><a href="#istoric" data-toggle="tab">Istoric</a></li>
                        <li><a href="#rapoarte" data-toggle="tab">Rapoarte</a></li>
                        <li><a href="#profil" data-toggle="tab">Profil</a></li>
                    </ul>
                </div>
                <div  class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main tab-content">
                    <center><h1 class="page-header">Dashboard</h1></center>
                    <!-- Cerere -->
                    <div id="cerere" class="row placeholders table-responsive  tab-pane fade">
                        <p>Situatia concediilor la data: <%= (new java.util.Date())%></p>
                        <table class="table table-striped">
                            <tr>
                                <th>ID</th>
                                <th>TIP CONCEDIU</th>
                                <th>DATA START</th>
                                <th>DATA FINAL</th>
                                <th>ZILE</th>
                                <th>STATUS</th>
                            </tr>
                            <c:forEach items="${listacereri}" var="item">
                                <tr>
                                    <td>${item.requestID}</td>
                                    <td>${item.tipConcediu}</td>
                                    <td>${item.dataStart}</td>
                                    <td >${item.dataFinal}</td>
                                    <td>${item.duration}</td>
                                    <td>${item.status}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <p>CERERE NOUA DE CONCEDIU</p>
                        <form class="form-inline" action="${pageContext.request.contextPath}/cerere/adauga" method="post" name="frm" onSubmit="return validateForm()" role="form">

                            <div class="form-group">
                                <label for="dropdown">Tip Concediu :</label>
                                <select name="dropdown" id="dropdown">
                                    <option value="Concediu De Odihna" selected>Concediu De Odihna</option>
                                    <option value="Concediu De Studii">Concediu De Studii</option>
                                    <option value="Concediu Medical">Concediu Medical</option>
                                    <option value="Concediu Fara Plata">Concediu Fara Plata</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="datastart">Data Start :</label>
                                <input type="date" name="datastart" id="datastart"/>
                            </div>
                            <div class="form-group">
                                <label for="datafinal">Data Final :</label>
                                <input type="date" name="datafinal" id="datafinal"/>
                            </div>
                            <p style="color: coral">${message}</p>
                            <input type="submit" class="btn btn-default" value="Depune Cererea">
                        </form>
                           
                    </div>

                    <!-- Istoric -->
                    <div id="istoric" class="table-responsive tab-pane fade">
                        <h2 class="sub-header">Section title</h2>
                        <form action="${pageContext.request.contextPath}/cerere/aproba" method="post" name="frm1">
                            <p>Situatia concediilor de aprobat la data: <%= (new java.util.Date())%></p>
                            <table class="imagetable table table-striped">
                                <tr>
                                    <th>ID</th>
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

                    <!-- Rapoarte -->
                    <div id="rapoarte" class="row placeholders tab-pane fade">
                        <div class="col-xs-6 col-sm-3 placeholder">
                            <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                            <h4>Label</h4>
                            <span class="text-muted">Something else</span>
                        </div>
                        <div class="col-xs-6 col-sm-3 placeholder">
                            <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                            <h4>Label</h4>
                            <span class="text-muted">Something else</span>
                        </div>
                        <div class="col-xs-6 col-sm-3 placeholder">
                            <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                            <h4>Label</h4>
                            <span class="text-muted">Something else</span>
                        </div>
                        <div class="col-xs-6 col-sm-3 placeholder">
                            <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                            <h4>Label</h4>
                            <span class="text-muted">Something else</span>
                        </div>
                    </div>

                    <!-- Profil -->
                    <div id="profil" class="row placeholders tab-pane fade">
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
        </div>
    </body>
</html>
