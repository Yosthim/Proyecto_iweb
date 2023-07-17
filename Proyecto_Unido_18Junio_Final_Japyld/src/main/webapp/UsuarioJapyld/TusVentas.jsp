<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.VentaJuegosGeneral" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session"/>
<jsp:useBean id="listaOfertas" type="java.util.ArrayList<com.example.proyecto_final_base_japyld.BeansGenerales.VentaJuegosGeneral>" scope="request"/>

<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Dashboard1</title>


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
    <jsp:include page="/Includes/UsuarioJapyld/SidebarGeneral.jsp">
        <jsp:param name="title" value="Pagina Principal"/>
    </jsp:include>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <jsp:include page="/Includes/UsuarioJapyld/navbarUsuarioDemasPaginas.jsp">
                <jsp:param name="title" value="Mas detalles"/>
            </jsp:include>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-3 text-gray-700">Tus Ventas</h1>
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
                                                        <button type="button" class="btn btn-outline-dark" data-toggle="modal" data-target="#confirmarEliminacion"
                                                                data-whatever="<%=oferta.getIdVenta()%>">
                                                            Eliminar de la lista
                                                        </button>
                                                        <%break;
                                                    case "Aceptado":%>
                                                        <button type="button" class="btn btn-outline-dark" data-toggle="modal" data-target="#confirmarEliminacion"
                                                                data-whatever="<%=oferta.getIdVenta()%>">
                                                            Eliminar de la lista
                                                        </button>
                                                        <%break;
                                                    case "Pendiente":%>
                                                        <div class="row justify-content-center">
                                                            <button type="button" class="btn btn-outline-dark" data-toggle="modal" data-target="#retirarOferta"
                                                                    data-whatever="<%=oferta.getIdVenta()%>">
                                                                Retirar oferta
                                                            </button>
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
                                                            <button type="button" class="btn btn-outline-dark" data-toggle="modal" data-target="#confirmarEliminacion"
                                                                    data-whatever="<%=oferta.getIdVenta()%>">
                                                                Eliminar de la lista
                                                            </button>
                                                        </div>
                                                        <%break;
                                                    case "No Aceptado":%>
                                                        <div class="row justify-content-center mb-3">
                                                            <button type="button" class="btn btn-outline-dark" data-toggle="modal" data-target="#NoAcceptModal"
                                                                    data-whatever1="<%=oferta.getIdVenta()%>" data-whatever2="<%=oferta.getPrecioAdmi()%>">
                                                                Cambiar oferta
                                                            </button>
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <button type="button" class="btn btn-outline-dark" data-toggle="modal" data-target="#retirarOferta"
                                                                    data-whatever="<%=oferta.getIdVenta()%>">
                                                                Retirar oferta
                                                            </button>
                                                        </div>
                                                        <%break;
                                                }
                                                %>
                                            </td>
                                        </tr>
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
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="<%=request.getContextPath()%>/LoginServlet?action=logout">Logout</a>
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
