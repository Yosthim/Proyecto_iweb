<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.ModuloAdmin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<ModuloAdmin> listarAdminActivos = (ArrayList<ModuloAdmin>) request.getAttribute("listarAdminActivos"); %>
<% ArrayList<ModuloAdmin> listarAdminInactivo = (ArrayList<ModuloAdmin>) request.getAttribute("listarAdminInactivo"); %>
<% ArrayList<ModuloAdmin> adminPerdidas = (ArrayList<ModuloAdmin>) request.getAttribute("perdidas"); %>
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
        <!-- Account page navigation-->
        <br>

        <div class="col-xl-12">
          <!-- Profile picture card-->
          <div class="card mb-4 mb-xl-0">

            <div class="card-header font-weight-bold text-primary">TABLA DE ADMINISTRADORES ACTIVOS</div>
            <div class="card-body text">

              <div class="col-12 col-xl-auto mb-3">
                <div class="d-flex justify-content-lg-end">
                  <a href="<%=request.getContextPath()%>/CorreoAdminServlet">
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
                    <% for (ModuloAdmin um : listarAdminActivos) {
                      boolean esPerdida = adminPerdidas.contains(um.getId());
                    %>
                    <tr <%= um.getId() == (Integer)request.getAttribute("idMax") ? "style='background-color: rgba(255, 215, 0, 0.5);'" : (esPerdida ? "style='background-color: rgba(255, 25, 25, 0.7);'" : "") %>>
                      <td>
                        <div class="d-flex align-items-center">

                          <label> <img src="<%=um.getImagen()%>" alt="" style="width: 45px; height: 45px;"></label>
                          <div class="d-flex align-items-center">
                            <ul>
                              <a href="<%=request.getContextPath()%>/DetalleAdminServlet?n=<%=String.valueOf(um.getId())%>" style="color:rgb(115,115,115);"><%=um.getNombre() + " " + um.getApellido()%></a>
                            </ul>
                          </div>
                        </div>
                      </td>
                      <td class="text-center"><%=um.getJuegoPorEntregar() %></td>
                      <td class="text-center"><%=um.getJuegoComprados()%></td>
                      <td class="text-center"><%=um.getDineroGastoTotal() != null ? "S/. " + um.getDineroGastoTotal() : "S/. 0" %></td>
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
                          <label> <img src="<%=um.getImagen()%>" alt="" style="width: 45px; height: 45px;"></label>
                          <div class="d-flex align-items-center">
                            <ul>
                              <a href="<%=request.getContextPath()%>/DetalleAdminServlet?n=<%=String.valueOf(um.getId())%>" style="color:rgb(115,115,115);"><%=um.getNombre() + " " + um.getApellido()%></a>
                            </ul>
                          </div>
                        </div>
                      </td>
                      <td class="text-center"><%=um.getJuegoPorEntregar() %></td>
                      <td class="text-center"><%=um.getJuegoComprados()%></td>
                      <td class="text-center"><%=um.getDineroGastoTotal() != null ? "S/. " + um.getDineroGastoTotal() : "S/. 0" %></td>
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
  <jsp:include page="/Includes/ManagerJapyld/Adicionales.jsp">
    <jsp:param name="title" value="Adicionales"/>
  </jsp:include>

</body>

</html>
