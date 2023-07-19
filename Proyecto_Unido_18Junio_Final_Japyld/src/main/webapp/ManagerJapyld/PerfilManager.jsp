<%@ page import="com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.ImagenPerfilDto" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: milene
  Date: 22/06/2023
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>
<% ArrayList<ImagenPerfilDto> listaFotoPerfil = (ArrayList<ImagenPerfilDto>) request.getAttribute("listaFotoPerfil"); %>
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
        <style>
            .img-perfil-principal {
                width: 300px; /* Ajusta el tamaño del círculo según tus necesidades */
                height: 300px;
            }
        </style>
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

                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between p-a">
                            <h1 class="h3 mb-4 text-gray-700">Perfil</h1>
                        </div>
                        <!-- Información del perfil -->
                        <div class="row">
                            <!-- Imagen de Perfil-->
                            <div class="col-lg-4">
                                <div class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-center align-items-center" style="height: 100%">
                                            <% for (ImagenPerfilDto fotoperfil : listaFotoPerfil) { %>
                                            <% if (personaSession.getIdPersona() == fotoperfil.getIdPersona()) { %>
                                            <img src="<%=fotoperfil.getDireccion_archivo()%>" class="img-perfil-principal" />
                                            <% } %>
                                            <% } %>
                                        </div>
                                        <hr>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-8">
                                <div class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-header py-2">
                                        <h5 class="mb-0 font-weight-bold text-primary">
                                            Información del manager
                                        </h5>
                                    </div>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <p class="mb-0 text-gray-900">Nombres</p>
                                            </div>
                                            <div class="col-sm-9">
                                                <p class="text-muted mb-0 justify-content-center"><%=personaSession.getNombre()%></p>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <p class="mb-0 text-gray-900">Apellidos</p>
                                            </div>
                                            <div class="col-sm-9">
                                                <p class="text-muted mb-0 justify-content-center"><%=personaSession.getApellido()%></p>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <p class="mb-0 text-gray-900">Fecha de nacimiento</p>
                                            </div>
                                            <div class="col-sm-9">
                                                <p class="text-muted mb-0 justify-content-center"><%=personaSession.getFechaDeNacimiento()%></p>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <p class="mb-0 text-gray-900">Email</p>
                                            </div>
                                            <div class="col-sm-9">
                                                <p class="text-muted mb-0 justify-content-center"><%=personaSession.getCorreo()%></p>
                                            </div>
                                        </div>
                                        <hr class="mb-5"/>
                                        <a href="<%=request.getContextPath()%>/LoginServlet?action=logout" class="btn btn-danger">Cerrar sesión</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <br>
                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; JAPYLD Solutions 2023</span>
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

