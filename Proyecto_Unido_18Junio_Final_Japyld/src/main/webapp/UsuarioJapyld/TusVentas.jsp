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
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center"
           href="<%=request.getContextPath()%>/AdminServlet?action=lista">
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
            <a class="nav-link" href="<%=request.getContextPath()%>/PaginaPrincipal">
                <i class="fas fa-home"></i>
                <span>Página Principal</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link"
               href="<%=request.getContextPath()%>/TusJuegos?idusuario=<%=personaSession.getIdPersona()%>">
                <i class="fas fa-gamepad"></i>
                <span>Juegos Comprados y Reservados</span>
            </a>

        </li>
        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link"
               href="<%=request.getContextPath()%>/VentaJuegos?idusuario=<%=personaSession.getIdPersona()%>">
                <i class="far fa-money-bill-alt"></i>
                <span>Venta de juegos</span></a>
        </li>

        <!-- Nav Item - Tables -->


        <hr class="sidebar-divider">

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
                <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                    <div class="input-group">
                        <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                               aria-label="Search" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="button">
                                <i class="fas fa-search fa-sm"></i>
                            </button>
                        </div>
                    </div>
                </form>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <li class="nav-item dropdown no-arrow d-sm-none">
                        <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-search fa-fw"></i>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                             aria-labelledby="searchDropdown">
                            <form class="form-inline mr-auto w-100 navbar-search">
                                <div class="input-group">
                                    <input type="text" class="form-control bg-light border-0 small"
                                           placeholder="Search for..." aria-label="Search"
                                           aria-describedby="basic-addon2">
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button">
                                            <i class="fas fa-search fa-sm"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </li>

                    <!-- Nav Item - Alerts -->
                    <li class="nav-item dropdown no-arrow mx-1">
                        <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-bell fa-fw"></i>
                            <!-- Counter - Alerts -->
                            <span class="badge badge-danger badge-counter">3+</span>
                        </a>
                        <!-- Dropdown - Alerts -->
                        <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="alertsDropdown">
                            <h6 class="dropdown-header">
                                Alerts Center
                            </h6>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-primary">
                                        <i class="fas fa-file-alt text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 12, 2019</div>
                                    <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-success">
                                        <i class="fas fa-donate text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 7, 2019</div>
                                    $290.29 has been deposited into your account!
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-warning">
                                        <i class="fas fa-exclamation-triangle text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 2, 2019</div>
                                    Spending Alert: We've noticed unusually high spending for your account.
                                </div>
                            </a>
                            <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                        </div>
                    </li>

                    <!-- Nav Item - Messages -->
                    <li class="nav-item dropdown no-arrow mx-1">
                        <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-envelope fa-fw"></i>
                            <!-- Counter - Messages -->
                            <span class="badge badge-danger badge-counter">7</span>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="messagesDropdown">
                            <h6 class="dropdown-header">
                                Message Center
                            </h6>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle" src=""
                                         alt="...">
                                    <div class="status-indicator bg-success"></div>
                                </div>
                                <div class="font-weight-bold">
                                    <div class="text-truncate">Hi there! I am wondering if you can help me with a
                                        problem I've been having.
                                    </div>
                                    <div class="small text-gray-500">Emily Fowler · 58m</div>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle" src=""
                                         alt="...">
                                    <div class="status-indicator"></div>
                                </div>
                                <div>
                                    <div class="text-truncate">I have the photos that you ordered last month, how
                                        would you like them sent to you?
                                    </div>
                                    <div class="small text-gray-500">Jae Chun · 1d</div>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle" src=""
                                         alt="...">
                                    <div class="status-indicator bg-warning"></div>
                                </div>
                                <div>
                                    <div class="text-truncate">Last month's report looks great, I am very happy with
                                        the progress so far, keep up the good work!
                                    </div>
                                    <div class="small text-gray-500">Morgan Alvarez · 2d</div>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60"
                                         alt="...">
                                    <div class="status-indicator bg-success"></div>
                                </div>
                                <div>
                                    <div class="text-truncate">Am I a good boy? The reason I ask is because someone
                                        told me that people say this to all dogs, even if they aren't good...
                                    </div>
                                    <div class="small text-gray-500">Chicken the Dog · 2w</div>
                                </div>
                            </a>
                            <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                        </div>
                    </li>

                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">Bienvenid@ <%=personaSession.getNombre()%></span>
                            <img class="img-profile rounded-circle"
                                 src="recursos/img/profile-1.png">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="<%=request.getContextPath()%>/PerfilAdmiServlet">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Profile
                            </a>

                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="<%=request.getContextPath()%>/LoginServlet?action=logout"
                               data-toggle="modal" data-target="#logoutModal">
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

<!-- No Aceptado Modal -->
<div class="modal fade" id="NoAcceptModal" tabindex="-1" role="dialog" aria-labelledby="cambiarOferta"
    aria-hidden="true">
    <div class="modal-dialog" role="form">
        <div class="modal-content">
            <form method="POST">
                <div class="modal-header">
                    <h5 class="modal-title" id="cambiarOferta">¿Desea cambiar su oferta?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">
                    El administrador le sugiere cambiar el precio de su oferta a S/<input id="precioAdmin" readonly style="width: fit-content">.
                    <div class="form-group">
                        <label for="precio" class="text-gray-900">Ingrese el nuevo precio:</label>
                        <input class="form-control" type="number" id="precio" name="precioNuevo">
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-danger" type="button" data-dismiss="modal">Cancelar</button>
                    <button class="btn btn-primary" type="submit">Cambiar oferta</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Modal para eliminar de la lista -->
