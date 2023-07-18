<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.JuegosCompradosReservados" %>
<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.Juegos" %>
<%@ page import="com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.ImagenPerfilDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session"/>
<jsp:useBean id="categoriaPreferida" type="java.lang.String" scope="request"/>
<jsp:useBean id="listaJuegos" type="java.util.ArrayList<com.example.proyecto_final_base_japyld.BeansGenerales.JuegosCompradosReservados>" scope="request"/>
<jsp:useBean id="juegosRecomendados" type="java.util.ArrayList<com.example.proyecto_final_base_japyld.BeansGenerales.Juegos>" scope="request"/>
<jsp:useBean id="textoBusqueda" scope="request" type="java.lang.String" class="java.lang.String"/>
<% ArrayList<ImagenPerfilDto> listaFotoPerfil = (ArrayList<ImagenPerfilDto>) request.getAttribute("listaFotoPerfil"); %>
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

    <style>
        .img-gaga {
            display: inline-block; /* Hacemos que el contenedor sea inline-block para que solo ocupe el tamaño de la imagen */
            width: 50px; /* Ajusta el tamaño del círculo según tus necesidades */
            height: 50px;
            border-radius: 50%; /* Convertimos el cuadro en un círculo */
            background-size: cover; /* Ajustamos la imagen para que cubra el círculo */
            background-position: center center; /* Centramos la imagen dentro del círculo horizontal y verticalmente */
            background-repeat: no-repeat; /* Evitamos que la imagen se repita */
            margin: 0; /* Quitamos cualquier margen */
            padding: 0; /* Quitamos cualquier relleno */
            border: none; /* Quitamos cualquier borde */
        }


        .nav-link.dropdown-toggle {
            align-items: center;
            padding: 1px 5px; /* Ajustamos el padding horizontal y vertical */
        }
        .user-name {
            color: #333; /* Color del nombre de usuario */
            font-size: 14px; /* Tamaño del texto del nombre de usuario */
        }

        /* Estilo para ocultar el mensaje de éxito por defecto */
        #CompraExitosa {
            display: none;
        }

        /* Estilo para mostrar el mensaje de éxito con una animación */
        .show-notification {
            animation: fadeOut 5s;
        }

        /* Animación de desvanecimiento */
        @keyframes fadeOut {
            0% {
                opacity: 1;
            }
            100% {
                opacity: 0;
            }
        }
    </style>



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
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>

                <!-- Topbar Search -->

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto"> <!-- Eliminamos "mr-auto" y "ml-auto" -->
                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <!-- Nav Item - Alerts -->
                    <!-- Nav Item - Messages -->
                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small user-name"><%=personaSession.getNombre()%></span>
                            <% for (ImagenPerfilDto fotoperfil : listaFotoPerfil) { %>
                            <% if (personaSession.getIdPersona() == fotoperfil.getIdPersona()) { %>
                            <img class="img-gaga"
                                 src="<%=fotoperfil.getDireccion_archivo()%>"
                                 alt="Avatar">
                            <% } %>
                            <% } %>
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="<%=request.getContextPath()%>/PerfilUsuarioServlet">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Profile
                            </a>

                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="<%=request.getContextPath()%>/LoginServlet?action=logout" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Logout
                            </a>
                        </div>
                    </li>
                </ul>
            </nav>
            <!-- End of Topbar -->
            <% if (session.getAttribute("exitoCompra") != null) { %>
            <div id="CompraExitosa" class="alert alert-success show-notification" role="alert">
                <%= session.getAttribute("exitoCompra") %>
            </div>
            <% session.removeAttribute("exitoCompra"); %>
            <% } %>
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
<script>
    // Función para mostrar el mensaje de éxito y ocultarlo después de 10 segundos
    function showNotification() {
        const notification = document.getElementById("CompraExitosa");
        notification.style.display = "block";

        setTimeout(function () {
            notification.style.display = "none";
        }, 5000);
    }

    // Llama a la función cuando se cargue la página
    window.onload = function () {
        showNotification();
    };
</script>
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
