<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.Objetivos" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.JuegosManager" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: milene
  Date: 14/06/2023
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>
<%
  ArrayList<Objetivos> objetivos = (ArrayList<Objetivos>) request.getAttribute("objetivos");
%>
<%
  ArrayList<Objetivos> objetivosPasados = (ArrayList<Objetivos>) request.getAttribute("objetivosPasados");
%>

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
            <br>
            <h2 class="h3 mb-1 text-gray-800">OBJETIVOS</h2>

            <%
              for(Objetivos objetivos1 : objetivos){
            %>

            <div class="d-sm-flex align-items-center justify-content-end mb-4">
              <div class="text-lg-end">
                <a class="btn btn-outline-dark" href="<%=request.getContextPath()%>/ManagerServlet?a=actualizarObjetivos">EDITAR OBJETIVOS </a>
              </div>
            </div>

            <div class="row">
              <div class="col-lg-6 mb-4">
                <div class="card bg-primary text-dark shadow">
                  <div class="card-body bg-light border border-red p-3 mb-3">
                   <b class="text-primary"> PRIMER OBJETIVO: VENTAS</b>
                    <br>
                    Cantidad de venta por mes estimada es <b> <%=objetivos1.getVentasPorMesJuego()%></b>
                  </div>
                </div>
              </div>

              <div class="col-lg-6 mb-4">
                <div class="card bg-success text-dark shadow">
                  <div class="card-body bg-light border border-red p-3 mb-3">
                    <b class="text-success"> SEGUNDO OBJETIVO: COMPRAS</b>
                    <br>
                    Cantidad de compras por mes estimada es <b><%=objetivos1.getGastosPorMesJuego()%></b>
                  </div>
                </div>

              </div>
              <div class="col-lg-6 mb-4">
                <div class="card bg-info text-dark shadow">
                  <div class="card-body bg-light border border-red p-3 mb-3">
                    <b class="text-info"> TERCER OBJETIVO: USUARIOS</b>
                    <br>
                    Cantidad de nuevos usuarios por mes estimada es  <b><%=objetivos1.getUsuarioPorMes()%></b>
                  </div>
                </div>
              </div>

              <div class="col-lg-6 mb-4">
                <div class="card bg-warning text-dark shadow">
                  <div class="card-body bg-light border border-red p-3 mb-3">
                    <b class="text-dark">  CUARTO OBJETIVO: INGRESO NETO</b>
                    <br>
                    Cantidad de ingreso neto por mes estimada es  <b><%=objetivos1.getVentasPorMesJuego() - objetivos1.getGastosPorMesJuego()%></b>
                  </div>
                </div>
              </div>
            </div>
            <%
              }
            %>
          </div>


          <br>

          <div class="col-xl-12">
            <!-- Profile picture card-->
            <div class="card mb-4 mb-xl-0">

              <div class="card-header font-weight-bold text-primary">RECORD DE METAS</div>
              <div class="card-body text">
                <div class="table-responsive">
                  <table class="table table-bordered" id="dataTable2" width="100%" cellspacing="0">
                    <thead>
                      <tr>
                        <th class="text-center">Ventas de Juegos por mes</th>
                        <th class="text-center">Gastos de Juegos por mes</th>
                        <th class="text-center">Usuarios registrados por mes</th>
                        <th class="text-center">Fecha</th>
                      </tr>
                    </thead>
                    <tbody>
                      <% for (Objetivos ob1 : objetivosPasados) { %>
                      <tr>
                        <td class="text-center"><%=ob1.getVentasPorMesJuego() %></td>
                        <td class="text-center"><%=ob1.getGastosPorMesJuego() %></td>
                        <td class="text-center"><%=ob1.getUsuarioPorMes() %></td>
                        <%Timestamp time = ob1.getFecha();%>
                        <%SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");%>
                        <%String formattedTimestamp = sdf.format(time);%>
                        <td class="text-center"><%=formattedTimestamp %></td>
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
