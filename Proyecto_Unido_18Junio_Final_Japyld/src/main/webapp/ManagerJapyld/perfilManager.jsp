<%--
  Created by IntelliJ IDEA.
  User: milene
  Date: 22/06/2023
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        <br>
                        <div class="row">
                            <div class="col-xl-4">
                                <!-- Profile picture card-->
                                <div class="card mb-4 mb-xl-0">
                                    <div class="card-body text-center">
                                        <!-- Profile picture image-->
                                        <img class="img-account-profile rounded-circle mb-2" src="recursos/img/profile-1.png" alt="">
                                    </div>

                                    <script3 src="https://code.jquery.com/jquery-3.6.0.min.js"></script3>

                                    <BR>
                                    <br>
                                    <div class="col-12 col-xl-auto mb-3">
                                        <div class="d-flex justify-content-center">
                                            <a href="<%=request.getContextPath()%>/ManagerServlet?a=perfilManager">
                                                <button class="btn btn-primary" type="button"> Editar foto de perfil</button>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-8">
                                <!-- Account details card-->
                                <div class="card mb-5">
                                    <div class="card-header">
                                        <b class="mr-5">DATOS</b>
                                    </div>
                                    <br>
                                    <div class="card-body">
                                        <label class="small mb-1" >Nombre y Apellido </label>
                                        <p class="p-3 bg-gray-200"><%=personaSession.getNombre() + " " + personaSession.getApellido()%></p>
                                        <label class="small mb-1" >Fecha de Nacimiento </label>
                                        <p class="p-3 bg-gray-300"><%=personaSession.getFechaDeNacimiento()%></p>
                                        <label class="small mb-1" >DNI </label>
                                        <p class="p-3 bg-gray-400"><%=personaSession.getDni()%></p>
                                        <label class="small mb-1" >Correo electrónico </label>
                                        <p class="p-3 bg-gray-400"><%=personaSession.getCorreo()%></p>

                                    </div>
                                </div>
                                <br>
                                <div class="col-12 col-xl-auto mb-3">
                                    <div class="d-flex justify-content-center">
                                        <a href="<%=request.getContextPath()%>/LoginServlet?action=logout">
                                            <button class="btn btn-danger" type="button"> Cerrar  sesión</button>

                                        </a>
                                    </div>
                                </div>
                            </div>

                        </div>
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

