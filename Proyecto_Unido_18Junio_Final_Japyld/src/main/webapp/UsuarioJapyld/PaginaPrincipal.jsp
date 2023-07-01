<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.PaginaPrincipalDto" %>
<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.Categoria" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<PaginaPrincipalDto> listaJuegos = (ArrayList<PaginaPrincipalDto>) request.getAttribute("listaJuegos"); %>
<% ArrayList<Categoria> categoriaLista = (ArrayList<Categoria>) request.getAttribute("categorias"); %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>
<jsp:useBean id="textoBusqueda" scope="request" type="java.lang.String" class="java.lang.String"/>


<html>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Página Principal</title>


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
  <jsp:include page="/Navbars/UsuarioJapyld/SidebarGeneral.jsp">
    <jsp:param name="title" value="Pagina Principal"/>
  </jsp:include>
  <!-- End of Sidebar -->

  <!-- Content Wrapper -->
  <div id="content-wrapper" class="d-flex flex-column">

    <!-- Main Content -->
    <div id="content">

      <!-- Topbar -->
      <jsp:include page="/Navbars/UsuarioJapyld/navbarUsuarioPagPrincipal.jsp">
        <jsp:param name="title" value="Pagina Principal"/>
      </jsp:include>
      <!-- End of Topbar -->

      <!-- Begin Page Content -->
      <div class="container-fluid">

        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
          <h1 class="h3 mb-0 text-gray-700">Juegos Disponibles</h1>
          <div class="dropdown d-inline-block">
            <button class="btn btn-sm btn-primary shadow-sm dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <i class="fas fa-search fa-sm text-white-50"></i> Seleccionar Categoría
            </button>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton" style="width: 100%">
              <a class="dropdown-item" href="<%=request.getContextPath()%>/Pagina-principal-oferta">Ofertas</a>
              <%for (Categoria categoria : categoriaLista){%>
              <a class="dropdown-item" href="<%=request.getContextPath()%>/Pagina-principal-categoria?idcategoria=<%=categoria.getIdCategorias()%>"><%=categoria.getNombre()%></a>
              <%}%>
              <a class="dropdown-item text-danger" href="<%=request.getContextPath()%>/PaginaPrincipal">Regresar</a>
            </div>
          </div>
        </div>

        <!-- Content Row -->
        <div class="row">

          <!-- Earnings (Monthly) Card Example -->
          <% for (PaginaPrincipalDto juegos : listaJuegos) {%>
          <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-primary shadow h-100 py-2">
              <div class="card-body">
                <div class="row no-gutters align-items-center">
                  <div class="col mr-2">
                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                      <h6><%=juegos.getNombreJuegos()%></h6>
                    </div>
                    <img src="<%=juegos.getDireccion_imagen()%>" class="img-fluid img_juego" alt="Imagen">
                    <div class="mt-3">
                      <% if(juegos.getPrecio_nuevo() == 0){%>
                      <p class="mt-2">Precio: S/. <%=juegos.getPrecio()%></p>
                      <%}else{%>
                      <p class="mt-2">Precio: S/. <%=juegos.getPrecio_nuevo()%></p>
                      <%}%>
                      <a class="btn btn-success" href="<%=request.getContextPath()%>/MasDetallesJuego?idjuego=<%=juegos.getIdJuegos()%>">Más Detalles</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <% } %>

          <!-- Pending Requests Card Example -->
        </div>

        <!-- Content Row -->

        <div class="row">
        </div>

        <!-- Content Row -->
        <div class="row">

          <!-- Content Column -->
          <div class="col-lg-6 mb-4">
          </div>

          <div class="col-lg-6 mb-4">
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
        <h5 class="modal-title" id="exampleModalLabel">Listo para irte?</h5>
        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
      </div>
      <div class="modal-body">Selecciona Logout si estás seguro de cerrar tu sesión.</div>
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

</body>

</html>