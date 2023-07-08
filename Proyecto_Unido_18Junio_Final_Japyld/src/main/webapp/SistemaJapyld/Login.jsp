<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Login</title>

    <!-- Custom fonts for this template-->
    <link href="recursos/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="recursos/css/sb-admin-2.min.css" rel="stylesheet">
    <style>
        .bg-loginimagen {
            background: url("https://unsplash.com/photos/ITFwHdPEED0/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8M3x8dmlkZW9qdWVnb3N8ZXN8MHx8fHwxNjg4NzQ5NjYyfDA&force=true&w=640");
            background-position: center;
            background-size: cover;
        }
    </style>

</head>

<body class="bg-gradient-primary">

<div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-10 col-lg-12 col-md-9">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-6 d-none d-lg-block bg-loginimagen"></div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Bienvenido</h1>
                                </div>
                                <form class="user" method="POST" action="<%=request.getContextPath()%>/LoginServlet">
                                    <div class="form-group">
                                        <input type="email" class="form-control" name = "inputEmail"
                                               placeholder="Correo Electrónico" autofocus="">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control" name ="inputPassword"
                                               placeholder="Contraseña" autofocus="">
                                    </div>
                                    <% if (request.getParameter("error") != null) { %>
                                    <div class = "text-danger">Error en usuario o contraseña.</div>
                                    <% } %>
                                    <% if (request.getParameter("errorUSR") != null) { %>
                                    <div class = "text-danger">Lo siento usuario. Has sido baneado.</div>
                                    <% } %>
                                    <% if (request.getParameter("errorADM") != null) { %>
                                    <div class = "text-danger">Lo siento Administrador. Fuiste despedido.</div>
                                    <% } %>
                                    <div class="form-group">
                                    </div>
                                    <button class ="btn btn-lg btn-primary btn-block" type="submit">Iniciar Sesión</button>
                                    <hr>
                                    <a href="<%=request.getContextPath()%>/RegisterServlet" class="btn btn-primary btn-control btn-block">
                                        Crear una cuenta nueva
                                    </a>
                                    <hr>
                                </form>
                                <hr>
                                <div class="text-center">

                                </div>
                                <div class="text-center">

                                </div>
                            </div>
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