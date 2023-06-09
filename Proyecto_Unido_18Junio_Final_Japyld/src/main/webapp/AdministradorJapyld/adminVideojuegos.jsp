
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.Juegos" %>
<%@ page import="com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DtoJ.JuegosPopulares" %>
<%@ page import="com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DtoJ.JuegosxCategoria" %><%--
  Created by IntelliJ IDEA.
  User: jossr
  Date: 5/06/2023
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>
<jsp:useBean id="textoBusqueda" scope="request" type="java.lang.String" class="java.lang.String"/>

<%
  ArrayList<JuegosPopulares> lista  =(ArrayList<JuegosPopulares>) request.getAttribute("lista");
%>
<%
  ArrayList<JuegosxCategoria> popCategoria  =(ArrayList<JuegosxCategoria>) request.getAttribute("popCategoria");
%>

<%
  ArrayList<Juegos> juegos  =(ArrayList<Juegos>) request.getAttribute("juegos");
%>

<html lang="en">

<head>
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

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Página Principal Manager</title>

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

  <script>
    $(document).ready(function(){
      $("#mensaje").delay(2000).hide(2000);
    });
  </script>

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
      <div class="container-xl px-4 mt-4">
        <!-- Account page navigation-->
        <hr class="mt-0 mb-4">
        <div class="row">
          <div class="col-xl-6">
            <!-- Profile picture card-->
            <div class="card mb-4 mb-xl-0">
              <div class="card-header"><b>JUEGOS MÁS POPULARES DE LOS ÚLTIMOS 30 DÍAS</b></div>
              <div class="card-body text">

                <section class="intro">
                  <div class="gradient-custom-1 h-100">
                    <div class="mask d-flex align-items-center h-100">
                      <div class="container">
                        <div class="row justify-content-center">
                          <div class="col-12">
                            <div class="table-responsive bg-white">
                              <table class="table mb-0">
                                <thead>
                                <tr>
                                  <th scope="col" style="color: #666666;">JUEGO</th>
                                  <th scope="col" class="text-center" style="color: #666666;">CANTIDAD DE VENTAS</th>
                                </tr>
                                </thead>
                                <tbody>
                                <% boolean mostrarBusqueda = !textoBusqueda.isEmpty(); %>
                                <% if (!mostrarBusqueda) { %>
                                <!-- Sección de juegos populares -->
                                <% for (JuegosPopulares j : lista) { %>
                                <tr>
                                  <td><%=j.getNombre()%></td>
                                  <td class="text-center"><%=j.getCantidadVentasJuegos()%></td>
                                </tr>
                                <% } %>
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
            <div class="card mb-4 mb-xl-0">
              <div class="card-header"><b>CATEGORÍAS DE JUEGOS MÁS POPULARES EN LOS ÚLTIMOS 30 DÍAS</b></div>
              <div class="card-body text">

                <section class="intro">
                  <div class="gradient-custom-1 h-100">
                    <div class="mask d-flex align-items-center h-100">
                      <div class="container">
                        <div class="row justify-content-center">
                          <div class="col-12">
                            <div class="table-responsive bg-white">
                              <table class="table mb-0">
                                <thead>
                                <tr>
                                  <th scope="col" style="color: #666666;">JUEGO</th>
                                  <th scope="col" class="text-center" style="color: #666666;">CANTIDAD DE VENTAS</th>
                                </tr>
                                </thead>
                                <tbody>
                                <% for (JuegosxCategoria jx :popCategoria) { %>
                                <tr>
                                  <td><%=jx.getNombreCategoria()%></td>
                                  <td class="text-center"><%=jx.getCantidadRepetida()%></td>
                                </tr>
                                <%}%>
                                </tbody>
                              </table>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <BR>

                </section>
              </div>

              <div class="container">

                <button class="btn btn-primary container" type="button" onclick="enviarFormulario()">Agregar Categoria</button>
                <BR>
                <form id="agregarCategoria" style="display: none;" action="<%=request.getContextPath()%>/AdminServlet?action=agregarCategoria" method="post">
                  <!-- Aquí van los campos del formulario -->
                  <div class="container">
                    <div>
                      <input class=" px-4 mt-4"  style="margin-right: 10px; margin-left: 20px;" type="text" name="nombre" placeholder="Nombre">
                    </div>
                    <div>
                      <button type="submit" class="btn btn-success px-4 mt-4"  style="margin-right: 10px; margin-left: 20px;" form="agregarCategoria" >Enviar</button>
                    </div>
                  </div>
                  <BR>
                </form>

                <script>
                  function enviarFormulario() {
                    var formulario = document.getElementById("agregarCategoria");
                    formulario.style.display = "block";
                  }
                </script>

              </div>
              <BR>
            </div>
          </div>
          <div class="col-xl-6">
            <!-- Account details card-->
            <div class="card mb-5">


              <!-- Sección de búsqueda -->
              <div class="card-header">
                <b class="mr-5">LISTA DE JUEGOS</b>

                <form method="post" action="<%=request.getContextPath()%>/AdminServlet?action=buscar">
                  <div class="input-group">
                    <input type="text" class="form-control bg-light border-0 small" placeholder="Buscar..." name="textoBuscar" value="<%=textoBusqueda%>"
                           aria-label="Search" aria-describedby="basic-addon2"/>
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="submit">
                        <i class="fas fa-search fa-sm"></i>
                      </button>
                      <a class="btn btn-danger" href="<%=request.getContextPath()%>/AdminServlet?action=buscar">
                        <i class="fas fa-window-close fa-sm"></i>
                      </a>
                    </div>
                  </div>
                </form>
              </div>


              <br>


              <section class="intro">
                <div class="gradient-custom-1 h-100">
                  <div class="mask d-flex align-items-center h-100">
                    <div class="container">
                      <div class="row justify-content-center">
                        <div class="col-12">
                          <div class="table-responsive bg-white">
                            <table class="table mb-0">
                              <thead>
                              <tr>
                                <th scope="col">NOMBRE</th>
                                <th scope="col">COSTO</th>
                                <th scope="col">CATEGORIA</th>
                                <th scope="col">STOCK</th>
                                <th scope="col">EDITAR</th>
                              </tr>
                              </thead>
                              <tbody>
                              <% for (Juegos j : juegos) {
                                boolean match = !mostrarBusqueda || j.getNombreJuegos().toLowerCase().contains(textoBusqueda.toLowerCase()); // Verificar si el juego coincide con el texto de búsqueda
                                if (!match) { // Si hay un texto de búsqueda y no hay coincidencia, pasar al siguiente juego
                                  continue;
                                }
                              %>
                              <tr <% if (j.getStock() == 0) { %>style="color: red;"<% } %>>
                                <td class="text-center"><%= j.getNombreJuegos() %></td>
                                <td class="text-center"><%= j.getPrecio() %></td>
                                <td class="text-center"><%= j.getCategoria().getNombre() %></td>
                                <td class="text-center"><%= j.getStock() %></td>
                                <td class="text-center">
                                  <a class="btn btn-primary <% if (j.getStock() == 0) { %>btn-danger<% } %>"
                                     href="<%= request.getContextPath() %>/AdminServlet?action=editar&id=<%= j.getIdJuegos() %>">
                                    Editar
                                  </a>
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
              </section>

              <br>

            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- End of Main Content -->

    <!-- Footer -->
    <br>
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
