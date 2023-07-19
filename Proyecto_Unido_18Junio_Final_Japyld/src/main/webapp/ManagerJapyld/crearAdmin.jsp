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

    <title>Crear Administrador</title>

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
                    <!-- Topbar -->
                    <jsp:include page="/Includes/ManagerJapyld/topbarManager.jsp">
                        <jsp:param name="title" value="Mas detalles"/>
                    </jsp:include>
                    <!-- End of Topbar -->

                    <% if (session.getAttribute("corr") != null) { %>
                    <div class="alert alert-danger d-flex align-items-center justify-content-center" role="alert">
                        <%= session.getAttribute("corr") %>
                    </div>
                    <% session.setAttribute("corr", null); %>
                    <% } %>

                    <% if (session.getAttribute("noma") != null) { %>
                    <div class="alert alert-danger d-flex align-items-center justify-content-center" role="alert">
                        <%= session.getAttribute("noma") %>
                    </div>
                    <% session.setAttribute("noma", null); %>
                    <% } %>

                    <% if (session.getAttribute("dny") != null) { %>
                    <div class="alert alert-danger d-flex align-items-center justify-content-center" role="alert">
                        <%= session.getAttribute("dny") %>
                    </div>
                    <% session.setAttribute("dny", null); %>
                    <% } %>

            <!-- Begin Page Content -->
                    <div class="container-xl px-4 mt-4">
                        <!-- Account page navigation-->
                        <br>
                        <div class="tab-pane py-5 py-xl-10 fade show active" id="wizard1" role="tabpanel" aria-labelledby="wizard1-tab">
                            <div class="row justify-content-center">
                                <div class="col-xxl-6 col-xl-8">
                                    <h3 class="text-primary">Registrar Administrador</h3>
                                    <br>
                                    <h5 class="card-title mb-4">Ingrese la siguiente información</h5>
                                    <form method="POST" action="<%=request.getContextPath()%>/ModuloAdminServlet?action=guardar" wtx-context="36BA0B7D-EB23-4009-BD9E-83DA80BAD4DE">
                                        <div class="row gx-3">
                                            <div class="mb-3 col-md-6" class="form-group">
                                                <label class="small mb-1" for="nombre">Nombre </label>
                                                <input class="form-control" id="nombre" type="text" name="nombre" placeholder="Enter Firstname"  pattern="[A-Za-záÁéÉíÍóÓúÚñÑüÜ\s]+" wtx-context="434A1E15-D4FD-4427-8AAE-AEFCABA1691D" title="Ingrese solo letras sin caracteres especiales ni números" required>
                                                <span class="error-message"></span>
                                            </div>
                                            <div class="mb-3 col-md-6">
                                                <label class="small mb-1" for="apellido">Apellido</label>
                                                <input class="form-control" id="apellido" type="text" name="apellido" placeholder="Enter Lastname"  pattern="[A-Za-záÁéÉíÍóÓúÚñÑüÜ\s]+" wtx-context="42795CFB-4E11-4FF6-A475-9275BD7BCAAE" title="Ingrese solo letras sin caracteres especiales ni números" required>
                                                <span class="error-message"></span>
                                            </div>
                                        </div>

                                        <div class="row gx-3">
                                            <div class="mb-3 col-md-6 form-group">
                                                <label class="small mb-1" for="dni">Dni</label>
                                                <input class="form-control" id="dni" type="text" name="dni" placeholder="Enter dni" pattern="[0-9]{8}" title="Ingrese solo números" wtx-context="42795CFB-4E11-4FF6-A475-9275BD7BCAAE" title="Ingrese solo numeros" maxlength="8" required oninput="this.value = this.value.replace(/[^0-9]/g, '');">
                                                <span class="error-message"></span>
                                            </div>


                                            <div class="mb-3 col-md-6 form-group">
                                                <label class="small mb-1" for="fecha">Nacimiento</label>
                                                <input  class="form-control datetimepicker" id="fecha" name="fecha" type="date" wtx-context="434A1E15-D4FD-4427-8AAE-AEFCABA1691D" title="Ingrese una fecha de nacimiento" min="1994-12-31" max="2015-12-31" required>
                                                <span class="error-message"></span>
                                            </div>
                                        </div>

                                        <div class="mb-3" class="form-group">
                                            <label class="small mb-1" for="correo">Correo electrónico</label>
                                            <input class="form-control" id="correo" type="email" name="correo" placeholder="Enter your email address"  wtx-context="9DF972A9-6C9A-45AE-8715-63056FD50C08" required>
                                            <span class="error-message"></span>
                                        </div>

                                        <div class="mb-3" class="form-group">
                                            <label class="small mb-1" for="contrasenia">Contraseña</label>
                                            <input class="form-control" id="contrasenia" type="password" name="contrasenia" placeholder="Enter Password"  wtx-context="9DF972A9-6C9A-45AE-8715-63056FD50C08" required>
                                            <span class="error-message"></span>
                                        </div>

                                        <hr class="my-4">
                                        <div class="d-flex justify-content-between">
                                            <a href="<%=request.getContextPath()%>/ModuloAdminServlet">
                                                <button class="btn btn-light alert-danger" type="button" >Cancelar</button>
                                            </a>
                                            <button class="btn btn-primary" type="submit">Añadir</button>
                                        </div>
                                    </form>

                                    <script>
                                        document.addEventListener('DOMContentLoaded', function() {
                                            var form = document.querySelector('form');
                                            var requiredInputs = form.querySelectorAll(':required');

                                            form.addEventListener('submit', function(event) {
                                                var requiredFields = form.querySelectorAll(':required');
                                                for (var i = 0; i < requiredFields.length; i++) {
                                                    var field = requiredFields[i];
                                                    if (!field.value.trim()) {
                                                        event.preventDefault(); // Evita que se envíe el formulario
                                                        field.classList.add('error');
                                                        var errorMessage = document.createElement('span');
                                                        errorMessage.className = 'error-message';
                                                        errorMessage.textContent = field.dataset.errorMessage || 'Campo requerido';
                                                        field.parentNode.appendChild(errorMessage);
                                                    }
                                                }
                                            });

                                            for (var i = 0; i < requiredInputs.length; i++) {
                                                var input = requiredInputs[i];
                                                input.addEventListener('input', function() {
                                                    this.classList.remove('error');
                                                    var errorMessage = this.parentNode.querySelector('.error-message');
                                                    if (errorMessage) {
                                                        errorMessage.remove();
                                                    }
                                                });
                                            }
                                        });
                                    </script>

                                </div>
                            </div>
                        </div>
                        <br>
                        <br>
                    </div>
                    <br>
                </div>
            <!-- End of Main Content -->

            <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; JAPYLD Solutions 2023</span>
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
