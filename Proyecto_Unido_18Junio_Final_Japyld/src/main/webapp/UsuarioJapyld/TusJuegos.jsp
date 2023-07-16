<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.JuegosCompradosReservados" %>
<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.Juegos" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session"/>
<jsp:useBean id="categoriaPreferida" type="java.lang.String" scope="request"/>
<jsp:useBean id="listaJuegos" type="java.util.ArrayList<com.example.proyecto_final_base_japyld.BeansGenerales.JuegosCompradosReservados>" scope="request"/>
<jsp:useBean id="juegosRecomendados" type="java.util.ArrayList<com.example.proyecto_final_base_japyld.BeansGenerales.Juegos>" scope="request"/>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Dashboard1</title>


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
    <jsp:include page="/Includes/UsuarioJapyld/SidebarGeneral.jsp">
        <jsp:param name="title" value="Pagina Principal"/>
    </jsp:include>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <jsp:include page="/Includes/UsuarioJapyld/navbarUsuarioDemasPaginas.jsp">
                <jsp:param name="title" value="Mas detalles"/>
            </jsp:include>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex justify-content-between p-a">
                    <h1 class="h3 mb-4 text-gray-700">Tus Juegos</h1>
                </div>
                <!-- Categoría más comprada -->
                <div class="row justify-content-center mb-4">
                    <div class="card border-bottom-primary shadow h-100 py-2">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col">
                                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                        <h6 class="mb-0 font-weight-bold">
                                            Categoría que más compraste
                                        </h6>
                                    </div>
                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                        <%=categoriaPreferida%>
                                    </div>
                                </div>
                                <div class="col-auto">
                                    <i class="fas fa-gamepad fa-2x text-gray-300"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <hr class="mb-4"/>
                <!-- Juegos comprados/Reservados y Recomendaciones-->
                <div class="row">
                    <div class="col-lg-8">
                                <h4 class="mb-0 font-weight-bold text-primary">
                                    Juegos Comprados y Reservados
                                </h4>
                        <div class="row mt-4 mb-3">
                            <%for (JuegosCompradosReservados juego : listaJuegos){
                                String type = null;
                                switch (juego.getEstadoCompraJuego()) {
                                    case "Comprado":
                                        type = "success";
                                        break;
                                    case "Reservado":
                                        type = "warning";
                                        break;
                                }
                            %>
                            <div class="col-xl-4 col-md-6 col-sm-10 mb-2">
                                <div class="card border-left-<%=type%> shadow h-100 py-2">
                                    <div class="card-header">
                                        <h5 class="mb-0 font-weight-bold text-<%=type%>">
                                            <%=juego.getEstadoCompraJuego()%>
                                        </h5>
                                    </div>
                                    <div class="card-body justify-content-center">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            <h6><%=juego.getJuego().getNombreJuegos()%></h6>
                                        </div>
                                        <img src="<%=request.getContextPath()%>/Image?act=juego&id=<%=juego.getJuego().getIdImagen()%>"
                                             class="img-fluid" alt="Imagen">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            <%String consola = null;%>
                                            <% switch (juego.getConsola().getIdConsola()) {
                                                    case "SWI":
                                                        consola = "Nintendo Switch";
                                                        break;
                                                    case "PS4":
                                                        consola = "Playstation 4";
                                                        break;
                                                    case "PS5":
                                                        consola = "Playstation 5";
                                                        break;
                                                    case "WIU":
                                                        consola = "WII U";
                                                        break;
                                                    case "XB3":
                                                        consola = "Xbox 360";
                                                        break;
                                                    case "XBO":
                                                        consola = "Xbox One";
                                                        break;
                                                }
                                            %>
                                            <label><%=consola%></label>
                                        </div>
                                        <hr>
                                        <!-- Formulario de puntuación -->
                                        <form method="POST" action="<%=request.getContextPath()%>/TusJuegos">
                                            <label>Califica tu juego:</label>
                                            <input type="hidden" name="idUsuario" value="<%=personaSession.getIdPersona()%>">
                                            <input type="number" name="rating" min="1" max="5" required>
                                            <input type="hidden" name="IdCompradoReservado" value="<%=juego.getIdJuegosCompradosReservados()%>">
                                            <button type="submit" class="btn btn-primary mt-2">Enviar</button>
                                        </form>
                                        <!-- Fin del formulario de puntuación -->
                                    </div>
                                </div>
                            </div>
                            <%}%>

                        </div>
                    </div>
                    <div class="col-lg-1"> </div>
                    <div class="col-lg-3">
                                <h4 class="mb-0 font-weight-bold text-primary">
                                    Recomendaciones
                                </h4>
                        <div class="row mt-4 justify-content-center">
                            <%for (Juegos juego : juegosRecomendados) {%>
                            <div class="col-xl-11 col-sm-10 mb-2">
                                <div class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-header">
                                        <h5 class="mb-0 font-weight-bold text-primary">
                                            <%=juego.getNombreJuegos()%>
                                        </h5>
                                    </div>
                                    <div class="card-body justify-content-center">
                                        <img src="<%=request.getContextPath()%>/Image?act=juego&id=<%=juego.getIdImagen()%>"
                                             class="img-fluid" alt="Imagen">
                                        <div class="mt-3">
                                            <a href="<%=request.getContextPath()%>/MasDetallesJuego?idjuego=<%=juego.getIdJuegos()%>"
                                               class="btn btn-success">Más detalles</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%}%>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2020</span>
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
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="<%=request.getContextPath()%>/LoginServlet?action=logout">Logout</a>
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
<script src="recursos/js/demo/chart-bar-demo.js"></script>

</body>

</html>
