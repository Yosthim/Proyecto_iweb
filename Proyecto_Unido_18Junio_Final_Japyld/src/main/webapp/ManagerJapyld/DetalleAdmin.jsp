<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.DetalleAdmin" %>
<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.ModuloAdmin" %>
<%ArrayList<DetalleAdmin> lista1 = (ArrayList<DetalleAdmin>) request.getAttribute("listaJuegosPropuestosComprados");%>
<%ArrayList<DetalleAdmin> lista2 = (ArrayList<DetalleAdmin>) request.getAttribute("listaJuegosPropuestosVendidos");%>
<jsp:useBean id="a" scope="request" type="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.DetalleAdmin"/>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Por Consola</title>

    <title>Gráfico de Barras con Bootstrap 4</title>

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

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<%=request.getContextPath()%>/ManagerServlet">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">JAPYLD <sup>TM</sup></div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">
        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item active">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
               aria-expanded="true" aria-controls="collapseTwo">
                <i class="fas fa-home"></i>
                <span>Panel Principal</span></a>
            </a>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Detalles de JAPYLD:</h6>
                    <a class="collapse-item" href="<%=request.getContextPath()%>/ManagerServlet?a=resumen">Resumen</a>
                    <a class="collapse-item" href="<%=request.getContextPath()%>/ManagerServlet?a=productos">Productos</a>
                    <a class="collapse-item" href="<%=request.getContextPath()%>/ManagerServlet?a=metas">Metas Mensuales</a>
                    <a class="collapse-item" href="<%=request.getContextPath()%>/ManagerServlet?a=estadisticas">Estadísticas</a>
                </div>
            </div>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            MÓDULOS
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
               aria-expanded="true" aria-controls="collapsePages">
                <i class="fas fa-gamepad"></i>
                <span>JUEGOS</span>
            </a>
            <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Estadísticas:</h6>
                    <a class="collapse-item" href="<%=request.getContextPath()%>/PorCategoriaJuegoServlet">Por categoría de Juego</a>
                    <a class="collapse-item" href="<%=request.getContextPath()%>/PorConsolaJuegoServlet">Por consola de Juego</a>
                    <h6 class="collapse-header">TOP 5:</h6>
                    <a class="collapse-item" href="<%=request.getContextPath()%>/Top5MasVendidosServlet">Más vendidos</a>
                    <a class="collapse-item" href="<%=request.getContextPath()%>/Top5MenosVendidosServlet">Menos vendidos</a>
                    <div class="collapse-divider"></div>
                    <h6 class="collapse-header">Más opciones:</h6>
                    <a class="collapse-item" href="<%=request.getContextPath()%>/EditarJuegosServlet?a=l">Editar Juegos</a>
                </div>
            </div>
        </li>

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/ModuloAdminServlet">
                <i class="fas fa-user-shield"></i>
                <span>ADMINISTRADOR</span></a>
        </li>

        <!-- Nav Item - Tables -->
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/ModuloUsuarioServlet">
                <i class="fas fa-user"></i>
                <span>USUARIO</span></a>
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


                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%=personaSession.getNombre() + " " + personaSession.getApellido()%></span>
                            <img class="img-profile rounded-circle"
                                 src="recursos/img/undraw_profile.svg">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="<%=request.getContextPath()%>/ManagerServlet?a=perfilManager">
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
            <div class="container-xl px-4 mt-4">
                <br>
                <div class="row">
                    <div class="col-xl-4">
                        <!-- Profile picture card-->
                        <div class="card mb-4 mb-xl-0">
                            <div class="card-body text-center">
                                <!-- Profile picture image-->
                                <img class="img-account-profile rounded-circle mb-2" src="recursos/img/profile-1.png" alt="">
                            </div>

                            <script3 src="https://code.jquery.com/jquery-3.6.0.min.js"></script3>
                            <script>
                                $(document).ready(function() {
                                    // Obtener el cuadro de texto editable y los botones
                                    var textoEditable3 = $('#texto-editable');
                                    var editarBtn3 = $('#editar-btn');
                                    var guardarBtn3 = $('#guardar-btn');

                                    // Al hacer clic en el botón "Editar"
                                    editarBtn3.click(function() {
                                        // Habilitar la edición del cuadro de texto
                                        textoEditable3.attr('contenteditable', 'true');
                                        textoEditable3.focus();

                                        // Ocultar el botón "Editar" y mostrar el botón "Guardar"
                                        editarBtn3.hide();
                                        guardarBtn3.show();
                                    });

                                    // Al hacer clic en el botón "Guardar"
                                    guardarBtn3.click(function() {
                                        // Deshabilitar la edición del cuadro de texto
                                        textoEditable3.attr('contenteditable', 'false');

                                        // Ocultar el botón "Guardar" y mostrar el botón "Editar"
                                        guardarBtn3.hide();
                                        editarBtn3.show();
                                    });
                                });
                            </script>
                            <BR>
                        </div>
                    </div>
                    <div class="col-xl-8">
                        <!-- Account details card-->
                        <div class="card mb-4">

                            <div class="card-header m-0 font-weight-bold text-warning <%= a.getId() == (Integer)request.getAttribute("idTrabajador") ? "trabajador-mes" : "" %>">
                                <% if (a.getId() == (Integer)request.getAttribute("idTrabajador")) { %>
                                TRABAJADOR DEL MES
                                <% } %>
                            </div>


                            <div class="container">
                                <br>
                                <h6 class="text-primary" style="color:#31a290;">NOMBRE DE PERFIL</h6>
                                <div class="d-flex">
                                    <div id="texto-editable" contenteditable="false" class="flex-grow-1 pr-3 custom-textbox">
                                        <h5><b> Nombre: </b> <%=a.getNombre() + " " + a.getApellido()%></h5>
                                        <h5><b> Email: </b> <%=a.getCorreo()%></h5>
                                        <h5><b> Fecha de nacimiento: </b><%=a.getFechaDeNacimiento()%></h5>
                                        <h5><b> DNI: </b> <%=a.getDni()%> </h5>
                                        <h5><b> Género </b> <%=a.getGenero()%> </h5>
                                        <h5><b> Categoría de juego preferido: </b> <%=a.getCategoriaJuegoPreferida()%> </h5>
                                        <h5><b> Fecha de Registro: </b> <%=a.getFechaRegistro()%> </h5>
                                        <h5><b> Estado </b> <%=a.getEstado()%> </h5>
                                    </div>
                                </div>
                            </div>

                            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                            <script>
                                $(document).ready(function() {
                                    // Obtener el cuadro de texto editable y los botones
                                    var textoEditable = $('#texto-editable');
                                    var editarBtn = $('#editar-btn');
                                    var guardarBtn = $('#guardar-btn');

                                    // Al hacer clic en el botón "Editar"
                                    editarBtn.click(function() {
                                        // Habilitar la edición del cuadro de texto
                                        textoEditable.attr('contenteditable', 'true');
                                        textoEditable.focus();

                                        // Ocultar el botón "Editar" y mostrar el botón "Guardar"
                                        editarBtn.hide();
                                        guardarBtn.show();
                                    });

                                    // Al hacer clic en el botón "Guardar"
                                    guardarBtn.click(function() {
                                        // Deshabilitar la edición del cuadro de texto
                                        textoEditable.attr('contenteditable', 'false');

                                        // Ocultar el botón "Guardar" y mostrar el botón "Editar"
                                        guardarBtn.hide();
                                        editarBtn.show();
                                    });
                                });
                            </script>
                            <br>
                            <script2 src="https://code.jquery.com/jquery-3.6.0.min.js"></script2>
                            <script>
                                $(document).ready(function() {
                                    // Obtener el cuadro de texto editable y los botones
                                    var textoEditable2 = $('#texto-editable');
                                    var editarBtn2 = $('#editar-btn');
                                    var guardarBtn2 = $('#guardar-btn');

                                    // Al hacer clic en el botón "Editar"
                                    editarBtn2.click(function() {
                                        // Habilitar la edición del cuadro de texto
                                        textoEditable2.attr('contenteditable', 'true');
                                        textoEditable2.focus();

                                        // Ocultar el botón "Editar" y mostrar el botón "Guardar"
                                        editarBtn2.hide();
                                        guardarBtn2.show();
                                    });

                                    // Al hacer clic en el botón "Guardar"
                                    guardarBtn2.click(function() {
                                        // Deshabilitar la edición del cuadro de texto
                                        textoEditable2.attr('contenteditable', 'false');

                                        // Ocultar el botón "Guardar" y mostrar el botón "Editar"
                                        guardarBtn2.hide();
                                        editarBtn2.show();
                                    });
                                });
                            </script>

                            <div class="card-body center-h center-h">
                                <div class="col-12 col-xl-auto mb-3">
                                    <div class="d-flex justify-content-end">
                                        <% if (a.getEstado().equals("Activo")) { %>
                                        <a href="<%= request.getContextPath()%>/DetalleAdminServlet?action=editar&n=<%=a.getId()%>" class="btn btn-danger">
                                            Despedir
                                        </a>
                                        <% } else { %>

                                        <% } %>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <section id="testimonials" class="testimonials">

                <div class="container-fluid">
                    <br>
                    <br>
                    <br>
                    <h1 class="h3 mb-2 text-gray-800 text-left">JUEGOS PROPUESTOS COMPRADOS</h1>
                    <br>

                    <div class="row">
                        <!-- Earnings (Monthly) Card Example -->
                        <% int i=1;
                            for(DetalleAdmin detalleAdmin:lista1) { %>
                        <div class="col-xl-6 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <h5 class="m-0 font-weight-bold text-primary"><%=i%>. "<%=detalleAdmin.getNombreJuegos()%>"</h5>
                                            <br>
                                            <div class="blog-entry d-flex blog-entry-search-item">
                                                <label> <img src="recursos/img/zorro.jpg" width="255" height="255" alt="Image" class="img-fluid"></label>
                                                <div class="d-flex align-items-center">
                                                    <ul>
                                                        <br>
                                                        <h3 class="m-0 font-weight-bold text-info">Precio Compra</h3>
                                                        <br>
                                                        <h5><%=detalleAdmin.getPrecioCompra()%></h5>
                                                        <br>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% i++;
                            } %>
                    </div>

                    <div class="swiper-pagination"></div>
                </div>
                <div class="container-fluid">
                    <br>
                    <br>
                    <br>
                    <h1 class="h3 mb-2 text-gray-800 text-left">JUEGOS PROPUESTOS VENDIDOS</h1>
                    <br>

                    <div class="row">
                        <!-- Earnings (Monthly) Card Example -->
                        <% int j=1;
                            for(DetalleAdmin detalleAdmin2:lista2) { %>
                        <div class="col-xl-6 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <h5 class="m-0 font-weight-bold text-primary"><%=j%>. "<%=detalleAdmin2.getNombreJuegos()%>"</h5>
                                            <br>
                                            <div class="blog-entry d-flex blog-entry-search-item">
                                                <label> <img src="recursos/img/zorro.jpg" width="255" height="255" alt="Image" class="img-fluid"></label>
                                                <div class="d-flex align-items-center">
                                                    <ul>

                                                        <br>
                                                        <h3 class="m-0 font-weight-bold text-info">Precio Venta</h3>
                                                        <br>
                                                        <h5><%=detalleAdmin2.getPrecioVenta()%></h5>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% j++;
                        } %>
                    </div>

                    <div class="swiper-pagination"></div>
                </div>
            </section>
        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <% for (ModuloAdmin b : (ArrayList<ModuloAdmin>) request.getAttribute("adminActivos")) { %>
                <% if (b.getId() == a.getId()) { %>
                <div style="background-color: #e1f0ff; width: 200px; height: 100px; margin: 0 auto;">
                    <p style="text-align: center; line-height: 100px; font-size: 18px;">GANANCIA: <%= b.getDineroTotal() != null ? b.getDineroTotal() : 0 %></p>
                </div>
                <% } %>
                <% } %>
                <div class="copyright text-center my-auto">
                    <br>
                    <br>
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
                    <h5 class="modal-title" id="exampleModalLabel">Cerrar sesión</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Seleccione "Confirmar" si desea salir de su cuenta.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/LoginServlet?action=logout">Salir</a>
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
