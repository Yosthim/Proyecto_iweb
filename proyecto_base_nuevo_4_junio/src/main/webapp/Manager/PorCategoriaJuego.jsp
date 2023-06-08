<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyecto_base_japyld.Manager.Models.Beans.PorCategoriaJuego" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<PorCategoriaJuego> listaPorCategoriaJuego = (ArrayList<PorCategoriaJuego>) request.getAttribute("listaPorCategoriaJuego"); %>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Por Categoria de Juego</title>

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
          <a class="nav-link collapsed" href="recursos/vendor/fontawesome-free/css/all.min.css" data-toggle="collapse" data-target="#collapseTwo"
             aria-expanded="true" aria-controls="collapseTwo">
            <i class="fas fa-home"></i>
            <span>Panel Principal</span></a>
          </a>
          <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
              <h6 class="collapse-header">Detalles de JAPYLD:</h6>
              <a class="collapse-item" href="paginaprincialmanager.html">Resumen</a>
              <a class="collapse-item" href="paginaprincialmanager.html">Productos</a>
              <a class="collapse-item" href="paginaprincialmanager.html">Metas Semanales</a>
              <a class="collapse-item" href="paginaprincialmanager.html">Estadísticas</a>
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
          <a class="nav-link collapsed" href="recursos/vendor/fontawesome-free/css/all.min.css" data-toggle="collapse" data-target="#collapsePages"
             aria-expanded="true" aria-controls="collapsePages">
            <i class="fas fa-gamepad"></i>
            <span>JUEGOS</span>
          </a>
          <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
              <h6 class="collapse-header">Estadísticas:</h6>
              <a class="collapse-item" href="<%=request.getContextPath()%>/PorCategoriaJuegoServlet">Por categoría de Juego</a>
              <a class="collapse-item" href="<%=request.getContextPath()%>/PorConsolaJuegoServlet">Por consola de Juego</a>
              <h6 class="collapse-header">TOP 5:</h6>
              <a class="collapse-item" href="<%=request.getContextPath()%>/Top5MasVendidosServlet">Más vendidos</a>
              <a class="collapse-item" href="<%=request.getContextPath()%>/Top5MenosVendidosServlet">Menos vendidos</a>
              <div class="collapse-divider"></div>
              <h6 class="collapse-header">Más opciones:</h6>
              <a class="collapse-item" href="<%=request.getContextPath()%>/EditarJuegosServlet?a=l">Editar Juegos</a>
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
                <a class="nav-link dropdown-toggle" href="recursos/vendor/fontawesome-free/css/all.min.css" id="searchDropdown" role="button"
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
                <a class="nav-link dropdown-toggle" href="recursos/vendor/fontawesome-free/css/all.min.css" id="alertsDropdown" role="button"
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
                  <a class="dropdown-item d-flex align-items-center" href="recursos/vendor/fontawesome-free/css/all.min.css">
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
                  <a class="dropdown-item d-flex align-items-center" href="recursos/vendor/fontawesome-free/css/all.min.css">
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
                  <a class="dropdown-item d-flex align-items-center" href="recursos/vendor/fontawesome-free/css/all.min.css">
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
                  <a class="dropdown-item text-center small text-gray-500" href="recursos/vendor/fontawesome-free/css/all.min.css">Show All Alerts</a>
                </div>
              </li>

              <!-- Nav Item - Messages -->
              <li class="nav-item dropdown no-arrow mx-1">
                <a class="nav-link dropdown-toggle" href="recursos/vendor/fontawesome-free/css/all.min.css" id="messagesDropdown" role="button"
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
                  <a class="dropdown-item d-flex align-items-center" href="recursos/vendor/fontawesome-free/css/all.min.css">
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
                  <a class="dropdown-item d-flex align-items-center" href="recursos/vendor/fontawesome-free/css/all.min.css">
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
                <a class="nav-link dropdown-toggle" href="recursos/vendor/fontawesome-free/css/all.min.css" id="userDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <span class="mr-2 d-none d-lg-inline text-gray-600 small">MANAGER</span>
                  <img class="img-profile rounded-circle"
                       src="recursos/img/undraw_profile.svg">
                </a>
                <!-- Dropdown - User Information -->
                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                     aria-labelledby="userDropdown">
                  <a class="dropdown-item" href="recursos/vendor/fontawesome-free/css/all.min.css">
                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                    Profile
                  </a>
                  <a class="dropdown-item" href="recursos/vendor/fontawesome-free/css/all.min.css">
                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                    Settings
                  </a>
                  <a class="dropdown-item" href="recursos/vendor/fontawesome-free/css/all.min.css">
                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                    Activity Log
                  </a>
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item" href="recursos/vendor/fontawesome-free/css/all.min.css" data-toggle="modal" data-target="#logoutModal">
                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                    Logout
                  </a>
                </div>
              </li>

            </ul>

          </nav>
          <!-- End of Topbar -->

          <!-- Begin Page Content -->
          <div class="container-xl px-4 mt-4">
            <!-- Account page navigation-->
            <br>

            <div class="row">
              <div class="col-xl-6">
                <!-- Profile picture card-->
                <div class="card mb-4 mb-xl-0">

                  <div class="card-header font-weight-bold text-primary">TABLA POR CATEGORÍA DE VIDEOJUEGOS</div>
                  <div class="card-body text">

                    <section class="intro">
                      <div class="gradient-custom-1 h-100">
                        <div class="mask d-flex align-items-center h-100">
                          <div class="container">
                            <div class="row justify-content-center">
                              <div class="col-12">
                                <div class="table-responsive bg-white">
                                  <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                      <tr>
                                        <th class="text-center">CATEGORÍA</th>
                                        <th class="text-center">N° USUARIOS</th>

                                      </tr>
                                    </thead>

                                    <tbody>
                                      <% for (PorCategoriaJuego cv : listaPorCategoriaJuego) { %>
                                      <tr>
                                        <td class="text-center"><%=cv.getNombreCategoria() %></td>
                                        <td class="text-center"><%=cv.getNum_usuarios()%></td>
                                      </tr>
                                      <% } %>
                                    </tbody>
                                  </table>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </section>
                  </div>
                </div>
                <br>
                <br>
              </div>


              <div class="col-xl-6">
                <!-- Account details card-->
                <div class="card mb-5">
                  <div class="card-header">
                    <b class="mr-5">RESUMEN</b>
                  </div>
                  <br>
                  <div class="card-body">

                    <% String[] nombreCategoria = new String[listaPorCategoriaJuego.size()];%>
                    <% int[] cantUsuarios = new int[listaPorCategoriaJuego.size()];%>

                    <% for (int i = 0; i < listaPorCategoriaJuego.size(); i++) { %>
                    <% nombreCategoria[i] = listaPorCategoriaJuego.get(i).getNombreCategoria(); %>
                    <% cantUsuarios[i] = listaPorCategoriaJuego.get(i).getNum_usuarios(); %>
                    <% } %>

                    <p class="alert alert-success" role="alert"> 1. La mayoría de usuarios prefieren los juegos de <%= nombreCategoria[0] %></p>
                    <p div class="alert alert-warning"> 2. Algunos usuarios prefieren los juegos de <%= nombreCategoria[1] + " y " + nombreCategoria[2] %></p>
                    <p class="alert alert-info" role="alert"> 3. Otros usuarios prefieren los juevos de <%= nombreCategoria[3] %> </p>
                    <p div class="alert alert-danger" role="alert"> 4. Pocos usuarios prefieren los juegos de <%= nombreCategoria[4] %></p>
                  </div>
                </div>
              </div>
            </div>

            <div class="row justify-content-center">
              <div class="col-xl-6">
                <!-- Profile picture card-->
                <div class="card mb-4 mb-xl-0">
                  <div class="card-header m-0 font-weight-bold text-primary">GRÁFICO POR CATEGORÍA DE VIDEOJUEGOS</div>
                  <br>
                  <div class="container">
                    <canvas id="barChart4"></canvas>
                  </div>

                  <script>
                    var ctx = document.getElementById('barChart4').getContext('2d');
                    var barChart4 = new Chart(ctx, {
                      type: 'bar',
                      data: {
                        labels: ['<%= nombreCategoria[0] %>', '<%= nombreCategoria[1] %>', '<%= nombreCategoria[2] %>', '<%= nombreCategoria[3] %>', '<%= nombreCategoria[4] %>'],
                        datasets: [{
                          labels: ' ',
                          data: [<%= cantUsuarios[0] %>, <%= cantUsuarios[1] %>, <%= cantUsuarios[2] %>, <%= cantUsuarios[3] %>, <%= cantUsuarios[4] %>],
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

            <br>
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
    <a class="scroll-to-top rounded" href="recursos/vendor/fontawesome-free/css/all.min.css">
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
