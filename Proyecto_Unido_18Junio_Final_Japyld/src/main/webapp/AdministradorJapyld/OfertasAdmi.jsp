
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.VentaJuegosGeneral" %><%--
  Created by IntelliJ IDEA.
  User: jossr
  Date: 5/06/2023
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>
<html lang="en">
<%
  ArrayList<VentaJuegosGeneral> ventas = (ArrayList<VentaJuegosGeneral>) request.getAttribute("ventas");
%>
<%
  ArrayList<VentaJuegosGeneral> nuevosJuegos = (ArrayList<VentaJuegosGeneral>) request.getAttribute("nuevosJuegos");
%>
<%
  ArrayList<VentaJuegosGeneral> nuevosOfertas = (ArrayList<VentaJuegosGeneral>) request.getAttribute("nuevosOfertas");
%>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Ofertas</title>

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

          <!-- Nav Item - Messages -->

          <div class="topbar-divider d-none d-sm-block"></div>

          <!-- Nav Item - User Information -->
          <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%=personaSession.getNombre()%></span>
              <img class="img-profile rounded-circle"
                   src="recursos/img/undraw_profile.svg">
            </a>
            <!-- Dropdown - User Information -->
            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                 aria-labelledby="userDropdown">
              <a class="dropdown-item" href="<%=request.getContextPath()%>/PerfilAdmiServlet">
                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                Profile
              </a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
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

              <div class="card-header font-weight-bold text-primary">JUEGOS ACEPTADOS</div>
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
                                  <th class="text-center">Juego</th>
                                  <th class="text-center">Usuario</th>
                                  <th class="text-center">Stock</th>

                                </tr>
                                </thead>

                                <tbody>
                                <%
                                  for(VentaJuegosGeneral j :ventas){
                                %>
                                <tr>
                                  <th scope="row" style="color: #666666;"><%=j.getJuego().getNombreJuegos()%></th>
                                  <td><%=j.getUsuario().getNombre()%></td>
                                  <td><%=j.getJuego().getStock()%></td>
                                </tr>
                                <%
                                  }
                                %>
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
            <!-- Profile picture card-->
            <div class="card mb-4 mb-xl-0">

              <div class="card-header font-weight-bold text-primary">NUEVAS OFERTAS </div>
              <div class="card-body text">

                <section class="intro">
                  <div class="gradient-custom-1 h-100">
                    <div class="mask d-flex align-items-center h-100">
                      <div class="container">
                        <div class="row justify-content-center">
                          <div class="col-12">
                            <div class="table-responsive bg-white">
                              <table class="table table-bordered" id="dataTable2" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                  <th class="text-center">Juego</th>
                                  <th class="text-center">Usuario</th>
                                  <th class="text-center">Precio</th>

                                </tr>
                                </thead>

                                <tbody >
                                <%
                                  for(VentaJuegosGeneral j :nuevosOfertas){
                                %>
                                <tr>
                                  <td class="text-center"><a href="<%=request.getContextPath()%>/OfertasServlet?action=ofertas&id=<%=j.getIdVenta()%>"><%=j.getJuego().getNombreJuegos()%></a>.</td>

                                  <td class="text-center"><%=j.getUsuario().getNombre()%></td>
                                  <td class="text-center"><%=j.getPrecioUsuario()%></td>
                                </tr>
                                <%
                                  }
                                %>
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
        </div>
        <div class="col-xl-12">
          <!-- Profile picture card-->
          <div class="card mb-4 mb-xl-0">

            <div class="card-header font-weight-bold text-primary">JUEGOS NUEVOS</div>
            <div class="card-body text">

              <section class="intro">
                <div class="gradient-custom-1 h-100">
                  <div class="mask d-flex align-items-center h-100">
                    <div class="container">
                      <div class="row justify-content-center">
                        <div class="col-12">
                          <div class="table-responsive bg-white">
                            <table class="table table-bordered" id="dataTable1" width="100%" cellspacing="0">
                              <thead>
                              <tr>
                                <th class="text-center">Juego</th>
                                <th class="text-center">Usuario</th>
                                <th class="text-center">Agregar</th>

                              </tr>
                              </thead>

                              <tbody>
                              <%
                                for(VentaJuegosGeneral j :nuevosJuegos){
                              %>
                              <tr>
                                <td class="text-center"><%=j.getNombreNuevo()%></td>
                                <td class="text-center"><%=j.getUsuario().getNombre()%></td>
                                <th class="text-center">
                                  <a href="<%=request.getContextPath()%>/AgregarJuegoUsuarioServlet?action=agregarJuegoUsuario" class="btn btn-primary">Agregar</a>
                                </th>
                              </tr>
                              <%
                                }
                              %>
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
        <br>
        <br>
      </div>
    </div>
    <!-- End of Main Content -->

    <!-- Footer -->
    <footer class="sticky-footer bg-white">
      <div class="container my-auto">
        <div class="copyright text-center my-auto">
          <span>Japyld</span>
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

<!-- Page level plugins -->
<script src="recursos/vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="recursos/js/demo/chart-area-demo.js"></script>
<script src="recursos/js/demo/chart-pie-demo.js"></script>

</body>

</html>
