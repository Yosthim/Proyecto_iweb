<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.PorCategoriaJuego" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: milene
  Date: 14/06/2023
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>
<% ArrayList<PorCategoriaJuego> listaPorCategoriaJuego = (ArrayList<PorCategoriaJuego>) request.getAttribute("listaPorCategoriaJuego"); %>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Por Categoria de Juego</title>

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
                    <a class="nav-link collapsed" href="recursos/vendor/fontawesome-free/css/all.min.css" data-toggle="collapse" data-target="#collapseTwo"
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
                    <a class="nav-link collapsed" href="recursos/vendor/fontawesome-free/css/all.min.css" data-toggle="collapse" data-target="#collapsePages"
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
                        <!-- Account page navigation-->
                        <br>

                        <div class="row">
                            <div class="col-xl-6">
                                <!-- Profile picture card-->
                                <div class="card mb-4 mb-xl-0">

                                    <div class="card-header font-weight-bold text-primary">TABLA POR CATEGORÍA DE VIDEOJUEGOS</div>
                                    <div class="card-body text">

                                        <section class="intro">
                                            <div class="gradient-custom-1 h-100">
                                                <div class="mask d-flex align-items-center h-100">
                                                    <div class="container">
                                                        <div class="row justify-content-center">
                                                            <div class="col-12">
                                                                <div class="table-responsive bg-white">
                                                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                                                        <thead>
                                                                            <tr>
                                                                                <th class="text-center">CATEGORÍA</th>
                                                                                <th class="text-center">N° USUARIOS</th>

                                                                            </tr>
                                                                        </thead>

                                                                        <tbody>
                                                                            <% for (PorCategoriaJuego cv : listaPorCategoriaJuego) { %>
                                                                            <tr>
                                                                                <td class="text-center"><%=cv.getNombreCategoria() %></td>
                                                                                <td class="text-center"><%=cv.getNum_usuarios()%></td>
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
                                        </section>
                                    </div>
                                </div>
                                <br>
                                <br>
                            </div>


                            <div class="col-xl-6">
                                <!-- Account details card-->
                                <div class="card mb-5">
                                    <div class="card-header">
                                        <b class="mr-5">RESUMEN</b>
                                    </div>
                                    <br>
                                    <div class="card-body">

                                        <% String[] nombreCategoria = new String[listaPorCategoriaJuego.size()];%>
                                        <% int[] cantUsuarios = new int[listaPorCategoriaJuego.size()];%>

                                        <% for (int i = 0; i < listaPorCategoriaJuego.size(); i++) { %>
                                        <% nombreCategoria[i] = listaPorCategoriaJuego.get(i).getNombreCategoria(); %>
                                        <% cantUsuarios[i] = listaPorCategoriaJuego.get(i).getNum_usuarios(); %>
                                        <% } %>

                                        <p class="alert alert-success" role="alert"> 1. La mayoría de usuarios prefieren los juegos de <%= nombreCategoria[0] %></p>
                                        <p div class="alert alert-warning"> 2. Algunos usuarios prefieren los juegos de <%= nombreCategoria[1] + " y " + nombreCategoria[2] %></p>
                                        <p class="alert alert-info" role="alert"> 3. Otros usuarios prefieren los juevos de <%= nombreCategoria[3] %> </p>
                                        <p div class="alert alert-danger" role="alert"> 4. Pocos usuarios prefieren los juegos de <%= nombreCategoria[4] %></p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row justify-content-center">
                            <div class="col-xl-6">
                                <!-- Profile picture card-->
                                <div class="card mb-4 mb-xl-0">
                                    <div class="card-header m-0 font-weight-bold text-primary">GRÁFICO POR CATEGORÍA DE VIDEOJUEGOS</div>
                                    <br>
                                    <div class="container">
                                        <canvas id="barChart4"></canvas>
                                    </div>

                                    <script>
                                        var ctx = document.getElementById('barChart4').getContext('2d');
                                        var barChart4 = new Chart(ctx, {
                                            type: 'bar',
                                            data: {
                                                labels: ['<%= nombreCategoria[0] %>', '<%= nombreCategoria[1] %>', '<%= nombreCategoria[2] %>', '<%= nombreCategoria[3] %>', '<%= nombreCategoria[4] %>'],
                                                datasets: [{
                                                    labels: ' ',
                                                    data: [<%= cantUsuarios[0] %>, <%= cantUsuarios[1] %>, <%= cantUsuarios[2] %>, <%= cantUsuarios[3] %>, <%= cantUsuarios[4] %>],
                                                    backgroundColor: [
                                                        'rgba(76, 175, 80, 0.5)', // Verde
                                                        'rgba(33, 150, 243, 0.5)', // Azul
                                                        'rgba(255, 193, 7, 0.5)', // Amarillo
                                                        'rgba(156, 39, 176, 0.5)', // Morado
                                                        'rgba(244, 67, 54, 0.5)' // Rojo
                                                    ],
                                                    borderColor: [
                                                        'rgba(76, 175, 80, 1)', // Verde
                                                        'rgba(33, 150, 243, 1)', // Azul
                                                        'rgba(255, 193, 7, 1)', // Amarillo
                                                        'rgba(156, 39, 176, 1)', // Morado
                                                        'rgba(244, 67, 54, 1)' // Rojo
                                                    ],
                                                    borderWidth: 1
                                                }]
                                            },
                                            options: {
                                                scales: {
                                                    y: {
                                                        beginAtZero: true
                                                    }
                                                },
                                                plugins: {
                                                    legend: {
                                                        display: false // Oculta la leyenda
                                                    }
                                                }
                                            }
                                        });
                                    </script>

                                    <br>

                                </div>
                            </div>
                        </div>

                        <br>
                        <br>
                    </div>


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
        <a class="scroll-to-top rounded" href="recursos/vendor/fontawesome-free/css/all.min.css">
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
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <a class="btn btn-primary" href="login.html">Logout</a>
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
