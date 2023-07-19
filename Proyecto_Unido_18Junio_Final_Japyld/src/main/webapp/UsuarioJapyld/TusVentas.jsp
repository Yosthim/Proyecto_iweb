<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.VentaJuegosGeneral" %>
<%@ page import="com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.ImagenPerfilDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="textoBusqueda" scope="request" type="java.lang.String" class="java.lang.String"/>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session"/>
<jsp:useBean id="listaOfertas" type="java.util.ArrayList<com.example.proyecto_final_base_japyld.BeansGenerales.VentaJuegosGeneral>" scope="request"/>
<% ArrayList<ImagenPerfilDto> listaFotoPerfil = (ArrayList<ImagenPerfilDto>) request.getAttribute("listaFotoPerfil"); %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tus Ventas</title>


    <!-- Custom fonts for this template-->
    <link href="recursos/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="recursos/css/sb-admin-2.min.css" rel="stylesheet">
    <style>
        .img_juego_grande {
            width: 275px;
            height: 340px;
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
    <jsp:include page="/Includes/UsuarioJapyld/SidebarGeneral.jsp">
        <jsp:param name="title" value="Pagina Principal"/>
    </jsp:include>
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
                            <a class="dropdown-item" href="<%=request.getContextPath()%>/PerfilUsuarioServlet">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Profile
                            </a>

                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="<%=request.getContextPath()%>/LoginServlet?action=logout" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Logout
                            </a>
                        </div>
                    </li>
                </ul>
            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-3 text-gray-700">Tus Ventas</h1>
                <!-- Mensajes de error o éxito -->
                <%if (session.getAttribute("exito") != null) {%>
                    <div id="exito" class="alert alert-success">
                        <%=session.getAttribute("exito")%>
                    </div>
                    <%
                    session.setAttribute("exito", null);
                    %>
                <%} else if (session.getAttribute("error") != null) {%>
                    <div id="error" class="alert alert-danger">
                        <%=session.getAttribute("error")%>
                    </div>
                    <%
                    session.setAttribute("error", null);
                    %>
                <%} %>
                <!-- Content row -->
                <div class="container-fluid">
                    <div class="card shadow mb-5">
                        <div class="card-body">
                            <div class="row mb-5 justify-content-center">
                                <div class="dropdown no-arrow mb-4">
                                    <button class="btn btn-primary dropdown-toggle" type="button"
                                            id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">
                                        Nueva Publicación (+)
                                    </button>
                                    <div class="dropdown-menu dropdown-menu-right animated--fade-in">
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/TusVentas?act=exist">
                                            Juego en Catálogo
                                        </a>
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/TusVentas?act=new">
                                            Juego nuevo
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th class="text-center">LISTA DE OFERTAS</th>
                                        <th class="text-center">ESTADO</th>
                                        <th class="text-center">ACCIONES</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <% for (VentaJuegosGeneral oferta: listaOfertas) {%>
                                        <%
                                            String background = "";
                                            switch (oferta.getEstadoVenta()) {
                                                case "Pendiente":
                                                    background = "bg-info";
                                                    break;
                                                case "Aceptado":
                                                    background = "bg-success";
                                                    break;
                                                case "No Aceptado":
                                                    background = "bg-warning";
                                                    break;
                                                case "Retirado":
                                                    background = "bg-secondary";
                                                    break;
                                                case "Rechazado":
                                                    background = "bg-danger";
                                                    break;
                                            }
                                        %>
                                        <%if (oferta.getVisibilidad().equals("Habilitado")) {%>
                                        <tr>
                                            <td>
                                                <div class="row align-items-center">
                                                    <% if (oferta.getDisponibilidad().equals("Nuevo")) {%>
                                                        <div class="h-100 col-4 col-sm-4 text-center">
                                                            <img src="<%=request.getContextPath()%>/Image?act=venta&id=<%=oferta.getIdVenta()%>"
                                                                     class="img-fluid mh-100" alt="Image">
                                                        </div>
                                                    <% } else {%>
                                                        <div class="h-100 col-4 col-sm-4 text-center">
                                                            <img src="<%=request.getContextPath()%>/Image?act=juego&id=<%=oferta.getJuego().getIdImagen()%>"
                                                                     alt="Image" class="img-fluid mh-100">
                                                        </div>
                                                    <% } %>
                                                    <div class="col-8 col-sm-8">
                                                        <div class="card h-100 py-2">
                                                            <div class="card-header text-center">
                                                                <h5 class="font-weight-bold text-primary mb-0">
                                                                    <%=oferta.getJuego().getNombreJuegos()%>
                                                                </h5>
                                                            </div>
                                                            <div class="card-body text-center">
                                                                <p class="font-weight-bold text-gray-900 py-2 mb-0">
                                                                    Categoría: <%=oferta.getJuego().getCategoria().getNombre()%>
                                                                </p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="align-middle">
                                                <div class="card <%=background%> text-white shadow">
                                                    <div class="card-body text-center">
                                                        <%=oferta.getEstadoVenta()%>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="align-middle">
                                                <%
                                                switch (oferta.getEstadoVenta()) {
                                                    case "Retirado":%>
                                                        <form method="POST" action="<%=request.getContextPath()%>/TusVentas?act=delete&id=<%=oferta.getIdVenta()%>">
                                                            <button type="submit" class="btn btn-outline-dark" id="retirado"
                                                                    onclick="return confirm('¿Está seguro que quiere eliminar la oferta de la lista?')">
                                                                Eliminar de la lista
                                                            </button>
                                                        </form>
                                                        <%break;
                                                    case "Aceptado":%>
                                                        <form method="POST" action="<%=request.getContextPath()%>/TusVentas?act=delete&id=<%=oferta.getIdVenta()%>">
                                                            <button type="submit" class="btn btn-outline-dark" id="aceptado"
                                                                    onclick="return confirm('¿Está seguro que quiere eliminar la oferta de la lista?')">
                                                                Eliminar de la lista
                                                            </button>
                                                        </form>
                                                        <%break;
                                                    case "Pendiente":%>
                                                        <div class="row justify-content-center">
                                                            <form method="POST" action="<%=request.getContextPath()%>/TusVentas?act=retire&id=<%=oferta.getIdVenta()%>">
                                                                <button type="submit" class="btn btn-outline-dark"
                                                                        onclick="return confirm('Un administrador podria revisar su oferta pronto\n' +
                                                                         '¿Está seguro que quiere retirar su oferta?')">
                                                                    Retirar oferta
                                                                </button>
                                                            </form>
                                                        </div>
                                                        <%break;
                                                    case "Rechazado":%>
                                                        <div class="row mb-3 px-2">
                                                            <div class="card shadow px-2">
                                                                <a href="#RazonRechazo" id="header" class="d-block card-header text-center py-3" data-toggle="collapse"
                                                                    role="button" aria-expanded="false" aria-controls="RazonRechazo">
                                                                    <h6 class="m-0 font-weight-bold text-gray-900">Razon del rechazo</h6>
                                                                </a>
                                                                <div class="collapse" id="RazonRechazo" aria-labelledby="header">
                                                                    <div class="card-body">
                                                                        <%=oferta.getRazonRechazo()%>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <form method="POST" action="<%=request.getContextPath()%>/TusVentas?act=delete&id=<%=oferta.getIdVenta()%>">
                                                                <button type="submit" class="btn btn-outline-dark" id="rechazado"
                                                                        onclick="return confirm('¿Está seguro que quiere eliminar la oferta de la lista?')">
                                                                    Eliminar de la lista
                                                                </button>
                                                            </form>
                                                        </div>
                                                        <%break;
                                                    case "No Aceptado":%>
                                                        <div class="row justify-content-center mb-3">
                                                            <a href="<%=request.getContextPath()%>/TusVentas?act=change&id=<%=oferta.getIdVenta()%>&p=<%=oferta.getPrecioAdmi()%>"
                                                               class="btn btn-outline-dark">
                                                                Cambiar oferta
                                                            </a>
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <form method="POST" action="<%=request.getContextPath()%>/TusVentas?act=retire&id=<%=oferta.getIdVenta()%>">
                                                                <button type="submit" class="btn btn-outline-dark"
                                                                        onclick="return confirm('Un administrador podria revisar su oferta pronto\n' +
                                                                         '¿Está seguro que quiere retirar su oferta?')">
                                                                    Retirar oferta
                                                                </button>
                                                            </form>
                                                        </div>
                                                        <%break;
                                                }
                                                %>
                                            </td>
                                        </tr>
                                        <%}%>
                                    <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Content Row -->
        <!-- /.container-fluid -->
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
                <h5 class="modal-title" id="exampleModalLabel">Cerrar Session</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Seleccione "Confirmar" si desera salir de su cuenta.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Confirmar</button>
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

<!-- Page level plugins -->
<script src="recursos/vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="recursos/js/demo/chart-area-demo.js"></script>
<script src="recursos/js/demo/chart-pie-demo.js"></script>

</body>

</html>
