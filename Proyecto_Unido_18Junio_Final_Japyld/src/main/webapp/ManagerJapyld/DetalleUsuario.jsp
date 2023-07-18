<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.DetalleUsuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.DetalleUsuario" %>
<%ArrayList<DetalleUsuario> lista1 = (ArrayList<DetalleUsuario>) request.getAttribute("listaJuegosVendidos");%>
<%ArrayList<DetalleUsuario> lista2 = (ArrayList<DetalleUsuario>) request.getAttribute("listaJuegosComprados");%>
<jsp:useBean id="e" scope="request" type="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.DetalleUsuario"/>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Por Genero de Juego</title>

    <title>Gráfico de Barras con Bootstrap 4</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

    <style>
        /* Estilos personalizados */
        .custom-textbox {
            border: 2px solid #ffffff;
            border-radius: 10px;
            padding: 10px;
            font-size: 18px;
            color: #333;
            background-color: #F1F7F6;
            outline: none;
        }

        .custom-textbox:focus {
            border-color: #29B6A5;
            box-shadow: 0 0 0 3px rgba(41, 182, 165, 0.2);
        }
    </style>

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

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
            <div class="container-xl px-4 mt-4">
                <!-- Account page navigation-->
                <br>
                <div class="row">
                    <div class="col-xl-4">
                        <!-- Profile picture card-->
                        <div class="card mb-4 mb-xl-0">
                            <div class="card-body text-center">
                                <!-- Profile picture image-->
                                <img class="img-account-profile rounded-circle mb-2" src="<%=e.getFotoPerfil()%>" alt="">
                            </div>

                            <script3 src="https://code.jquery.com/jquery-3.6.0.min.js"></script3>
                            <script>
                                $(document).ready(function() {
                                    // Obtener el cuadro de texto editable y los botones
                                    var textoEditable3 = $('#texto-editable');
                                    var editarBtn3 = $('#editar-btn');
                                    var guardarBtn3 = $('#guardar-btn');

                                    // Al hacer clic en el botón "Editar"
                                    editarBtn3.click(function() {
                                        // Habilitar la edición del cuadro de texto
                                        textoEditable3.attr('contenteditable', 'true');
                                        textoEditable3.focus();

                                        // Ocultar el botón "Editar" y mostrar el botón "Guardar"
                                        editarBtn3.hide();
                                        guardarBtn3.show();
                                    });

                                    // Al hacer clic en el botón "Guardar"
                                    guardarBtn3.click(function() {
                                        // Deshabilitar la edición del cuadro de texto
                                        textoEditable3.attr('contenteditable', 'false');

                                        // Ocultar el botón "Guardar" y mostrar el botón "Editar"
                                        guardarBtn3.hide();
                                        editarBtn3.show();
                                    });
                                });
                            </script>
                            <BR>
                        </div>
                    </div>
                    <div class="col-xl-8">
                        <!-- Account details card-->
                        <div class="card mb-4">
                            <div class="card-header m-0 font-weight-bold text-primary">
                                <a href="<%=request.getContextPath()%>/DetalleUsuarioServlet?action=exportarCSV&nm=<%=e.getId()%>">
                                    <u>Descargar Perfil</u>
                                </a>
                            </div>
                            <div class="container">
                                <br>
                                <h6 class="text-primary" style="color:#31a290;">NOMBRE DE PERFIL</h6>
                                <div class="d-flex">
                                    <div id="texto-editable" contenteditable="false" class="flex-grow-1 pr-3 custom-textbox">
                                        <h5><b> Nombre: </b> <%=e.getNombre() + " " + e.getApellido()%></h5>
                                        <h5><b> Email: </b> <%=e.getCorreo()%></h5>
                                        <h5><b> Fecha de nacimiento: </b><%=e.getFechaDeNacimiento()%></h5>
                                        <h5><b> DNI: </b> <%=e.getDni()%> </h5>
                                        <h5><b> Género </b> <%=e.getGenero()%> </h5>
                                        <h5><b> Categoría de juego preferido: </b> <%=e.getCategoriaJuegoPreferida()%> </h5>
                                        <h5><b> Fecha de Registro: </b> <%=e.getFechaRegistro()%> </h5>
                                        <h5><b> Estado </b> <%=e.getEstado()%> </h5>
                                    </div>
                                </div>
                            </div>

                            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                            <script>
                                $(document).ready(function() {
                                    // Obtener el cuadro de texto editable y los botones
                                    var textoEditable = $('#texto-editable');
                                    var editarBtn = $('#editar-btn');
                                    var guardarBtn = $('#guardar-btn');

                                    // Al hacer clic en el botón "Editar"
                                    editarBtn.click(function() {
                                        // Habilitar la edición del cuadro de texto
                                        textoEditable.attr('contenteditable', 'true');
                                        textoEditable.focus();

                                        // Ocultar el botón "Editar" y mostrar el botón "Guardar"
                                        editarBtn.hide();
                                        guardarBtn.show();
                                    });

                                    // Al hacer clic en el botón "Guardar"
                                    guardarBtn.click(function() {
                                        // Deshabilitar la edición del cuadro de texto
                                        textoEditable.attr('contenteditable', 'false');

                                        // Ocultar el botón "Guardar" y mostrar el botón "Editar"
                                        guardarBtn.hide();
                                        editarBtn.show();
                                    });
                                });
                            </script>
                            <div class="card-body center-h center-h">
                                <div class="col-12 col-xl-auto mb-3">
                                    <div class="d-flex justify-content-end">
                                        <% if (e.getEstado().equals("Activo")) { %>
                                        <a href="<%= request.getContextPath()%>/DetalleUsuarioServlet?action=editar&nm=<%=e.getId()%>" class="btn btn-danger">
                                            Ban
                                        </a>
                                        <% } else { %>

                                        <% } %>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <br>
                <br>
            </div>
            <br>
            <section id="testimonials" class="testimonials">
                <div class="container-fluid">
                    <!-- Earnings (Monthly) Card Example -->
                    <br>
                    <br>
                    <div class="row">
                        <div class="col-xl-6 col-md-6 mb-4">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">LISTA DE JUEGOS VENDIDO</h6>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                            <tr>
                                                <th>NOMBRE DEL JUEGO</th>
                                                <th>DINERO GANADO</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <% for (DetalleUsuario juegosVendidos : lista1) { %>
                                            <tr>
                                                <td><%=juegosVendidos.getNombreJuegos() %></td>
                                                <td>S/. <%=juegosVendidos.getPrecio_admin()%></td>
                                            </tr>
                                            <% } %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-xl-6 col-md-6 mb-4">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">LISTA DE JUEGOS COMPRADOS</h6>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="dataTable1" width="100%" cellspacing="0">
                                            <thead>
                                            <tr>
                                                <th>NOMBRE DEL JUEGO</th>
                                                <th>DINERO GASTADO</th>
                                            </tr>
                                            </thead>
                                            <tbody>

                                            <% for (DetalleUsuario juegosComprados : lista2) { %>
                                            <tr>
                                                <td><%=juegosComprados.getNombreJuegos() %></td>
                                                <td>S/. <%=juegosComprados.getPrecio()%></td>
                                            </tr>
                                            <% } %>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-pagination"></div>
                    </div>
                </div>
            </section>


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