<div class="modal fade" id="confirmarEliminacion" tabindex="-1" role="dialog" aria-labelledby="confirmar" aria-hidden="true">
    <div class="modal-dialog" role="form">
        <div class="modal-content">
            <form method="POST">
                <div class="modal-header">
                    <h5 class="modal-title" id="confirmar">Eliminar de la lista:</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">
                    La oferta se eliminará de la lista permanentemente.<br/>
                    ¿Está seguro que quiere eliminarla?
                </div>
                <div class="modal-footer">
                    <button class="btn btn-danger" type="button" data-dismiss="modal">Cancelar</button>
                    <button class="btn btn-primary" type="submit">Eliminar oferta</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Modal para retirar una oferta -->
<div class="modal fade" id="retirarOferta" tabindex="-1" role="dialog" aria-labelledby="retirar" aria-hidden="true">
    <div class="modal-dialog" role="form">
        <div class="modal-content">
            <form method="POST">
                <div class="modal-header">
                    <h5 class="modal-title" id="retirar">Retirar oferta:</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">
                    Puede que un administrador revise su oferta pronto.<br/>
                    ¿Está seguro que quiere retirarla?
                </div>
                <div class="modal-footer">
                    <button class="btn btn-danger" type="button" data-dismiss="modal">Cancelar</button>
                    <button class="btn btn-primary" type="submit">Retirar oferta</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Código para modificar los modales dinámicamente -->
<script>
    //Lógica para el cambio de oferta
    <%--var changeOfferModal = document.getElementById('NoAcceptModal');--%>
    <%--changeOfferModal.addEventListener('show.bs.modal', function (event) {--%>
    <%--    //Obtenemos el botón relacionado que muestra el modal--%>
    <%--    let button = event.relatedTarget;--%>
    <%--    //Obtenemos los valores requeridos--%>
    <%--    let idOferta = button.getAttribute('data-whatever1');--%>
    <%--    console.log('hola');--%>
    <%--    console.log(idOferta);--%>
    <%--    var precioNuevo = button.getAttribute('data-whatever2');--%>
    <%--    console.log(precioNuevo);--%>
    <%--    //Ubicamos el input donde se mostrará el precio nuevo--%>
    <%--    var input = changeOfferModal.querySelector('#precioAdmin');--%>
    <%--    input.value = precioNuevo;--%>
    <%--    input.value = 12;--%>
    <%--    //Ubicamos la parte del form del modal--%>
    <%--    let formModal = changeOfferModal.querySelector('form');--%>
    <%--    //Le indicamos la acción que hará y el parámetro que enviara--%>
    <%--    formModal.action = "<%=request.getContextPath()%>/TusVentas?act=change&id=" + idOferta;--%>
    <%--})--%>

    $('#NoAcceptModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var idOferta = button.data('whatever1');
        var precioAdmin = button.data('whatever2');
        var modal = $(this);
        modal.find('.modal-body input').val(precioAdmin);
        modal.find('.modal-content form').action = "<%=request.getContextPath()%>/TusVentas?act=change&id=" + idOferta;

    })

    //Lógica para eliminar una oferta de la lista
    <%--var deleteOfferModal = document.getElementById('confirmarEliminacion');--%>
    <%--deleteOfferModal.addEventListener('show.bs.modal', function (event) {--%>
    <%--    //Botón relacionado al modal--%>
    <%--    let button = event.relatedTarget;--%>
    <%--    //Obtenemos el valor del id de la oferta--%>
    <%--    let idOferta = button.getAttribute('data-whatever');--%>
    <%--    //Ubicamos la parte del form--%>
    <%--    let formModal = deleteOfferModal.querySelector('form');--%>
    <%--    //Le indicamos la acción a realizar--%>
    <%--    formModal.action = "<%=request.getContextPath()%>/TusVentas?act=delete&id=" + idOferta;--%>
    <%--    console.log(formModal.action)--%>
    <%--})--%>
    $('#confirmarEliminacion').on('show.bs.modal', function (event) {
        var boton = $(event.relatedTarget);
        var idOferta = boton.data('whatever');
        var modal = $(this);
        modal.find('.modal-content form').action = "../TusVentas?act=new"
    })
    //Lógica para retirar una oferta
    var retireOfferModal = document.getElementById('retirarOferta');
    retireOfferModal.addEventListener('show.bs.modal', function (event) {
        //Botón relacionado al modal
        let button = event.relatedTarget;
        //Obtenemos el valor del id de la oferta
        let idOferta = button.getAttribute('data-whatever');
        //Ubicamos la parte del form
        let formModal = deleteOfferModal.querySelector('form');
        //Le indicamos la acción a realizar
        formModal.action = "<%=request.getContextPath()%>/TusVentas?act=retire&id=" + idOferta;
    })

</script>
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
