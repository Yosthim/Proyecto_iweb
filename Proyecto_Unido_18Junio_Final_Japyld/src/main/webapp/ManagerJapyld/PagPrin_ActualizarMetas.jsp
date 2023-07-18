<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.Objetivos" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.JuegosManager" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%--
  Created by IntelliJ IDEA.
  User: milene
  Date: 14/06/2023
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>
<html lang="en">

  <head>

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

          <br>

          <div class="container-fluid" id="metas">
            <br>
            <h3 class="h4 mb-1 text-gray-800"><b>METAS MENSUALES</b></h3>
            <br>
            <h2 class="h3 mb-1 text-gray-800">OBJETIVOS</h2>
            <br>




            <form method="POST" action="<%=request.getContextPath()%>/ManagerServlet?p=aMetas">
              <div class="row">
                <div class="col-lg-6 mb-4">
                  <div class="card bg-primary text-white shadow">
                    <div class="card-body">
                      PRIMER OBJETIVO: VENTAS
                      <br>
                      Cantidad de venta por mes estimada es <div class="mb-3">
                      <input type="text" class="form-control" name="ventas" id="ventas">
                      </div>
                    </div>
                  </div>
                </div>

                <div class="col-lg-6 mb-4">
                  <div class="card bg-secondary text-white shadow">
                    <div class="card-body">
                      SEGUNDO OBJETIVO: COMPRAS
                      <br>
                      Cantidad de compras por mes estimada es <div class="mb-3">
                      <input type="text" class="form-control" name="compras" id="compras">
                      </div>
                    </div>
                  </div>
                </div>

                <div class="col-lg-6 mb-4">
                  <div class="card bg-info text-white shadow">
                    <div class="card-body">
                      TERCER OBJETIVO: USUARIOS
                      <br>
                      Cantidad de nuevos usuarios por mes estimada es <div class="mb-3">
                      <input type="text" class="form-control" name="usuarios" id="usuarios">
                      <%
                        LocalDateTime fechaActual = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String fechaActualStr = fechaActual.format(formatter);
                      %>
                      <input type="hidden" class="form-control" name="fecha" value="<%= fechaActualStr %>">
                    </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="d-sm-flex align-items-center justify-content-center mb-3">
                <div class="text-lg-end">
                  <button type="submit" class="btn btn-danger">Actualizar</button>
                </div>
              </div>

            </form>
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
