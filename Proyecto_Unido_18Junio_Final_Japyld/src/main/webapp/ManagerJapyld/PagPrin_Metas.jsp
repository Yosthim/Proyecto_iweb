<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.Objetivos" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.JuegosManager" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.ManagerDao" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.EstadisticasDao" %><%--
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

    <style>
      .alert-container {
        display: flex;
        justify-content: center; /* Centra horizontalmente */
        align-items: center; /* Centra verticalmente */
        max-width: 600px; /* Ajusta el ancho máximo de la alerta */
        margin: 8px auto; /* Márgenes superior e inferior para separar de otros elementos */
      }
    </style>

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

          <% if (session.getAttribute("info") != null) { %>
          <div class="alert-container">
            <div class="alert alert-success d-flex align-items-center align-content-center" role="alert">
              <%= session.getAttribute("info") %>
            </div>
          </div>
          <% session.setAttribute("info", null); %>
          <% } %>

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

                <% List<String> mesesEnIngles = new ArrayList<>(Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));%>
                <% ManagerDao managerObjetivos = new ManagerDao();%>
                <% String metaLograda = null;%>
                <% String mesESP1 = null;%>

                <% for  (String mes : mesesEnIngles) {%>

                <% ArrayList<Objetivos> objetivosMes = managerObjetivos.ObjetivosPorMes(mes);%>
                <% EstadisticasDao estadisticasDao = new EstadisticasDao();%>
                <% if(!objetivosMes.isEmpty()) {%>
                <% if(mes.equals("January")) { mesESP1 = "E N E R O";%><%}%>
                <%if (mes.equals("February")) { mesESP1 = "F E B R E R O";%><%}%>
                <%if (mes.equals("March")) { mesESP1 = "M A R Z O";%><%}%>
                <%if (mes.equals("April")) { mesESP1 = "A B R I L";%><%}%>
                <%if (mes.equals("May")) { mesESP1 = "M A Y O";%><%}%>
                <%if (mes.equals("June")) { mesESP1 = "J U N I O";%><%}%>
                <%if (mes.equals("July")) { mesESP1 = "J U L I O";%><%}%>
                <%if (mes.equals("August")) { mesESP1 = "A G O S T O";%><%}%>
                <%if (mes.equals("September")) { mesESP1 = "S E P T I E M B RE";%><%}%>
                <%if (mes.equals("October")) { mesESP1 = "O C T U B R E";%><%}%>
                <%if (mes.equals("November")) { mesESP1 = "N O V I E M B R E";%><%}%>
                <%if (mes.equals("December")) { mesESP1 = "D I C I E M B R E";%><%}%>

                <p div class="alert alert-warning text-center"><b><%=mesESP1%></b></p>
                <div class="table-responsive">
                  <table class="table table-bordered" id="dataTable2" width="100%" cellspacing="0">
                    <thead>
                      <tr>
                        <th class="text-center">META</th>
                        <th class="text-center">VENTAS OBTENIDAS</th>
                        <th class="text-center">¿Se logró la meta?</th>
                        <th class="text-center">META</th>
                        <th class="text-center">COMPRAS REALIZADAS</th>
                        <th class="text-center">¿Se logró la meta?</th>
                        <th class="text-center">META</th>
                        <th class="text-center">INGRESOS OBTENIDOS</th>
                        <th class="text-center">¿Se logró la meta?</th>
                        <th class="text-center">META</th>
                        <th class="text-center">NUEVOS USUARIOS</th>
                        <th class="text-center">¿Se logró la meta?</th>
                      </tr>
                    </thead>
                    <tbody>
                      <% for (Objetivos obMes : objetivosMes) { %>
                      <tr>
                        <td class="text-center"><%=obMes.getVentasPorMesJuego() %></td>
                        <td class="text-center"><%=estadisticasDao.VentaPorMes(mes) %></td>
                        <% if(obMes.getVentasPorMesJuego() <= estadisticasDao.VentaPorMes(mes)) { %>
                              <td class="alert alert-success text-center">SI</td>
                        <%} else {%>
                              <td class="alert alert-danger text-center">NO</td>
                        <%}%>
                        <td class="text-center"><%=obMes.getGastosPorMesJuego() %></td>
                        <td class="text-center"><%=estadisticasDao.CompraPorMes(mes) %></td>
                        <% if(obMes.getGastosPorMesJuego() <= estadisticasDao.CompraPorMes(mes)) { %>
                        <td class="alert alert-danger text-center">NO</td>
                        <%} else {%>
                        <td class="alert alert-success text-center">SI</td>
                        <%}%>
                        <td class="text-center"><%=obMes.getVentasPorMesJuego() - obMes.getGastosPorMesJuego() %></td>
                        <td class="text-center"><%=estadisticasDao.VentaPorMes(mes) - estadisticasDao.CompraPorMes(mes)%></td>
                        <% if(obMes.getVentasPorMesJuego() - obMes.getGastosPorMesJuego() <= estadisticasDao.VentaPorMes(mes) - estadisticasDao.CompraPorMes(mes)) { %>
                        <td class="alert alert-success text-center">SI</td>
                        <%} else {%>
                        <td class="alert alert-danger text-center">NO</td>
                        <%}%>
                        <td class="text-center"><%=obMes.getUsuarioPorMes() %></td>
                        <td class="text-center"><%=estadisticasDao.UsuariosPorMes(mes) %></td>
                        <% if(obMes.getUsuarioPorMes() <= estadisticasDao.UsuariosPorMes(mes)) { %>
                        <td class="alert alert-success text-center">SI</td>
                        <%} else {%>
                        <td class="alert alert-danger text-center">NO</td>
                        <%}%>
                      </tr>
                      <% } %>
                    </tbody>
                  </table>
                </div>
                <% } %>
                <% } %>
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
