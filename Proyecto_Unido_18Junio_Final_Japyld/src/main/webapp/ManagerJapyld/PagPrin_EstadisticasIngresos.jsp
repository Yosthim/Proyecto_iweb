<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.Objetivos" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.JuegosManager" %>
<%@ page import="java.util.List" %>
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
<% ArrayList<JuegosManager> listaPorConsolaJuego = (ArrayList<JuegosManager>) request.getAttribute("listaPorConsolaJuego"); %>
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

          <!-- Begin Page Content -->

          <div class="container-fluid" id="estadisticas">
            <br>
            <h3 class="h4 mb-1 text-gray-800"><b>ESTADÍSTICAS</b></h3>
            <h7 class="text-secondary">JAPYLD fue creada en marzo del 2023. A partir de esa fecha se presentan los registros.</h7>
            <br>
            <br>
            <div class="card">
              <div class="card-header border-bottom">
                <!-- Wizard navigation-->
                <div class="nav nav-pills nav-justified flex-column flex-xl-row nav-wizard" id="cardTab" role="tablist">
                  <!-- Wizard navigation item 1-->
                  <a class="nav-item nav-link" id="wizard1-tab" href="<%=request.getContextPath()%>/ManagerServlet?a=estadisticasVentas" data-bs-toggle="tab" role="tab" aria-controls="wizard1" aria-selected="false" tabindex="-1">
                    <div class="wizard-step-icon">1</div>
                    <div class="wizard-step-text">
                      <div class="wizard-step-text-name">VENTA DE JUEGOS</div>
                      <div class="wizard-step-text-details">Administrador a Usuario</div>
                    </div>
                  </a>
                  <!-- Wizard navigation item 2-->
                  <a class="nav-item nav-link" id="wizard2-tab" href="<%=request.getContextPath()%>/ManagerServlet?a=estadisticasCompras" data-bs-toggle="tab" role="tab" aria-controls="wizard2" aria-selected="false" tabindex="-1">
                    <div class="wizard-step-icon">2</div>
                    <div class="wizard-step-text">
                      <div class="wizard-step-text-name">COMPRA DE JUEGOS</div>
                      <div class="wizard-step-text-details">Usuario a Administrador</div>
                    </div>
                  </a>
                  <!-- Wizard navigation item 3-->
                  <a class="nav-item nav-link active" id="wizard3-tab" href="<%=request.getContextPath()%>/ManagerServlet?a=estadisticasIngresos" data-bs-toggle="tab" role="tab" aria-controls="wizard3" aria-selected="true">
                    <div class="wizard-step-icon">3</div>
                    <div class="wizard-step-text">
                      <div class="wizard-step-text-name">INGRESOS MENSUALES</div>

                    </div>
                  </a>
                  <!-- Wizard navigation item 4-->
                  <a class="nav-item nav-link" id="wizard4-tab" href="<%=request.getContextPath()%>/ManagerServlet?a=estadisticasUsuarios" data-bs-toggle="tab" role="tab" aria-controls="wizard4" aria-selected="false" tabindex="-1">
                    <div class="wizard-step-icon">4</div>
                    <div class="wizard-step-text">
                      <div class="wizard-step-text-name">USUARIOS REGISTRADOS</div>

                    </div>
                  </a>
                </div>
              </div>
              <div class="card-body">
                <div class="tab-content" id="cardTabContent">
                  <!-- Wizard tab pane item 3-->
                  <div class="tab-pane py-5 py-xl-10 fade active show" id="wizard3" role="tabpanel" aria-labelledby="wizard3-tab">
                    <div class="row justify-content-center">
                      <div class="col-xxl-6 col-xl-8">
                        <h5 class="text-primary text-center">INGRESOS NETOS DE LA PÁGINA</h5>
                        <div class="card-body text">
                          <br>
                          <div style="width: 100%; overflow-x: auto;">
                            <div class="container" style="width: 800px;">
                              <canvas id="barChart"></canvas>
                            </div>
                          </div>
                          <% EstadisticasDao estadisticasDao = new EstadisticasDao();%>
                          <script>
                            var ctx = document.getElementById('barChart').getContext('2d');
                            var barChart = new Chart(ctx, {
                              type: 'bar',
                              data: {
                                labels: ['Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Setiembre','Octubre', 'Noviembre', 'Diciembre'],
                                datasets: [{
                                  labels: ' Ventas por mes ',
                                  data: [<%=estadisticasDao.VentaPorMes("March") - estadisticasDao.CompraPorMes("March") %>,
                                          <%=estadisticasDao.VentaPorMes("April") - estadisticasDao.CompraPorMes("April") %>,
                                          <%=estadisticasDao.VentaPorMes("May") - estadisticasDao.CompraPorMes("May") %>,
                                          <%=estadisticasDao.VentaPorMes("June") - estadisticasDao.CompraPorMes("June") %>,
                                          <%=estadisticasDao.VentaPorMes("July") - estadisticasDao.CompraPorMes("July") %>,
                                          <%=estadisticasDao.VentaPorMes("August") - estadisticasDao.CompraPorMes("August") %>,
                                          <%=estadisticasDao.VentaPorMes("September") - estadisticasDao.CompraPorMes("September") %>,
                                          <%=estadisticasDao.VentaPorMes("October") - estadisticasDao.CompraPorMes("October") %>,
                                          <%=estadisticasDao.VentaPorMes("November") - estadisticasDao.CompraPorMes("November") %>,
                                          <%=estadisticasDao.VentaPorMes("December") - estadisticasDao.CompraPorMes("December") %>],

                                  backgroundColor: [
                                    'rgba(76, 175, 80, 0.5)', // Verde
                                    'rgba(33, 150, 243, 0.5)', // Azul
                                    'rgba(255, 193, 7, 0.5)', // Amarillo
                                    'rgba(156, 39, 176, 0.5)', // Morado
                                    'rgba(244, 67, 54, 0.5)', // Rojo
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
                                    'rgba(244, 67, 54, 1)' , // Rojo
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
                                    beginAtZero: true,
                                    title: {
                                      display: true,
                                      text: 'Compras'
                                    }
                                  },
                                  x: {
                                    title: {
                                      display: true,
                                      text: 'Meses del año'
                                    }
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
                  </div>
                </div>
              </div>
            </div>
          </div>


        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
          <div class="container my-auto">
            <div class="copyright text-center my-auto">
              <span>Copyright &copy; JAPYLD Solutions 2023</span>
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
