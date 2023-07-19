<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.Objetivos" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.JuegosManager" %>
<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.ProductosVendidos" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.ProductosVendidosDao" %><%--
  Created by IntelliJ IDEA.
  User: milene
  Date: 14/06/2023
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>

<%
  ArrayList<ProductosVendidos> listaMasVendidos = (ArrayList<ProductosVendidos>) request.getAttribute("listaMasVendidos");
%>
<%
  ArrayList<ProductosVendidos> listaMenosVendidos = (ArrayList<ProductosVendidos>) request.getAttribute("listaMenosVendidos");
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
          <div class="container-fluid" id="productos">
            <br>
            <h3 class="h4 mb-1 text-gray-800"><b>PRODUCTOS</b></h3>
            <br>
            <h4 class="h5 mb-1 text-gray-800">JUEGO MÁS VENDIDO DE CADA MES</h4>
            <br>
            <div class="row">
              <!-- Earnings (Monthly) Card Example -->
              <% List<String> mesesEnIngles = new ArrayList<>(Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));%>
              <% ProductosVendidosDao productosVendidosDao = new ProductosVendidosDao();%>
              <% for  (String meses : mesesEnIngles) {%>

              <% ArrayList<ProductosVendidos> productosVendidos = productosVendidosDao.listarJuegosMasVendidos(meses);%>
                <% if(!productosVendidos.isEmpty()) {%>
                  <% String mesESP = null;%>
                  <% for  (ProductosVendidos vendidosMes : productosVendidos) {%>
                    <% if(vendidosMes.getMes().equals("January")) { mesESP = "ENERO";%><%}%>
                    <%if (vendidosMes.getMes().equals("February")) { mesESP = "FEBRERO";%><%}%>
                    <%if (vendidosMes.getMes().equals("March")) { mesESP = "MARZO";%><%}%>
                    <%if (vendidosMes.getMes().equals("April")) { mesESP = "ABRIL";%><%}%>
                    <%if (vendidosMes.getMes().equals("May")) { mesESP = "MAYO";%><%}%>
                    <%if (vendidosMes.getMes().equals("June")) { mesESP = "JUNIO";%><%}%>
                    <%if (vendidosMes.getMes().equals("July")) { mesESP = "JULIO";%><%}%>
                    <%if (vendidosMes.getMes().equals("August")) { mesESP = "AGOSTO";%><%}%>
                    <%if (vendidosMes.getMes().equals("September")) { mesESP = "SEPTIEMBRE";%><%}%>
                    <%if (vendidosMes.getMes().equals("October")) { mesESP = "OCTUBRE";%><%}%>
                    <%if (vendidosMes.getMes().equals("November")) { mesESP = "NOVIEMBRE";%><%}%>
                    <%if (vendidosMes.getMes().equals("December")) { mesESP = "DICIEMBRE";%><%}%>
                  <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-left-primary shadow h-100 py-2">
                      <h5 class="text-center text-primary"><b><%=mesESP%></b></h5>
                      <hr class="sidebar-divider my-0">
                      <div class="card-body">
                        <div class="row no-gutters align-items-center">
                          <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                              <h6><%=vendidosMes.getNombreJuego()%></h6>
                            </div>
                            <img src="<%=request.getContextPath()%>/Image?act=juego&id=<%=vendidosMes.getId_imagen()%>" class="img-fluid img_juego" alt="Imagen">
                            <div class="mt-3">
                              <p class="mt-2">Precio: S/. <%=vendidosMes.getPrecio()%></p>
                              <p class="text-gray-500 text-right mb-1"><b> <%= "Ventas: " + vendidosMes.getVentas()%></b></p>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                 <% } %>
                <% } %>
              <% } %>
            </div>
            <br>
            <br>
            <br>
            <br>
            <h4 class="h5 mb-1 text-gray-800">JUEGO MENOS VENDIDO DE CADA MES </h4>
            <br>
            <div class="row">
              <!-- Earnings (Monthly) Card Example -->
              <% List<String> mesesEnIngles1 = new ArrayList<>(Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));%>
              <% ProductosVendidosDao productosVendidosDao1 = new ProductosVendidosDao();%>
              <% for  (String meses1 : mesesEnIngles1) {%>

              <% ArrayList<ProductosVendidos> productosVendidos1 = productosVendidosDao1.listarJuegosMenosVendidos(meses1);%>
              <% if(!productosVendidos1.isEmpty()) {%>
              <% String mesESP1 = null;%>
              <% for  (ProductosVendidos vendidosMesM : productosVendidos1) {%>
              <% if(vendidosMesM.getMes().equals("January")) { mesESP1 = "ENERO";%><%}%>
              <%if (vendidosMesM.getMes().equals("February")) { mesESP1 = "FEBRERO";%><%}%>
              <%if (vendidosMesM.getMes().equals("March")) { mesESP1 = "MARZO";%><%}%>
              <%if (vendidosMesM.getMes().equals("April")) { mesESP1 = "ABRIL";%><%}%>
              <%if (vendidosMesM.getMes().equals("May")) { mesESP1 = "MAYO";%><%}%>
              <%if (vendidosMesM.getMes().equals("June")) { mesESP1 = "JUNIO";%><%}%>
              <%if (vendidosMesM.getMes().equals("July")) { mesESP1 = "JULIO";%><%}%>
              <%if (vendidosMesM.getMes().equals("August")) { mesESP1 = "AGOSTO";%><%}%>
              <%if (vendidosMesM.getMes().equals("September")) { mesESP1 = "SEPTIEMBRE";%><%}%>
              <%if (vendidosMesM.getMes().equals("October")) { mesESP1 = "OCTUBRE";%><%}%>
              <%if (vendidosMesM.getMes().equals("November")) { mesESP1 = "NOVIEMBRE";%><%}%>
              <%if (vendidosMesM.getMes().equals("December")) { mesESP1 = "DICIEMBRE";%><%}%>
              <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-primary shadow h-100 py-2">
                  <h5 class="text-center text-primary"><b><%=mesESP1%></b></h5>
                  <hr class="sidebar-divider my-0">
                  <div class="card-body">
                    <div class="row no-gutters align-items-center">
                      <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                          <h6><%=vendidosMesM.getNombreJuego()%></h6>
                        </div>
                        <img src="<%=request.getContextPath()%>/Image?act=juego&id=<%=vendidosMesM.getId_imagen()%>" class="img-fluid img_juego" alt="Imagen">
                        <div class="mt-3">
                          <p class="mt-2">Precio: S/. <%=vendidosMesM.getPrecio()%></p>
                          <p class="text-gray-500 text-right mb-1"><b> <%= "Ventas: " + vendidosMesM.getVentas()%></b></p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <% } %>
              <% } %>
              <% } %>
            </div>
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
