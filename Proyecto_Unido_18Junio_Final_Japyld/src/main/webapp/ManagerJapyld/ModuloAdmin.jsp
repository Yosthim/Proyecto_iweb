<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.ModuloAdmin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<ModuloAdmin> listarAdminActivos = (ArrayList<ModuloAdmin>) request.getAttribute("listarAdminActivos"); %>
<% ArrayList<ModuloAdmin> listarAdminInactivo = (ArrayList<ModuloAdmin>) request.getAttribute("listarAdminInactivo"); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Lista de Administradores</title>

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

      <div class="container-fluid">
        <!-- Account page navigation-->
        <br>

        <div class="col-xl-12">
          <!-- Profile picture card-->
          <div class="card mb-4 mb-xl-0">

            <div class="card-header font-weight-bold text-primary">TABLA DE ADMINISTRADORES ACTIVOS</div>
            <div class="card-body text">

              <div class="col-12 col-xl-auto mb-3">
                <div class="d-flex justify-content-lg-end">
                  <a href="BuzonMensaje.html">
                    <button class="btn btn-info" type="button">Enviar Mensaje</button>
                  </a>
                </div>
              </div>
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                  <tr>
                    <th class="text-center">Administradores</th>
                    <th class="text-center">Juegos Por Entregar</th>
                    <th class="text-center">Juegos Comprados</th>
                    <th class="text-center">Dinero Gastado En<br>Juegos de Usuario</th>
                  </tr>
                  </thead>
                  <tbody>
                  <% for (ModuloAdmin um : listarAdminActivos) { %>
                  <tr <%= um.getId() == (Integer)request.getAttribute("idMax") ? "style='background-color: rgba(255, 215, 0, 0.5);'" : "" %>>
                  <td>
                      <div class="d-flex align-items-center">
                        <label> <img src="recursos/img/profile-1.png" alt="" style="width: 45px; height: 45px;"></label>
                        <div class="d-flex align-items-center">
                          <ul>
                            <a href="<%=request.getContextPath()%>/DetalleAdminServlet?n=<%=String.valueOf(um.getId())%>" style="color:rgb(115,115,115);"><%=um.getNombre() + " " + um.getApellido()%></a>
                          </ul>
                        </div>
                      </div>
                    </td>
                    <td class="text-center"><%=um.getJuegoPorEntregar() %></td>
                    <td class="text-center"><%=um.getJuegoComprados()%></td>
                    <td class="text-center"><%=um.getDineroGastoTotal() != null ? um.getDineroGastoTotal() : "0" %></td>
                  </tr>
                  <% } %>
                  </tbody>

                </table>
              </div>
              <br>
              <div class="col-12 col-xl-auto mb-3">
                <div class="d-flex justify-content-center">
                  <a href="<%= request.getContextPath()%>/ModuloAdminServlet?action=crear">
                    <button class="btn btn-primary" type="button">Agregar Administrador (+)</button>
                  </a>
                </div>
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

            <div class="card-header font-weight-bold text-primary">TABLA DE ADMINISTRADORES DESPEDIDOS</div>
            <div class="card-body text">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable2" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th class="text-center">Administradores</th>
                      <th class="text-center">Juegos Entregados</th>
                      <th class="text-center">Juegos Comprados</th>
                      <th class="text-center">Dinero Gastado En<br>Juegos de Usuario</th>
                    </tr>
                  </thead>
                  <tbody>
                    <% for (ModuloAdmin um : listarAdminInactivo) { %>
                    <tr>
                      <td>
                        <div class="d-flex align-items-center">
                          <label> <img src="recursos/img/profile-1.png" alt="" style="width: 45px; height: 45px;"></label>
                          <div class="d-flex align-items-center">
                            <ul>
                              <a href="<%=request.getContextPath()%>/DetalleAdminServlet?n=<%=String.valueOf(um.getId())%>" style="color:rgb(115,115,115);"><%=um.getNombre() + " " + um.getApellido()%></a>
                            </ul>
                          </div>
                        </div>
                      </td>
                      <td class="text-center"><%=um.getJuegoPorEntregar() %></td>
                      <td class="text-center"><%=um.getJuegoComprados()%></td>
                      <td class="text-center"><%=um.getDineroGastoTotal() != null ? um.getDineroGastoTotal() : "0" %></td>
                    </tr>
                    <% } %>
                  </tbody>
                </table>
              </div>
              <br>
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
