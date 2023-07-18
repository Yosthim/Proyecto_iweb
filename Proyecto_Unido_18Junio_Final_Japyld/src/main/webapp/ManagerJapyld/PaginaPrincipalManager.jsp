<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.Objetivos" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.JuegosManager" %>
<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.JuegosManagerDao" %>
<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.ManagerDao" %>
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
  ArrayList<JuegosManager> juegosRecienComentados = (ArrayList<JuegosManager>) request.getAttribute("juegosRecienComentados");
%>
<%
  ArrayList<JuegosManager> catalogoReciente = (ArrayList<JuegosManager>) request.getAttribute("catalogoReciente");
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
      .img_juego_grande {
        width: 275px;
        height: 340px;
      }
    </style>

    <style>
      .img_juego {
        width: 275px;
        height: 280px;
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

          <!-- Begin Page Content -->
          <div class="container-fluid" id="resumen">
            <br>
            <h3 class="h4 mb-1 text-gray-800"><b>RESUMEN</b></h3>
            <br>
            <h4 class="h5 mb-1 text-gray-800">ÚLTIMOS COMENTARIOS</h4>
            <br>
            <!-- Content Row -->
            <div class="row">
              <!-- First Column -->
              <% for (JuegosManager ultimosComentados : juegosRecienComentados) { %>
              <div class="col-xl-4 col-lg-4">
                <!-- Donut Chart -->
                <div class="card shadow mb-4 border-left-primary">
                  <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary"><%= ultimosComentados.getNombreJuegos() %></h6>
                  </div>
                  <div class="card-body text-center">
                    <img src="<%= ultimosComentados.getDireccion_imagen() %>" class="img-fluid img_juego_grande" alt="Imagen">
                  </div>
                </div>
              </div>
              <div class="col-xl-8 col-lg-8">
                <!-- Area Chart -->
                <div class="card shadow mb-4">
                  <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Comentarios</h6>
                  </div>
                  <div class="card-body">
                    <% ManagerDao comentarioJuego = new ManagerDao(); %>
                    <% ArrayList<JuegosManager> comentariosPorJuego = comentarioJuego.comentarioPorJuego(ultimosComentados.getIdJuegos()); %>
                    <% for (JuegosManager comentarios : comentariosPorJuego) { %>
                    <div class="card-body p-2">
                      <%Timestamp time = comentarios.getComentarios().getFecha_comentario1();%>
                      <%SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");%>
                      <%SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");%>
                      <%String fecha = sdf1.format(time);%>
                      <%String hora = sdf2.format(time);%>
                      <p class="text-info mb-1"><b> <%= "Por " + comentarios.getPersonas().getNombre() + " " + comentarios.getPersonas().getApellido() + " el " + fecha + " a las " + hora %></b></p>
                      <p class="p-2 bg-gray-200">➤ <%= comentarios.getComentarios().getComentario() %></p>
                    </div>
                    <% } %>
                  </div>
                </div>
              </div>
              <% } %>
            </div>

            <br>
            <br>

            <h4 class="h5 mb-1 text-gray-800">CATÁLOGO RECIENTE</h4>
            <br>
            <div class="row">

              <% for (JuegosManager nuevosIngresos : catalogoReciente) {%>
              <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-primary shadow h-100 py-2">
                  <div class="card-body">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                          <h6><%=nuevosIngresos.getNombreJuegos()%></h6>
                        </div>
                        <img src="<%=nuevosIngresos.getDireccion_imagen()%>" class="img-fluid img_juego" alt="Imagen">
                        <div class="mt-3">
                          <p class="mt-2">Precio: S/. <%=nuevosIngresos.getPrecio()%></p>
                          <p class="text-gray-500 text-right mb-1"><b> <%= "Admin: " + nuevosIngresos.getPersonas().getNombre() + " " + nuevosIngresos.getPersonas().getApellido()%></b></p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <% } %>

            </div>
          </div>

          <br>
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
