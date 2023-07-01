<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.MasDetallesDto" %>
<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.Comentarios" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<Comentarios> listaComentarios = (ArrayList<Comentarios>) request.getAttribute("listaComentarios"); %>
<% ArrayList<MasDetallesDto> listaMasDetallesJuego = (ArrayList<MasDetallesDto>) request.getAttribute("listaMasDetallesJuego"); %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>

<html>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SB Admin 2 - Charts</title>

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
      <% for (MasDetallesDto juego :listaMasDetallesJuego){%>
      <!-- Begin Page Content -->
      <div class="container-fluid">

        <!-- Page Heading -->
        <h1 class="h3 mb-2 text-gray-800">Más detalles</h1>
        <!-- Content Row -->
        <div class="row">
          <div class="col-xl-4 col-lg-4">
            <!-- Donut Chart -->
            <div class="card shadow mb-4 border-left-primary">
              <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary"><%=juego.getNombreJuegos()%></h6>
              </div>
              <div class="card-body text-center">
                <img src="<%=juego.getDireccion_imagen()%>" class="img-fluid img_juego_grande" alt="Imagen">
                <div class="mt-3">
                  <h5 class="mb-0">Rating:</h5>
                  <div class="rating">
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                  </div>
                  <h5>Categoría: <%=juego.getCategoria()%> </h5>
                </div>
              </div>
            </div>
          </div>
          <div class="col-xl-8 col-lg-8">
            <!-- Area Chart -->
            <div class="card shadow mb-4">
              <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Información del Juego</h6>
              </div>
              <div class="card-body">

                <p class="text-justify"><%=juego.getDescripcion()%></p>
                <div class="d-flex align-items-center mb-3">
                  <h6 class="mr-3">Consola:</h6>
                  <div class="row">
                    <div class="col-sm-2">
                      <img src="recursos/img/kisspng-playstation-2-logo-playstation-logo-5b2a2f6c44a1e2.0330864515294913082811.png" class="img-fluid" alt="PlayStation">
                    </div>
                    <div class="col-sm-2">
                      <img src="recursos/img/kisspng-black-xbox-360-xbox-one-logo-xbox-5abe7f19c6db83.0322681315224338178145.png" class="img-fluid" alt="XBOX">
                    </div>
                    <div class="col-sm-2">
                      <img src="recursos/img/nintendo_logo.png" class="img-fluid" alt="Nintendo">
                    </div>
                  </div>
                </div>
                <hr>
                <div class="d-flex justify-content-between align-items-center mb-3">
                  <span>Stock: <%=juego.getStock()%></span>
                  <div class="d-flex">
                    <button class="btn">Precio: S/. <%=juego.getPrecio()%></button>
                    <a class="btn btn-success ml-2" href="<%=request.getContextPath()%>/PaginaCompra?idjuego=<%=juego.getIdJuegos()%>">Comprar</a>

                  </div>
                </div>
              </div>
            </div>
            <div class="card shadow mb-4">
              <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Comentarios de Usuarios</h6>
              </div>
              <div class="card-body">
                <% for (Comentarios comentario : listaComentarios){%>
                <div class="media mb-4">
                  <img class="mr-3 rounded-circle" src="" alt="Usuario 1">
                  <div class="media-body">
                    <h6 class="mt-0"><%=comentario.getPersonaComentario().getNombre()%></h6>
                    <p><%=comentario.getComentario()%></p>
                  </div>
                </div>
                <hr>
                <% } %>
                <!-- Agrega más comentarios aquí -->
              </div>
            </div>
          </div>
        </div>

      </div>
      <!-- /.container-fluid -->
      <% } %>
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
