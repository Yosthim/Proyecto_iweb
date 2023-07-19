<%@ page import="com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.ImagenPerfilDto" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: PABLO
  Date: 21/06/2023
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>
<% ArrayList<ImagenPerfilDto> listaFotoPerfil = (ArrayList<ImagenPerfilDto>) request.getAttribute("listaFotoPerfil"); %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Perfil</title>

    <style>
        /* Estilos personalizados */
        .custom-textbox {
            border: 2px solid #ffffff;
            border-radius: 10px;
            padding: 10px;
            font-size: 18px;
            color: #333;
            background-color: #F1F7F6;
            outline: none;
        }

        .custom-textbox:focus {
            border-color: #29B6A5;
            box-shadow: 0 0 0 3px rgba(41, 182, 165, 0.2);
        }
    </style>

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
        .img-gaga {
            display: inline-block; /* Hacemos que el contenedor sea inline-block para que solo ocupe el tamaño de la imagen */
            width: 50px; /* Ajusta el tamaño del círculo según tus necesidades */
            height: 50px;
            border-radius: 50%; /* Convertimos el cuadro en un círculo */
            background-size: cover; /* Ajustamos la imagen para que cubra el círculo */
            background-position: center center; /* Centramos la imagen dentro del círculo horizontal y verticalmente */
            background-repeat: no-repeat; /* Evitamos que la imagen se repita */
            margin: 0; /* Quitamos cualquier margen */
            padding: 0; /* Quitamos cualquier relleno */
            border: none; /* Quitamos cualquier borde */
        }


        .nav-link.dropdown-toggle {
            align-items: center;
            padding: 1px 5px; /* Ajustamos el padding horizontal y vertical */
        }
        .user-name {
            color: #333; /* Color del nombre de usuario */
            font-size: 14px; /* Tamaño del texto del nombre de usuario */
        }
    </style>
</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<%=request.getContextPath()%>/AdminServlet?action=lista">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">JAPYLD <sup>TM</sup></div>
        </a>

        <!-- Divider -->

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            MÓDULOS
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/AdminServlet?action=lista">
                <i class="fas fa-home"></i>
                <span>Inicio</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/AdminServlet?action=listasPaginaVideojuegos">
                <i class="fas fa-gamepad"></i>
                <span>Videojuegos</span>
            </a>

        </li>
        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/AdminServlet?action=listaPaginaOfertas">
                <i class="far fa-money-bill-alt"></i>
                <span>Ofertas</span></a>
        </li>

        <!-- Nav Item - Tables -->
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/JuegosReservadosServlet">
                <i class="fas fa-box-open"></i>
                <span>Reservas</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">
        <br>

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>

                <!-- Topbar Search -->

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto"> <!-- Eliminamos "mr-auto" y "ml-auto" -->
                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <!-- Nav Item - Alerts -->
                    <!-- Nav Item - Messages -->
                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small user-name"><%=personaSession.getNombre()%></span>
                            <% for (ImagenPerfilDto fotoperfil : listaFotoPerfil) { %>
                            <% if (personaSession.getIdPersona() == fotoperfil.getIdPersona()) { %>
                            <img class="img-gaga"
                                 src="<%=fotoperfil.getDireccion_archivo()%>"
                                 alt="Avatar">
                            <% } %>
                            <% } %>
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="<%=request.getContextPath()%>/PerfilAdmiServlet">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Ver Perfil
                            </a>

                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="<%=request.getContextPath()%>/LoginServlet?action=logout" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Salir
                            </a>
                        </div>
                    </li>
                </ul>
            </nav>
            <% if (session.getAttribute("info") != null) {
                //if (request.getParameter("msg") != null) {%>
            <div id="mensaje" class="alert alert-success" role="alert">
                <%=session.getAttribute("info")%>
            </div>
            <%
                    session.setAttribute("info", null);
                }
            %>
            <% if (session.getAttribute("err") != null) {
                //if (request.getParameter("msg") != null) {%>
            <div id="mensajeError" class="alert alert-danger" role="alert">
                <%=session.getAttribute("err")%>
            </div>
            <%
                    session.setAttribute("err", null);
                }
            %>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between p-a">
                    <h1 class="h3 mb-4 text-gray-700">Perfil</h1>
                </div>
                <!-- Información del perfil -->
                <div class="row">
                    <!-- Imagen de Perfil y boton de edición-->
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
                                <div class="d-flex justify-content-center">
                                    <a class="btn btn-info" href="<%=request.getContextPath()%>/EditarPerfilServlet">
                                        Editar foto <i class="fas fa-edit"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-header py-2">
                                <h5 class="mb-0 font-weight-bold text-primary">
                                    Información del usuario
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


            <!-- /.container-fluid -->

        </div>



        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright © Japyld Solutions 2023</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Cerrar sesión</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Seleccione "Confirmar" si desea salir de su cuenta</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                <a class="btn btn-primary" href="<%=request.getContextPath()%>/LoginServlet?action=logout">Confirmar</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="recursos/vendor/jquery/jquery.min.js"></script>
<script src="recursos/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="recursos/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="recursos/js/sb-admin-2.min.js"></script>

</body>

</html>

