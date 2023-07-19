<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.JuegosManager" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: milene
  Date: 14/06/2023
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<JuegosManager> listarTodosJuegos = (ArrayList<JuegosManager>) request.getAttribute("listarTodosJuegos"); %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Editar Juegos</title>

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

                    <!-- Begin Page Content -->

                    <% if (session.getAttribute("info") != null) { %>
                    <div class="alert-container">
                        <div class="alert alert-success d-flex align-items-center align-content-center" role="alert">
                            <%= session.getAttribute("info") %>
                        </div>
                    </div>
                    <% session.setAttribute("info", null); %>
                    <% } %>

                    <div class="container-fluid">
                        <br>
                        <h1 class="h3 mb-2 text-gray-800 text-center">ELIMINAR JUEGOS</h1>
                        <br>
                        <br>

                        <div class="row">

                            <!-- Earnings (Monthly) Card Example -->
                            <% for (JuegosManager juegosTodos : listarTodosJuegos) {%>

                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                    <h6><%=juegosTodos.getNombreJuegos()%></h6>
                                                </div>
                                                <img src="<%=request.getContextPath()%>/Image?act=juego&id=<%=juegosTodos.getId_imagen()%>" class="img-fluid img_juego" alt="Imagen">
                                                <div class="mt-3">
                                                    <% if(juegosTodos.getPrecio_nuevo() == 0){%>
                                                    <p class="mt-2">Precio: S/. <%=juegosTodos.getPrecio()%></p>
                                                    <%}else{%>
                                                    <p class="mt-2">Precio: S/. <%=juegosTodos.getPrecio_nuevo()%></p>
                                                    <%}%>
                                                    <div class="center justify-content-center">
                                                        <a onclick="return confirm('Estas seguro de borrar :( ?')" class="btn alert-danger"
                                                           href="<%=request.getContextPath()%>/EditarJuegosServlet?a=b&idjuego=<%=juegosTodos.getIdJuegos()%>">BORRAR</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <% } %>

                            <!-- Pending Requests Card Example -->
                        </div>
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
