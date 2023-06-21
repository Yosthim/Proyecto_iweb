<%--
  Created by IntelliJ IDEA.
  User: PABLO
  Date: 20/06/2023
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>
        function mostrarOpcionSeleccionada(selectElement) {
            var selectedOption = selectElement.options[selectElement.selectedIndex].text;
            selectElement.style.background = "white";
            selectElement.title = "Seleccionado: " + selectedOption;
        }
    </script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Register</title>

    <!-- Custom fonts for this template-->
    <link href="recursos/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="recursos/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Crea tu cuenta!</h1>
                        </div>
                        <form class="user" method="POST" action="<%=request.getContextPath()%>/RegisterServlet">
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="text" class="form-control" name="Nombre"
                                           placeholder="Nombres">
                                </div>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" name="Apellido"
                                           placeholder="Apellidos">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <select class="form-control" name="Genero" onchange="mostrarOpcionSeleccionada(this)">
                                        <option value="">Género</option>
                                        <option value="masculino">Masculino</option>
                                        <option value="femenino">Femenino</option>
                                        <option value="otro">Otro</option>
                                    </select>
                                </div>
                                <div class="col-sm-6">
                                    <select class="form-control" name="Categoria" onchange="mostrarOpcionSeleccionada(this)">
                                        <option value ="">Categorías</option>
                                        <option value="accion">Acción</option>
                                        <option value="terror">Terror</option>
                                        <option value="aventura">Aventura</option>
                                        <option value="Estrategia">Estrategia</option>
                                        <option value="deportivo">Deportivo</option>
                                        <option value="simulacion">Simulación</option>
                                        <option value="musical">Musical</option>
                                        <option value="arcade">Arcade</option>
                                        <option value="clasico">Clasico</option>
                                        <option value="infantil">Infantil</option>
                                        <option value="gestion">Gestion</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control" name="Email"
                                       placeholder="Correo Electrónico">
                            </div>
                            <div class="form-group">
                                <input type="date" class="form-control" name="Fecha_nacimiento"
                                       placeholder="Fecha de nacimiento">
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="password" class="form-control"
                                           name="Contraseña" placeholder="Contraseña">
                                </div>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control"
                                           name="Dni" placeholder="DNI">
                                </div>
                            </div>
                            <div class="centered">
                                <input type="submit" value="Registra tu Cuenta">
                            </div>
                        </form>
                        <hr>
                        <div class="text-center">
                            <a class="small" href="<%=request.getContextPath()%>/LoginServlet">Ya tienes cuenta? Inicia sesión</a>
                        </div>
                    </div>
                </div>
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
