<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.PorConsolaJuego" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: milene
  Date: 14/06/2023
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>
<% ArrayList<PorConsolaJuego> listaPorConsolaJuego = (ArrayList<PorConsolaJuego>) request.getAttribute("listaPorConsolaJuego"); %>

<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Por Consola de Juego</title>

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
                                        <th class="text-center">CONSOLA</th>
                                        <th class="text-center">N° USUARIOS</th>

                                      </tr>
                                    </thead>

                                    <tbody>
                                      <% for (PorConsolaJuego cv : listaPorConsolaJuego) { %>
                                      <tr>
                                        <td class="text-center"><%=cv.getTipoConsola() %></td>
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

                    <% String[] tipoConsola = new String[listaPorConsolaJuego.size()];%>
                    <% int[] cantUsuarios = new int[listaPorConsolaJuego.size()];%>

                    <% for (int i = 0; i < listaPorConsolaJuego.size(); i++) { %>
                    <% tipoConsola[i] = listaPorConsolaJuego.get(i).getTipoConsola(); %>
                    <% cantUsuarios[i] = listaPorConsolaJuego.get(i).getNum_usuarios(); %>
                    <% } %>

                    <p class="alert alert-success" role="alert"> 1. La mayoría de usuarios prefieren jugar con <%= tipoConsola[0] %></p>
                    <p div class="alert alert-warning"> 2. Algunos usuarios prefieren jugar con <%= tipoConsola[1] + " o " + tipoConsola[2] %></p>
                    <p class="alert alert-info" role="alert"> 3. Otros usuarios prefieren jugar con <%= tipoConsola[3] %> </p>
                    <p div class="alert alert-danger" role="alert"> 4. Pocos usuarios prefieren jugar con <%= tipoConsola[4] + " o " + tipoConsola[5] %></p>
                  </div>
                </div>
              </div>
            </div>

            <div class="row justify-content-center">
              <div class="col-xl-6">
                <!-- Profile picture card-->
                <div class="card mb-4 mb-xl-0">
                  <div class="card-header m-0 font-weight-bold text-primary">GRÁFICO POR CONSOLA DE VIDEOJUEGOS</div>
                  <br>
                  <div class="container">
                    <canvas id="barChart4"></canvas>
                  </div>

                  <script>
                    var ctx = document.getElementById('barChart4').getContext('2d');
                    var barChart4 = new Chart(ctx, {
                      type: 'bar',
                      data: {
                        labels: ['<%= tipoConsola[0] %>', '<%= tipoConsola[1] %>', '<%= tipoConsola[2] %>', '<%= tipoConsola[3] %>', '<%= tipoConsola[4] %>','<%= tipoConsola[5] %>'],
                        datasets: [{
                          labels: ' ',
                          data: [<%= cantUsuarios[0] %>, <%= cantUsuarios[1] %>, <%= cantUsuarios[2] %>, <%= cantUsuarios[3] %>, <%= cantUsuarios[4] %>, <%= cantUsuarios[5] %>],
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
    <jsp:include page="/Includes/ManagerJapyld/Adicionales.jsp">
      <jsp:param name="title" value="Adicionales"/>
    </jsp:include>

  </body>

</html>
