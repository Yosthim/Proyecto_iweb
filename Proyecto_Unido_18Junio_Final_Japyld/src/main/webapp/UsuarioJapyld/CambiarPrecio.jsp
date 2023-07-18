<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.VentaJuegosGeneral" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session"/>
<jsp:useBean id="precioAdmin" type="java.math.BigDecimal" scope="request"/>
<jsp:useBean id="idOferta" type="java.lang.Integer" scope="request"/>

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
            <div class="container-fluid ">

                <!-- Page Heading -->
                <h1 class="h3 mb-3 text-gray-700">Cambiar Precio</h1>

                <!-- Form Content -->
                <div class="container-fluid align-middle justify-content-center">
                    <div class="card shadow m-5">
                        <form method="POST" action="<%=request.getContextPath()%>/TusVentas?act=change&id=<%=idOferta%>">
                            <div class="card-header">
                                <h5 class="font-weight-bold text-gray-900">Edición de la oferta:</h5>
                            </div>
                            <div class="card-body">
                                El administrador no aceptó su oferta inicial porque considera que el precio
                                no es adecuado; por lo que, le sugiere que cambie el precio a: <strong>S/<%=precioAdmin%></strong>
                                <div class="form-group">
                                    <label for="precioNuevo" class="text-gray-900">Ingrese el nuevo precio:</label>
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">S/</span>
                                        </div>
                                        <input class="form-control" type="number" id="precioNuevo" name="precioNuevo" placeholder="0.00"/>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer justify-content-center">
                                <a href="<%=request.getContextPath()%>/TusVentas" class="btn btn-danger">Cancelar</a>
                                <button type="submit" class="btn btn-success"
                                        onclick="return confirm('¿Está seguro de modificar su oferta?')">
                                    Cambiar oferta
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Content Row -->
        <!-- /.container-fluid -->
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

</body>

</html>
