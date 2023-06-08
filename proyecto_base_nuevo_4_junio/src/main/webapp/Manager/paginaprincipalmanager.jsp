<%@ page import="com.example.proyecto_base_japyld.models.beans.Objetivos" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: jossr
  Date: 7/06/2023
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Objetivos> objetivos = (ArrayList<Objetivos>) request.getAttribute("objetivos");
%>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Página Principal Manager</title>

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
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="paginaprincialmanager.html">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">JAPYLD <sup>TM</sup></div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">
        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item active">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
               aria-expanded="true" aria-controls="collapseTwo">
                <i class="fas fa-home"></i>
                <span>Panel Principal</span></a>
            </a>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Detalles de JAPYLD:</h6>
                    <a class="collapse-item" href="#resumen">Resumen</a>
                    <a class="collapse-item" href="#productos">Productos</a>
                    <a class="collapse-item" href="#metas">Metas Semanales</a>
                    <a class="collapse-item" href="#estadisticas">Estadísticas</a>
                </div>
            </div>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            MÓDULOS
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
               aria-expanded="true" aria-controls="collapsePages">
                <i class="fas fa-gamepad"></i>
                <span>JUEGOS</span>
            </a>
            <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Estadísticas:</h6>
                    <a class="collapse-item" href="PorGeneroJuego.html">Por género de Juego</a>
                    <a class="collapse-item" href="PorConsolaJuego.html">Por consola de Juego</a>
                    <h6 class="collapse-header">TOP 5:</h6>
                    <a class="collapse-item" href="Top5masvendidos.html">Más vendidos</a>
                    <a class="collapse-item" href="Top5menosvendidos.html">Menos vendidos</a>
                    <div class="collapse-divider"></div>
                    <h6 class="collapse-header">Más opciones:</h6>
                    <a class="collapse-item" href="EditarJuegos.html">Editar Juegos</a>
                </div>
            </div>
        </li>

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="TablaAdministradores.html">
                <i class="fas fa-user-shield"></i>
                <span>ADMINISTRADOR</span></a>
        </li>

        <!-- Nav Item - Tables -->
        <li class="nav-item">
            <a class="nav-link" href="TablaUsuarios.html">
                <i class="fas fa-user"></i>
                <span>USUARIO</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">
        <br>

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

    </ul>
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
                <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                    <div class="input-group">
                        <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                               aria-label="Search" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="button">
                                <i class="fas fa-search fa-sm"></i>
                            </button>
                        </div>
                    </div>
                </form>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <li class="nav-item dropdown no-arrow d-sm-none">
                        <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-search fa-fw"></i>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                             aria-labelledby="searchDropdown">
                            <form class="form-inline mr-auto w-100 navbar-search">
                                <div class="input-group">
                                    <input type="text" class="form-control bg-light border-0 small"
                                           placeholder="Search for..." aria-label="Search"
                                           aria-describedby="basic-addon2">
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button">
                                            <i class="fas fa-search fa-sm"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </li>

                    <!-- Nav Item - Alerts -->
                    <li class="nav-item dropdown no-arrow mx-1">
                        <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-bell fa-fw"></i>
                            <!-- Counter - Alerts -->
                            <span class="badge badge-danger badge-counter">3+</span>
                        </a>
                        <!-- Dropdown - Alerts -->
                        <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="alertsDropdown">
                            <h6 class="dropdown-header">
                                Alerts Center
                            </h6>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-primary">
                                        <i class="fas fa-file-alt text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 12, 2019</div>
                                    <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-success">
                                        <i class="fas fa-donate text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 7, 2019</div>
                                    $290.29 has been deposited into your account!
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-warning">
                                        <i class="fas fa-exclamation-triangle text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 2, 2019</div>
                                    Spending Alert: We've noticed unusually high spending for your account.
                                </div>
                            </a>
                            <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                        </div>
                    </li>

                    <!-- Nav Item - Messages -->
                    <li class="nav-item dropdown no-arrow mx-1">
                        <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-envelope fa-fw"></i>
                            <!-- Counter - Messages -->
                            <span class="badge badge-danger badge-counter">7</span>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="messagesDropdown">
                            <h6 class="dropdown-header">
                                Message Center
                            </h6>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle" src="recursos/img/undraw_profile_1.svg"
                                         alt="...">
                                    <div class="status-indicator bg-success"></div>
                                </div>
                                <div class="font-weight-bold">
                                    <div class="text-truncate">Hi there! I am wondering if you can help me with a
                                        problem I've been having.</div>
                                    <div class="small text-gray-500">Emily Fowler · 58m</div>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle" src="recursos/img/undraw_profile_2.svg"
                                         alt="...">
                                    <div class="status-indicator"></div>
                                </div>
                                <div>
                                    <div class="text-truncate">I have the photos that you ordered last month, how
                                        would you like them sent to you?</div>
                                    <div class="small text-gray-500">Jae Chun · 1d</div>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle" src="recursos/img/undraw_profile_3.svg"
                                         alt="...">
                                    <div class="status-indicator bg-warning"></div>
                                </div>
                                <div>
                                    <div class="text-truncate">Last month's report looks great, I am very happy with
                                        the progress so far, keep up the good work!</div>
                                    <div class="small text-gray-500">Morgan Alvarez · 2d</div>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60"
                                         alt="...">
                                    <div class="status-indicator bg-success"></div>
                                </div>
                                <div>
                                    <div class="text-truncate">Am I a good boy? The reason I ask is because someone
                                        told me that people say this to all dogs, even if they aren't good...</div>
                                    <div class="small text-gray-500">Chicken the Dog · 2w</div>
                                </div>
                            </a>
                            <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                        </div>
                    </li>

                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">Tu Perfil</span>
                            <img class="img-profile rounded-circle"
                                 src="recursos/img/undraw_profile.svg">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Profile
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                Settings
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                Activity Log
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Logout
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid" id="resumen">
                <br>
                <h3 class="h4 mb-1 text-gray-800"><b>RESUMEN</b></h3>
                <br>
                <h4 class="h5 mb-1 text-gray-800">ÚLTIMOS COMENTARIOS</h4>
                <br>
                <!-- Content Row -->
                <div class="row">

                    <!-- First Column -->
                    <div class="col-lg-4">
                        <!-- Custom Text Color Utilities -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Juego "ABCDEFG</h6>
                            </div>
                            <div class="card-body">
                                <p class="p-3 bg-gray-200"> 1. Este juego...</p>
                                <p class="p-3 bg-gray-300"> 2. No he dejado de jugar...</p>
                                <p class="p-3 bg-gray-400"> 3. Creo que los gráficos...</p>
                                <p class="p-3 bg-gray-500"> 4. Pensé que sería más...</p>
                            </div>
                        </div>
                    </div>

                    <!-- Second Column -->
                    <div class="col-lg-4">

                        <!-- Custom Text Color Utilities -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Juego "ABCDEFG</h6>
                            </div>
                            <div class="card-body">
                                <p class="p-3 bg-gray-200"> 1. Este juego...</p>
                                <p class="p-3 bg-gray-300"> 2. No he dejado de jugar...</p>
                                <p class="p-3 bg-gray-400"> 3. Creo que los gráficos...</p>
                                <p class="p-3 bg-gray-500"> 4. Pensé que sería más...</p>

                            </div>
                        </div>

                    </div>

                    <!-- Second Column -->
                    <div class="col-lg-4">

                        <!-- Custom Text Color Utilities -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Juego "ABCDEFG</h6>
                            </div>
                            <div class="card-body">
                                <p class="p-3 bg-gray-200"> 1. Este juego...</p>
                                <p class="p-3 bg-gray-300"> 2. No he dejado de jugar...</p>
                                <p class="p-3 bg-gray-400"> 3. Creo que los gráficos...</p>
                                <p class="p-3 bg-gray-500"> 4. Pensé que sería más...</p>

                            </div>
                        </div>
                    </div>
                </div>

                <br>
                <br>
                <h4 class="h5 mb-1 text-gray-800">PUNTUACIONES</h4>
                <br>
                <!-- Content Row -->
                <!-- Content Row -->
                <div class="row">
                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            <h6>The Legend of Zelda</h6>
                                            <div class="h3 mb-0 font-weight-bold text-gray-800 justify-content-center">  ⭐ ⭐ ⭐ ⭐ ⭐</div>
                                        </div>
                                        <img src="recursos/img/Legend_of_Zelda.jpg" class="img-fluid" alt="Imagen">
                                        <div class="mt-3">
                                            <p class="mt-2">Precio: S/. 80</p>
                                            <button class="btn btn-success">Más Detalles</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            <h6>The Legend of Zelda</h6>
                                            <div class="h3 mb-0 font-weight-bold text-gray-800 justify-content-center">  ⭐ ⭐ ⭐ ⭐ ⭐</div>
                                        </div>
                                        <img src="recursos/img/Legend_of_Zelda.jpg" class="img-fluid" alt="Imagen">
                                        <div class="mt-3">
                                            <p class="mt-2">Precio: S/. 80</p>
                                            <button class="btn btn-success">Más Detalles</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            <h6>The Legend of Zelda</h6>
                                            <div class="h3 mb-0 font-weight-bold text-gray-800 justify-content-center">  ⭐ ⭐ ⭐ ⭐ ⭐</div>
                                        </div>
                                        <img src="recursos/img/Legend_of_Zelda.jpg" class="img-fluid" alt="Imagen">
                                        <div class="mt-3">
                                            <p class="mt-2">Precio: S/. 80</p>
                                            <button class="btn btn-success">Más Detalles</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Pending Requests Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-warning shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            <h6>The Legend of Zelda</h6>
                                            <div class="h3 mb-0 font-weight-bold text-gray-800 justify-content-center">  ⭐ ⭐ ⭐ ⭐ ⭐</div>
                                        </div>
                                        <img src=recursos/img/Legend_of_Zelda.jpg" class="img-fluid" alt="Imagen">
                                        <div class="mt-3">
                                            <p class="mt-2">Precio: S/. 80</p>
                                            <button class="btn btn-success">Más Detalles</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <br>

                <h4 class="h5 mb-1 text-gray-800">CATÁLOGO RECIENTE</h4>
                <br>
                <!-- Content Row -->
                <!-- Content Row -->
                <div class="row">
                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <h5 class="m-0 font-weight-bold text-primary">Juego "ABCDEF</h5>
                                        <br>
                                        <div class="h7 mb-0 font-weight-bold text-gray-800 justify-content-center">Nuevo de Acción</div>
                                        <p class="p-5 bg-gray-200">
                                            ¡N U E V O!
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>

                                        </p>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">Precio: S/ 90.00</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <h5 class="m-0 font-weight-bold text-primary">Juego "ABCDEF</h5>
                                        <br>
                                        <div class="h7 mb-0 font-weight-bold text-gray-800 justify-content-center">Nuevo de Acción</div>
                                        <p class="p-5 bg-gray-200">
                                            ¡N U E V O!
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>

                                        </p>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">Precio: S/ 90.00</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <h5 class="m-0 font-weight-bold text-primary">Juego "ABCDEF</h5>
                                        <br>
                                        <div class="h7 mb-0 font-weight-bold text-gray-800 justify-content-center">Nuevo de Aventura</div>
                                        <p class="p-5 bg-gray-200">
                                            ¡N U E V O!
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>

                                        </p>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">Precio: S/ 170.00</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <h5 class="m-0 font-weight-bold text-primary">Juego "ABCDEF</h5>
                                        <br>
                                        <div class="h7 mb-0 font-weight-bold text-gray-800 justify-content-center">Nuevos de Terror</div>
                                        <p class="p-5 bg-gray-200">
                                            ¡N U E V O!
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>

                                        </p>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">Precio: S/ 220.00</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <br>

            <div class="container-fluid" id="productos">
                <br>
                <br>
                <br>
                <h3 class="h4 mb-1 text-gray-800"><b>PRODUCTOS</b></h3>
                <br>
                <h4 class="h5 mb-1 text-gray-800">LO MÁS VENDIDO</h4>
                <br>
                <div class="row">
                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            <h6>📉 The Legend of Zelda</h6>
                                        </div>
                                        <img src="recursos/img/Legend_of_Zelda.jpg" class="img-fluid" alt="Imagen">
                                        <div class="mt-3">
                                            <p class="mt-2">Precio: S/. 80</p>
                                            <button class="btn btn-success">Más Detalles</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            <h6>📉 The Legend of Zelda</h6>
                                        </div>
                                        <img src="recursos/img/Legend_of_Zelda.jpg" class="img-fluid" alt="Imagen">
                                        <div class="mt-3">
                                            <p class="mt-2">Precio: S/. 80</p>
                                            <button class="btn btn-success">Más Detalles</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            <h6>📉 The Legend of Zelda</h6>
                                        </div>
                                        <img src="recursos/img/Legend_of_Zelda.jpg" class="img-fluid" alt="Imagen">
                                        <div class="mt-3">
                                            <p class="mt-2">Precio: S/. 80</p>
                                            <button class="btn btn-success">Más Detalles</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Pending Requests Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-warning shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            <h6>📉 The Legend of Zelda</h6>
                                        </div>
                                        <img src="recursos/img/Legend_of_Zelda.jpg" class="img-fluid" alt="Imagen">
                                        <div class="mt-3">
                                            <p class="mt-2">Precio: S/. 80</p>
                                            <button class="btn btn-success">Más Detalles</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <br>
                <h4 class="h5 mb-1 text-gray-800">LO MENOS VENDIDO</h4>
                <br>
                <div class="row">
                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            <h6>📈 The Legend of Zelda</h6>
                                        </div>
                                        <img src="recursos/img/Legend_of_Zelda.jpg" class="img-fluid" alt="Imagen">
                                        <div class="mt-3">
                                            <p class="mt-2">Precio: S/. 80</p>
                                            <button class="btn btn-success">Más Detalles</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            <h6>📈 The Legend of Zelda</h6>
                                        </div>
                                        <img src="recursos/img/Legend_of_Zelda.jpg" class="img-fluid" alt="Imagen">
                                        <div class="mt-3">
                                            <p class="mt-2">Precio: S/. 80</p>
                                            <button class="btn btn-success">Más Detalles</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            <h6>📈 The Legend of Zelda</h6>
                                        </div>
                                        <img src="recursos/img/Legend_of_Zelda.jpg" class="img-fluid" alt="Imagen">
                                        <div class="mt-3">
                                            <p class="mt-2">Precio: S/. 80</p>
                                            <button class="btn btn-success">Más Detalles</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Pending Requests Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-warning shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            <h6>📈 The Legend of Zelda</h6>
                                        </div>
                                        <img src="recursos/img/Legend_of_Zelda.jpg" class="img-fluid" alt="Imagen">
                                        <div class="mt-3">
                                            <p class="mt-2">Precio: S/. 80</p>
                                            <button class="btn btn-success">Más Detalles</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>


            <br>

            <div class="container-fluid" id="metas">
                <br>
                <br>
                <br>
                <h3 class="h4 mb-1 text-gray-800"><b>METAS SEMANALES</b></h3>
                <br>

                <br>
                <div class="d-sm-flex align-items-center justify-content-end mb-4">
                    <h4 class="h5 mb-1 text-gray-800">-------------------------------------------------------   OBJETIVOS   --------------------------------------------------------</h4>
                    <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-download fa-sm text-white-50"></i> EDITAR</a>
                    <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-download fa-sm text-white-50"></i> GUARDAR</a>
                </div>
                <%
                    for(Objetivos objetivos1 : objetivos){
                %>
                <div class="row">
                    <div class="col-lg-6 mb-4">
                        <input type="checkbox" name="check1" value="1" checked>
                        <div class="card bg-primary text-white shadow">
                            <div class="card-body">
                                Cantidad de venta por mes estimada es <%=objetivos1.getVentasPorMesJuego()%>
                                <div class="text-white-50 small">#4e73df</div>
                            </div>
                            <div class="card-body">
                                Cantidad de gatos por mes estimada es <%=objetivos1.getGastosPorMesJuego()%>
                                <div class="text-white-50 small">#4e73df</div>
                            </div>
                            <div class="card-body">
                                Cantidad de nuevos usuarios por mes estimada es <%=objetivos1.getUsuarioPorMes()%>
                                <div class="text-white-50 small">#4e73df</div>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>

            </div>

            <br>
            <br>
            <div class="container-fluid" id="estadisticas">
                <br>
                <br>
                <br>
                <h3 class="h4 mb-1 text-gray-800"><b>ESTADÍSTICAS</b></h3>
                <br>
                <div class="container-xl px-4 mt-4">
                    <!-- Account page navigation-->
                    <div class="row">
                        <div class="col-xl-6">
                            <!-- Profile picture card-->
                            <div class="card mb-4 mb-xl-0">
                                <div class="card-header"><b>GRÁFICO DE COMPRA Y VENTA</b></div>
                                <div class="card-body text">
                                    <br>
                                    <div class="container">
                                        <canvas id="barChart"></canvas>
                                    </div>
                                    <script>
                                        var ctx = document.getElementById('barChart').getContext('2d');
                                        var barChart = new Chart(ctx, {
                                            type: 'bar',
                                            data: {
                                                labels: ['Abril', 'Mayo', 'Junio', 'Julio', 'Agosto'],
                                                datasets: [{
                                                    labels: ' ',
                                                    data: [10, 20, 15, 45, 10],
                                                    backgroundColor: [
                                                        'rgba(76, 175, 80, 0.5)', // Verde
                                                        'rgba(33, 150, 243, 0.5)', // Azul
                                                        'rgba(255, 193, 7, 0.5)', // Amarillo
                                                        'rgba(156, 39, 176, 0.5)', // Morado
                                                        'rgba(244, 67, 54, 0.5)' // Rojo
                                                    ],
                                                    borderColor: [
                                                        'rgba(76, 175, 80, 1)', // Verde
                                                        'rgba(33, 150, 243, 1)', // Azul
                                                        'rgba(255, 193, 7, 1)', // Amarillo
                                                        'rgba(156, 39, 176, 1)', // Morado
                                                        'rgba(244, 67, 54, 1)' // Rojo
                                                    ],
                                                    borderWidth: 1
                                                }]
                                            },
                                            options: {
                                                scales: {
                                                    y: {
                                                        beginAtZero: true
                                                    }
                                                },
                                                plugins: {
                                                    legend: {
                                                        display: false // Oculta la leyenda
                                                    }
                                                }
                                            }
                                        });
                                    </script>
                                    <br>
                                </div>
                            </div>

                            <br>
                            <br>

                            <div class="card mb-4 mb-xl-0">
                                <div class="card-header"><b>GRÁFICO DE INGRESOS MENSUAL</b></div>
                                <div class="card-body text">
                                    <br>
                                    <div class="container">
                                        <canvas id="barChart2"></canvas>
                                    </div>
                                    <script>
                                        var ctx = document.getElementById('barChart2').getContext('2d');
                                        var barChart2 = new Chart(ctx, {
                                            type: 'bar',
                                            data: {
                                                labels: ['Abril', 'Mayo', 'Junio', 'Julio', 'Agosto'],
                                                datasets: [{
                                                    labels: ' ',
                                                    data: [10, 34, 23, 31, 10],
                                                    backgroundColor: [
                                                        'rgba(76, 175, 80, 0.5)', // Verde
                                                        'rgba(33, 150, 243, 0.5)', // Azul
                                                        'rgba(255, 193, 7, 0.5)', // Amarillo
                                                        'rgba(156, 39, 176, 0.5)', // Morado
                                                        'rgba(244, 67, 54, 0.5)' // Rojo
                                                    ],
                                                    borderColor: [
                                                        'rgba(76, 175, 80, 1)', // Verde
                                                        'rgba(33, 150, 243, 1)', // Azul
                                                        'rgba(255, 193, 7, 1)', // Amarillo
                                                        'rgba(156, 39, 176, 1)', // Morado
                                                        'rgba(244, 67, 54, 1)' // Rojo
                                                    ],
                                                    borderWidth: 1
                                                }]
                                            },
                                            options: {
                                                scales: {
                                                    y: {
                                                        beginAtZero: true
                                                    }
                                                },
                                                plugins: {
                                                    legend: {
                                                        display: false // Oculta la leyenda
                                                    }
                                                }
                                            }
                                        });
                                    </script>
                                    <br>
                                </div>
                            </div>
                        </div>

                        <div class="col-xl-6">
                            <!-- Account details card-->
                            <div class="card mb-4">
                                <div class="card-header"><b>ESTADÍSTICAS DE VENTAS DIARIAS</b></div>
                                <br>
                                <div class="container">
                                    <canvas id="barChart3"></canvas>
                                </div>
                                <script>
                                    var ctx = document.getElementById('barChart3').getContext('2d');
                                    var barChart3 = new Chart(ctx, {
                                        type: 'bar',
                                        data: {
                                            labels: ['Abril', 'Mayo', 'Junio', 'Julio', 'Agosto'],
                                            datasets: [{
                                                labels: ' ',
                                                data: [10, 20, 15, 45, 10],
                                                backgroundColor: [
                                                    'rgba(76, 175, 80, 0.5)', // Verde
                                                    'rgba(33, 150, 243, 0.5)', // Azul
                                                    'rgba(255, 193, 7, 0.5)', // Amarillo
                                                    'rgba(156, 39, 176, 0.5)', // Morado
                                                    'rgba(244, 67, 54, 0.5)' // Rojo
                                                ],
                                                borderColor: [
                                                    'rgba(76, 175, 80, 1)', // Verde
                                                    'rgba(33, 150, 243, 1)', // Azul
                                                    'rgba(255, 193, 7, 1)', // Amarillo
                                                    'rgba(156, 39, 176, 1)', // Morado
                                                    'rgba(244, 67, 54, 1)' // Rojo
                                                ],
                                                borderWidth: 1
                                            }]
                                        },
                                        options: {
                                            scales: {
                                                y: {
                                                    beginAtZero: true
                                                }
                                            },
                                            plugins: {
                                                legend: {
                                                    display: false // Oculta la leyenda
                                                }
                                            }
                                        }
                                    });
                                </script>
                                <br>
                                <div class="card-body ">
                                </div>
                            </div>

                            <br>

                            <div class="card mb-4">
                                <div class="card-header"><b>ESTADÍSTICAS DE CANTIDAD DE USUARIOS</b></div>
                                <br>
                                <div class="container">
                                    <canvas id="barChart4"></canvas>
                                </div>

                                <script>
                                    var ctx = document.getElementById('barChart4').getContext('2d');
                                    var barChart4 = new Chart(ctx, {
                                        type: 'bar',
                                        data: {
                                            labels: ['Abril', 'Mayo', 'Junio', 'Julio', 'Agosto'],
                                            datasets: [{
                                                labels: ' ',
                                                data: [10, 20, 15, 45, 10],
                                                backgroundColor: [
                                                    'rgba(76, 175, 80, 0.5)', // Verde
                                                    'rgba(33, 150, 243, 0.5)', // Azul
                                                    'rgba(255, 193, 7, 0.5)', // Amarillo
                                                    'rgba(156, 39, 176, 0.5)', // Morado
                                                    'rgba(244, 67, 54, 0.5)' // Rojo
                                                ],
                                                borderColor: [
                                                    'rgba(76, 175, 80, 1)', // Verde
                                                    'rgba(33, 150, 243, 1)', // Azul
                                                    'rgba(255, 193, 7, 1)', // Amarillo
                                                    'rgba(156, 39, 176, 1)', // Morado
                                                    'rgba(244, 67, 54, 1)' // Rojo
                                                ],
                                                borderWidth: 1
                                            }]
                                        },
                                        options: {
                                            scales: {
                                                y: {
                                                    beginAtZero: true
                                                }
                                            },
                                            plugins: {
                                                legend: {
                                                    display: false // Oculta la leyenda
                                                }
                                            }
                                        }
                                    });
                                </script>
                                <br>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
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
                <a class="btn btn-primary" href="login.html">Logout</a>
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