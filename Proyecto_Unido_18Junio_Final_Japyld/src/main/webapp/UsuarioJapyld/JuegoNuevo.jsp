<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.Categoria" %>
<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.Consola" %>
<%@ page import="com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.ImagenPerfilDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="textoBusqueda" scope="request" type="java.lang.String" class="java.lang.String"/>
<jsp:useBean id="categorias" type="java.util.ArrayList<com.example.proyecto_final_base_japyld.BeansGenerales.Categoria>" scope="request"/>
<jsp:useBean id="consolas" type="java.util.ArrayList<com.example.proyecto_final_base_japyld.BeansGenerales.Consola>" scope="request"/>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session"/>
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
        .img_juego_grande {
            width: 275px;
            height: 340px;
        }

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

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">Nueva oferta</h1>

                <!-- Content Row -->
                <div class="row justify-content-center">

                    <!-- Form card -->
                    <div class="card shadow mb-4 border-left-primary col-9">
                        <div class="card-header">
                            <h4 class="mb-0 font-weight-bold text-primary">
                                Juego Nuevo
                            </h4>
                        </div>
                        <div class="card-body justify-content-center">
                            <form method="POST" action="<%=request.getContextPath()%>/TusVentas?act=new" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label class="text-gray-900" for="nombre">Ingrese el nombre del Juego:</label>
                                    <input type="text" class="form-control" id="nombre" name="nombreJuego" placeholder="Nombre del Juego">
                                </div>
                                <div class="form-group">
                                    <label class="text-gray-900" for="desc">Ingrese la Descripción del Juego:</label>
                                    <textarea class="form-control" id="desc" name="descripcion" rows="5"></textarea>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label class="text-gray-900" for="categorias">Seleccione la Categoría:</label>
                                        <select class="form-control" id="categorias" name="idCategoria">
                                            <option selected>Categorias</option>
                                            <% for(Categoria categoria: categorias) { %>
                                                <option value="<%=categoria.getIdCategorias()%>">
                                                    <%=categoria.getNombre()%>
                                                </option>
                                            <% } %>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label class="text-gray-900" for="consolas">Seleccione la consola:</label>
                                        <select class="form-control" id="consolas" name="idConsola">
                                            <option selected>Consolas</option>
                                            <% for(Consola consola: consolas) { %>
                                                <option value="<%=consola.getIdConsola()%>">
                                                    <%=consola.getNombre()%>
                                                </option>
                                            <% } %>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-2">
                                        <label class="text-gray-900" for="precioInput">Precio:</label>
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">S/</span>
                                            </div>
                                            <input class="form-control" type="number" id="precioInput" name="precio" placeholder="0.00"/>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-2">
                                        <label class="text-gray-900" for="cantidad">Cantidad:</label>
                                        <input type="number" class="form-control" id="cantidad" name="stock" placeholder="0"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="text-gray-900" for="imageFile">Suba la imagen del Juego</label>
                                    <div class="text-center mt-2 mb-3">
                                        <img src="https://dummyimage.com/150x150/dee2e6/6c757d.jpg" class="img-thumbnail"
                                             id="imagePreview" height="150px" width="150px" alt="Imagen del juego"/>
                                    </div>
                                    <div class="mb-3">
                                        <input class="form-control-file" type="file" id="imageFile" name="imagenJuego"
                                               accept="image/png, image/jpeg" onchange="readURL(this);"/>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-center">
                                    <a class="btn btn-danger mx-2" href="<%=request.getContextPath()%>/TusVentas?idusuario=<%=personaSession.getIdPersona()%>">Cancelar</a>
                                    <button class="btn btn-success mx-2" type="submit">Publicar</button>
                                </div>
                            </form>
                        </div>
                    </div>

                    <!-- /Form card -->
                </div>

            </div>
            <!-- /.container-fluid -->

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
<script src ="recursos/vendor/fontawesome-free/css/all.min.css"></script>
<script src ="recursos/css/sb-admin-2.min.css"></script>

<!-- Script para ver un preview de la imagen -->
<script>
    function readURL(input) {
        if(input.files[0].size > 2097152){
            alert("El archivo es muy grande");
            input.value = "";
            $('#imagePreview')
                .attr('src', "https://dummyimage.com/200x200/dee2e6/6c757d.jpg")
                .width(100)
                .height(100);
        }
        else if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#imagePreview')
                    .attr('src', e.target.result)
                    .width(200)
                    .height(200);
            };

            reader.readAsDataURL(input.files[0]);
        }
    }
</script>

</body>

</html>
