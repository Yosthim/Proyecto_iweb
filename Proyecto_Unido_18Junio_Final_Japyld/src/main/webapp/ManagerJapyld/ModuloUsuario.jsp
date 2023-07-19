<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.ModuloUsuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>
<% ArrayList<ModuloUsuario> listaUsersActivos = (ArrayList<ModuloUsuario>) request.getAttribute("listaUserActivo"); %>
<% ArrayList<ModuloUsuario> listaUsersBaneados = (ArrayList<ModuloUsuario>) request.getAttribute("listaUserBaneado"); %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Por Genero de Juego</title>

    <title>Gráfico de Barras con Bootstrap 4</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <!-- Custom fonts for this template-->
    <link href="recursos/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="recursos/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <jsp:include page="/Includes/ManagerJapyld/SideBarManager.jsp">
        <jsp:param name="title" value="SideBar General"/>
    </jsp:include>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <jsp:include page="/Includes/ManagerJapyld/topbarManager.jsp">
                <jsp:param name="title" value="Mas detalles"/>
            </jsp:include>

            <!-- Begin Page Content -->
            <div class="container-fluid">
                <!-- Account page navigation-->
                <br>
                <div class="col-xl-12">
                    <!-- Profile picture card-->
                    <div class="card mb-4 mb-xl-0">

                        <div class="card-header font-weight-bold text-primary">TABLA DE USUARIOS</div>
                        <div class="card-body text">
                            <div class="table-responsive">
                                <table class="table table-bordered" width="100%" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th class="text-center">Usuarios</th>
                                        <th class="text-center">Correo Electronico</th>
                                        <th class="text-center">Juegos Comprados/Reservados</th>
                                        <th class="text-center">Fecha De Registro</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <% for (ModuloUsuario um : listaUsersActivos) { %>
                                    <tr>
                                        <td>
                                            <div class="d-flex align-items-center">
                                                <label> <img src="<%=um.getDireccionImagen()%>" alt="" style="width: 45px; height: 45px;"></label>
                                                <div class="d-flex align-items-center">
                                                    <ul>
                                                        <a href="<%=request.getContextPath()%>/DetalleUsuarioServlet?nm=<%=String.valueOf(um.getId())%>" style="color:rgb(115,115,115);"><%=um.getNombre() + " " + um.getApellido()%></a>
                                                    </ul>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="text-center"><%=um.getCorreo()%></td>
                                        <td class="text-center"><%=um.getNumJuegos()%></td>
                                        <td class="text-center"><%=um.getFechaRegistro()%></td>
                                    </tr>
                                    <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <br>
                    <br>
                </div>
                <br>
                <div class="col-xl-12">
                    <!-- Profile picture card-->
                    <div class="card mb-4 mb-xl-0">

                        <div class="card-header font-weight-bold text-primary">USUARIOS BANEADOS</div>
                        <div class="card-body text">
                            <div class="table-responsive">
                                <table class="table table-bordered" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th class="text-center">Usuarios</th>
                                            <th class="text-center">Correo Electronico</th>
                                            <th class="text-center">Numero De Juegos</th>
                                            <th class="text-center">Fecha De Registro</th>
                                            <th class="text-center"> </th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <% for (ModuloUsuario ubaneado : listaUsersBaneados) { %>
                                        <tr>
                                            <td>
                                                <div class="d-flex align-items-center">
                                                    <label> <img src="<%=ubaneado.getDireccionImagen()%>" alt="" style="width: 45px; height: 45px;"></label>
                                                    <div class="d-flex align-items-center">
                                                        <ul>
                                                            <a href="<%=request.getContextPath()%>/DetalleUsuarioServlet?nm=<%=String.valueOf(ubaneado.getId())%>" style="color:rgb(115,115,115);"><%=ubaneado.getNombre() + " " + ubaneado.getApellido()%></a>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="text-center"><%=ubaneado.getCorreo()%></td>
                                            <td class="text-center"><%=ubaneado.getNumJuegos()%></td>
                                            <td class="text-center"><%=ubaneado.getFechaRegistro()%></td>
                                            <td class="text-center">
                                                <div class="center">
                                                    <a class="btn btn-warning" href="<%=request.getContextPath()%>/DetalleUsuarioServlet?action=noban&idUser=<%=ubaneado.getId()%>&desti=<%=ubaneado.getCorreo()%>">Desbanear</a>
                                                </div>
                                            </td>

                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <br>
                    <br>
                </div>
            </div>
            <br>
        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2021</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <jsp:include page="/Includes/ManagerJapyld/Adicionales.jsp">
        <jsp:param name="title" value="Adicionales"/>
    </jsp:include>

</body>

</html>
