<%--
  Created by IntelliJ IDEA.
  User: PABLO
  Date: 21/06/2023
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Perfil</title>

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
                <div class="d-sm-flex align-items-center justify-content-between p-a">
                    <h1 class="h3 mb-4 text-gray-800">Perfil</h1>
                </div>
                <!-- Información del perfil -->
                <div class="row">
                    <!-- Imagen de Perfil y boton de edición-->
                    <div class="col-lg-4">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <img src="<%=personaSession.getImagen().getDireccionArchivo()%>" class="img-fluid"/>
                                <hr/>
                                <div class="d-flex justify-content-center">
                                    <a class="btn btn-info" href="#">
                                        Editar foto <i class="fas fa-edit"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-header py-2">
                                <h5 class="mb-0 font-weight-bold text-primary">
                                    Información del usuario
                                </h5>
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0 text-gray-900">Nombres</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0 justify-content-center"><%=personaSession.getNombre()%></p>
                                    </div>
                                </div>
                                <hr/>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0 text-gray-900">Apellidos</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0 justify-content-center"><%=personaSession.getApellido()%></p>
                                    </div>
                                </div>
                                <hr/>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0 text-gray-900">Fecha de nacimiento</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0 justify-content-center"><%=personaSession.getFechaDeNacimiento()%></p>
                                    </div>
                                </div>
                                <hr/>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0 text-gray-900">Email</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0 justify-content-center"><%=personaSession.getCorreo()%></p>
                                    </div>
                                </div>
                                <hr class="mb-5"/>
                                <a href="<%=request.getContextPath()%>/LoginServlet?action=logout" class="btn btn-danger">Cerrar sesión</a>
                            </div>
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

</body>

</html>

